package com.saesig.member;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.common.RequestDto;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.animalDivision.QAnimalDivision1;
import com.saesig.domain.animalDivision.QAnimalDivision2;
import com.saesig.domain.member.*;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

import static com.querydsl.jpa.JPAExpressions.select;
import static org.springframework.util.StringUtils.hasText;

public class CustomMemberRepositoryImpl implements CustomMemberRepository {
    private final JPAQueryFactory queryFactory;
    private final EntityManager em;

    public CustomMemberRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
        this.em = em;
    }

    @Override
    public Page<MemberResponseDto> findAll(MemberRequestDto memberRequestDto, Pageable pageable) {
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;

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
                .where(searchEq(memberRequestDto.getSearchType(), memberRequestDto.getSearchKeyword()),
                        signupMethodEq(memberRequestDto.getSignupMethod()),
                        statusEq(memberRequestDto.getMemberStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(qMember.createdAt.desc())
                .fetchResults();

        return new PageImpl<>(members.getResults(), pageable, members.getTotal());
    }

    private BooleanExpression searchEq(String searchType, String searchKeyword) {
        if(hasText(searchType) && hasText(searchKeyword)) {
            if("email".equals(searchType)) {
                return QMember.member.email.like("%" + searchKeyword + "%");
            } else if("nickname".equals(searchType)){
                return QMember.member.nickname.like("%" + searchKeyword + "%");
            }
        }

        return null;
    }

    private BooleanExpression signupMethodEq(String signupMethod) {
        return hasText(signupMethod) ? QMember.member.signupMethod.eq(SignupMethod.valueOf(signupMethod)) : null;
    }

    private BooleanExpression statusEq(String status) {
        return hasText(status) ? QMember.member.status.eq(MemberStatus.valueOf(status)) : null;
    }

    /* public static BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }*/

    // 입양 기록 조회
    @Override
    public Page<AdoptedListResponseDto> findAdoptedList(Long id, RequestDto request, Pageable pageable) {
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;
        QAnimalDivision1 qAnimalDivision1 = QAnimalDivision1.animalDivision1;
        QAnimalDivision2 qAnimalDivision2 = QAnimalDivision2.animalDivision2;

        //when
        QueryResults<AdoptedListResponseDto> adoptedList = queryFactory.select(
                        Projections.fields(AdoptedListResponseDto.class,
                                qAdopt.title
                                , qAdopt.gender
                                , qAdopt.animalDivision1.category.as("animalDivision1")
                                , qAdopt.animalDivision2.category.as("animalDivision2")
                                , qAdopt.createdBy.nickname.as("adoptionMemberName")
                                , qAdopt.modifiedAt.as("adoptedCompletedAt")
                        )
                ).from(qAdopt)
                .innerJoin(qAnimalDivision1).on(qAdopt.animalDivision1.id.eq(qAnimalDivision1.id))
                .innerJoin(qAnimalDivision2).on(qAdopt.animalDivision2.id.eq(qAnimalDivision2.id))
                .innerJoin(qMember).on(qAdopt.createdBy.id.eq(qMember.id))
                .where(qAdopt.status.eq(AdoptStatus.COMPLETE).and(qMember.id.eq(id)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(adoptedList.getResults(), pageable, adoptedList.getTotal());
    }

    // 분양 기록 조회
    @Override
    public Page<AdoptionListResponseDto> findAdoptionList(Long id, RequestDto request, PageRequest pageable) {
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;
        QAnimalDivision1 qAnimalDivision1 = QAnimalDivision1.animalDivision1;
        QAnimalDivision2 qAnimalDivision2 = QAnimalDivision2.animalDivision2;

        //when
        QueryResults<AdoptionListResponseDto> adoptionList = queryFactory.select(
                        Projections.fields(AdoptionListResponseDto.class,
                                qAdopt.title
                                , qAdopt.gender
                                , qAdopt.animalDivision1.category.as("animalDivision1")
                                , qAdopt.animalDivision2.category.as("animalDivision2")
                                , qAdopt.adoptMember.nickname.as("adoptedMemberName")
                                , qAdopt.modifiedAt.as("adoptionCompletedAt")
                        )
                ).from(qAdopt)
                .innerJoin(qAnimalDivision1).on(qAdopt.animalDivision1.id.eq(qAnimalDivision1.id))
                .innerJoin(qAnimalDivision2).on(qAdopt.animalDivision2.id.eq(qAnimalDivision2.id))
                .leftJoin(qMember).on(qAdopt.adoptMember.id.eq(qMember.id))
                .where(qAdopt.status.ne(AdoptStatus.STOP).and(qAdopt.createdBy.id.eq(id)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(adoptionList.getResults(), pageable, adoptionList.getTotal());
    }

    @Override
    public Page<ReportResponseDto> findReportList(Long id, RequestDto request, PageRequest of) {
        String nativeQuery = "" +
                "SELECT m.nickname, m.email, ar.category, ar.content, ar.created_at as reported_at " +
                "FROM adopt_report ar INNER JOIN member m ON ar.member_id = m.id INNER JOIN adopt a ON ar.adopt_id = a.id " +
                "WHERE m.id = :id " +
                "UNION " +
                "SELECT m.nickname, m.email, dr.category, dr.content, dr.created_at as reported_at " +
                "FROM diary_report dr INNER JOIN member m ON dr.member_id = m.id INNER JOIN diary d ON dr.diary_id = d.id " +
                "WHERE m.id = :id " +
                "LIMIT :limit OFFSET :offset";

        Query queryResult = em.createNativeQuery(nativeQuery)
                .setParameter("id", id)
                .setParameter("offset", of.getOffset())
                .setParameter("limit", of.getPageSize());

        JpaResultMapper jpaResultMapper = new JpaResultMapper();
        List<ReportResponseDto> list = jpaResultMapper.list(queryResult, ReportResponseDto.class);

        return new PageImpl<>(list, of, list.size());
    }

    @Override
    public Page<BlockResponseDto> findBlockList(Long id, RequestDto request, PageRequest pageable) {
        QBlockedMember qBlockedMember = QBlockedMember.blockedMember;

        QueryResults<BlockResponseDto> blockList = queryFactory.select(
                        Projections.fields(
                                BlockResponseDto.class,
                                new CaseBuilder()
                                        .when(qBlockedMember.blockedMemberInfo.id.eq(id))
                                        .then("차단받음")
                                        .otherwise("차단함")
                                        .as("category"),
                                qBlockedMember.blockingMemberInfo.nickname.as("blockingMemberName"),
                                qBlockedMember.blockedMemberInfo.nickname.as("blockedMemberName"),
                                qBlockedMember.createdAt.as("blockedAt")
                        ))
                .from(qBlockedMember)
                .where(qBlockedMember.blockingMemberInfo.id.eq(id)
                        .or(qBlockedMember.blockedMemberInfo.id.eq(id)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();


        return new PageImpl<>(blockList.getResults(), pageable, blockList.getTotal());
    }

    @Override
    public Optional<MemberDetailDto> findDetailById(Long id) {
        QMember qMember = QMember.member;
        QDormantMember qDormantMember = QDormantMember.dormantMember;

        MemberDetailDto memberDetail = queryFactory.select(
                        Projections.fields(
                                MemberDetailDto.class,
                                qMember.nickname
                                , qMember.email
                                , qMember.signupMethod
                                , qMember.status
                                , qMember.createdAt
                                , qMember.lastLoggedAt
                                , qDormantMember.createdAt.as("dormancyConvertedAt")
                                , qMember.deletedAt
                        ))
                .from(qMember)
                .leftJoin(qDormantMember)
                .on(qMember.id.eq(qDormantMember.member.id))
                .where(qMember.id.eq(id)).fetchOne();

        return Optional.ofNullable(memberDetail);
    }
}
