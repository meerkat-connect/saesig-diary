package com.saesig.dormantMember.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberStatus;
import com.saesig.domain.member.QDormantMember;
import com.saesig.domain.member.QMember;
import com.saesig.dormantMember.dto.DormantMemberRequestDto;
import com.saesig.dormantMember.dto.DormantMemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    private final EntityManager em;
    private final AuditorAware auditorAware;
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

    public void insertDormantMember(Long id) {
        Member loginMember = (Member) auditorAware.getCurrentAuditor().get();
        String nativeQuery =
                "INSERT INTO dormant_member (member_id, email, password, prev_password, signup_method, status, nickname, last_logged_at, password_modified_at, service_agreement, location_service_agreement, privacy_agreement, marketing_service_agreement, modified_at, modified_by, created_at, created_by) " +
                "SELECT m.id, m.email, m.password, m.prev_password, m.signup_method, m.status, m.nickname, m.last_logged_at, m.password_modified_at, m.service_agreement, m.location_service_agreement, m.privacy_agreement, m.marketing_service_agreement, NOW(), :modified_by, NOW(), :created_by " +
                "FROM member m " +
                "WHERE m.id = :id";

        em.createNativeQuery(nativeQuery)
                .setParameter("id", id)
                .setParameter("modified_by", loginMember.getId())
                .setParameter("created_by", loginMember.getId())
                .executeUpdate();
    }
}
