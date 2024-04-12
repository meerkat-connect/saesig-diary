package com.saesig.dormantMember.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.QDormantMember;
import com.saesig.domain.member.QMember;
import com.saesig.dormantMember.dto.DormantMemberRequestDto;
import com.saesig.dormantMember.dto.DormantMemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@RequiredArgsConstructor
@Repository
public class DormantMemberRepository {
    private final JPAQueryFactory queryFactory;
    private static final QDormantMember dormantMember = QDormantMember.dormantMember;
    private static final QMember member = QMember.member;

    public Page<DormantMemberResponseDto> findAll(DormantMemberRequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest pageRequest = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        QueryResults<DormantMemberResponseDto> dormantMembers =
                queryFactory.select(
                                Projections.fields(
                                        DormantMemberResponseDto.class,
                                        dormantMember.id,
                                        member.id.as("memberId"),
                                        dormantMember.signupMethod,
                                        dormantMember.email,
                                        dormantMember.nickname,
                                        dormantMember.status,
                                        member.createdAt.as("joinedAt"),
                                        dormantMember.lastLoggedAt,
                                        dormantMember.createdAt.as("changedAt")
                                )
                        ).from(dormantMember)
                        .innerJoin(member)
                        .on(dormantMember.id.eq(member.id))
                        .where(searchEq(request.getSearchType(), request.getSearchKeyword()),
                                dormancyDateEq(request.getDormancyStartDate(), request.getDormancyEndDate()))
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetchResults();

        return new PageImpl<>(dormantMembers.getResults(), pageRequest, dormantMembers.getTotal());
    }

    private BooleanExpression searchEq(String searchType, String searchKeyword) {
        if (hasText(searchType)) {
            if ("email".equals(searchType)) {
                return dormantMember.email.like("%" + searchKeyword + "%");
            } else if ("nickname".equals(searchType)) {
                return dormantMember.nickname.like("%" + searchKeyword + "%");
            }
        }

        return null;
    }

    private BooleanExpression dormancyDateEq(LocalDate dormancyStartDate, LocalDate dormancyEndDate) {
        if (dormancyStartDate != null && dormancyEndDate != null) {
            BooleanExpression isGoeStartDate = dormantMember.createdAt.goe(dormancyStartDate.atStartOfDay());
            BooleanExpression isLoeEndDate = dormantMember.createdAt.loe(dormancyEndDate.atTime(LocalTime.MAX));

            return Expressions.allOf(isGoeStartDate, isLoeEndDate);
        }

        return null;
    }

    public Optional<DormantMemberResponseDto> findById(Long id) {
        DormantMemberResponseDto dormantMemberDetail = queryFactory.select(
                        Projections.fields(
                                DormantMemberResponseDto.class,
                                dormantMember.id,
                                member.id.as("memberId"),
                                dormantMember.signupMethod,
                                dormantMember.email,
                                dormantMember.nickname,
                                dormantMember.status,
                                member.createdAt.as("joinedAt"),
                                dormantMember.lastLoggedAt,
                                dormantMember.createdAt.as("changedAt")
                        )
                ).from(dormantMember)
                .innerJoin(member)
                .on(dormantMember.member.id.eq(member.id))
                .where(dormantMember.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(dormantMemberDetail);
    }

    public Optional<DormantMemberResponseDto> findByMemberId(Long memberId) {
        DormantMemberResponseDto dormantMemberDetail = queryFactory.select(
                        Projections.fields(
                                DormantMemberResponseDto.class,
                                dormantMember.id,
                                member.id.as("memberId"),
                                dormantMember.signupMethod,
                                dormantMember.email,
                                dormantMember.nickname,
                                dormantMember.status,
                                member.createdAt.as("joinedAt"),
                                dormantMember.lastLoggedAt,
                                dormantMember.createdAt.as("changedAt")
                        )
                ).from(dormantMember)
                .innerJoin(member)
                .on(dormantMember.member.id.eq(member.id))
                .where(dormantMember.member.id.eq(memberId))
                .fetchOne();

        return Optional.ofNullable(dormantMemberDetail);
    }


    public void deleteDormant(Long dormantId){
        queryFactory.delete(dormantMember)
                .where(dormantMember.id.in(dormantId))
                .execute();
    }

    public void deleteDormant(Long[] dormantMemberIds) {
        List<Tuple> dormantMembers = queryFactory.select(
                        dormantMember.id
                        , dormantMember.member.id
                ).from(dormantMember)
                .where(dormantMember.id.in(dormantMemberIds))
                .fetch();

        Long[] memberIds = dormantMembers.stream()
                .map(member -> member.get(dormantMember.member.id))
                .collect(Collectors.toList())
                .toArray(Long[]::new);

        queryFactory.delete(dormantMember)
                .where(dormantMember.id.in(dormantMemberIds))
                .execute();

        queryFactory.update(member)
                .set(member.status, MemberStatus.NORMAL)
                .where(member.id.in(memberIds))
                .execute();
    }
}
