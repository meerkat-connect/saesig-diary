package com.saesig.member;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.member.QMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

import static com.querydsl.jpa.JPAExpressions.select;
import static org.springframework.util.StringUtils.hasText;

public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    private final JPAQueryFactory queryFactory;

    public CustomMemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MemberResponseDto> findAll(MemberRequestDto memberRequestDto, Pageable pageable) {
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;

        //when
        QueryResults<MemberResponseDto> members = queryFactory.select(
                        Projections.fields(MemberResponseDto.class,
                                qMember.id, qMember.email, qMember.nickname, qMember.signupMethod, qMember.status, qMember.createdAt,
                                ExpressionUtils.as(select(qAdopt.count())
                                        .from(qAdopt)
                                        .where(qAdopt.createdBy.id.eq(qMember.id)), "adoptCount"),
                                ExpressionUtils.as(select(qAdopt.count())
                                        .from(qAdopt)
                                        .where(qAdopt.status.eq(AdoptStatus.COMPLETE)
                                                .and(qAdopt.adoptMember.id.eq(qMember.id))), "receiveCount")
                        )
                ).from(qMember)
                .offset(pageable.getOffset()) //0부터 시작 (zero index)
                .limit(pageable.getPageSize()) // 최대 2건 조회
                .fetchResults();

        return new PageImpl<>(members.getResults(), pageable, members.getTotal());
    }

    private BooleanExpression nickNameEq(String nickName) {
        return hasText(nickName) ? null : QMember.member.nickname.eq(nickName);
    }

    private BooleanExpression emailEq(String email) {
        return hasText(email) ? null : QMember.member.email.eq(email);
    }

    private BooleanExpression signupMethodEq(String signupMethod) {
        return hasText(signupMethod) ? null : QMember.member.email.eq(signupMethod);
    }

    private BooleanExpression statusEq(String status) {
        return hasText(status) ? null : QMember.member.email.eq(status);
    }
}
