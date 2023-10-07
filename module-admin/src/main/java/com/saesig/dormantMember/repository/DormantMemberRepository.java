package com.saesig.dormantMember.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class DormantMemberRepository {
    private final JPAQueryFactory queryFactory;
    private final QDormantMember dormantMember = QDormantMember.dormantMember;
    private final QMember member = QMember.member;

    public Page<DormantMemberResponseDto> findAll(DormantMemberRequestDto request) {
        Integer pageNum = request.getStart() / request.getLength();
        PageRequest pageRequest = PageRequest.of(pageNum, request.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        QueryResults<DormantMemberResponseDto> dormantMembers =
                queryFactory.select(
                                Projections.fields(DormantMemberResponseDto.class,
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
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetchResults();

        return new PageImpl<>(dormantMembers.getResults(),  pageRequest, dormantMembers.getTotal());
    }

    public Optional<DormantMemberResponseDto> findById(Long id) {
        DormantMemberResponseDto dormantMember = queryFactory.select(
                        Projections.fields(DormantMemberResponseDto.class,
                                this.dormantMember.signupMethod,
                                this.dormantMember.email,
                                this.dormantMember.nickname,
                                this.dormantMember.status,
                                member.createdAt.as("joinedAt"),
                                this.dormantMember.lastLoggedAt,
                                this.dormantMember.createdAt.as("changedAt")
                        )
                ).from(this.dormantMember)
                .innerJoin(member)
                .on(this.dormantMember.id.eq(member.id))
                .where(this.dormantMember.id.eq(id))
                .fetchOne();

        return Optional.ofNullable(dormantMember);
    }
}
