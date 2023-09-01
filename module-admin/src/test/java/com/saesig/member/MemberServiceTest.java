package com.saesig.member;

import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.SaesigDiaryApplication;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.animalDivision.QAnimalDivision1;
import com.saesig.domain.animalDivision.QAnimalDivision2;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.QMember;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
class MemberServiceTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory jpaQueryFactory;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("jasypt.encryptorKey", "saesig");
    }

    @BeforeEach
    void beforeEach() {
        jpaQueryFactory = new JPAQueryFactory(em);
    }

    @Test
    @DisplayName("회원 단건 조회")
    void 회원_단건_조회() {
        //given
        Long memberId = 1L;
        QMember m = QMember.member;

        //when
        Member member = jpaQueryFactory
                .selectFrom(m)
                .where(m.id.eq(memberId))
                .fetchOne();

        //then
        assertThat(member).isNotNull();
    }

    @Test
    @DisplayName("회원 입양 횟수 조회")
    void 회원_입양_횟수_조회() {
        Long memberId = 1L;
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;

        Long adoptCount = jpaQueryFactory.select(qMember.count())
                .from(qMember)
                .innerJoin(qAdopt)
                .on(qMember.id.eq(qAdopt.adoptMember.id))
                .where(qAdopt.status.eq(AdoptStatus.COMPLETE))
                .fetchOne();

        assertThat(adoptCount).isNotNegative();
    }

    @Test
    @DisplayName("회원 분양 횟수 조회")
    void 회원_분양_횟수_조회() {
        Long memberId = 1L;
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;

        Long adoptCount = jpaQueryFactory.select(qMember.count())
                .from(qMember)
                .innerJoin(qAdopt)
                .on(qMember.id.eq(qAdopt.createdBy.id))
                .fetchOne();

        assertThat(adoptCount).isNotNegative();
    }

    @Test
    @DisplayName("querydsl subquery 회원 조회")
    void querydsl_subquery_회원_조회() {
        //given
        Long memberId = 1L;
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;

        //when
        Tuple tuple = jpaQueryFactory.select(
                        qMember,
                        JPAExpressions.select(qAdopt.count())
                                .from(qAdopt)
                                .where(qAdopt.createdBy.id.eq(qMember.id)),
                        JPAExpressions.select(qAdopt.count())
                                .from(qAdopt)
                                .where(qAdopt.status.eq(AdoptStatus.COMPLETE)
                                        .and(qAdopt.adoptMember.id.eq(qMember.id)))
                ).from(qMember)
                .where(qMember.id.eq(memberId))
                .fetchOne();
        //then
        assertThat(tuple).isNotNull();
    }

    @Test
    @DisplayName("querydsl 입양 목록 조회")
    void querydsl_입양_목록_조회(){
        //given
        Long memberId = 1L;
        QMember qMember = QMember.member;
        QAdopt qAdopt = QAdopt.adopt;
        QAnimalDivision1 qAnimalDivision1 = QAnimalDivision1.animalDivision1;
        QAnimalDivision2 qAnimalDivision2 = QAnimalDivision2.animalDivision2;

        //when
        QueryResults<AdoptedListDto> adoptedList = jpaQueryFactory.select(
                        Projections.fields(AdoptedListDto.class,
                                qAdopt.title
                                , qAdopt.gender
                                , qAdopt.animalDivision1.category
                                , qAdopt.animalDivision2.category
                                , qAdopt.adoptMember.nickname
                                , qAdopt.modifiedAt
                        )
                ).from(qAdopt)
                .innerJoin(qAnimalDivision1).on(qAdopt.animalDivision1.id.eq(qAnimalDivision1.id))
                .innerJoin(qAnimalDivision2).on(qAdopt.animalDivision2.id.eq(qAnimalDivision2.id))
                .innerJoin(qMember).on(qAdopt.adoptMember.id.eq(qMember.id))
                .where(qAdopt.status.eq(AdoptStatus.COMPLETE).and(qMember.id.eq(memberId)))
                .fetchResults();

        List<AdoptedListDto> results = adoptedList.getResults();

        //then
        assertThat(results.size()).isNotNegative();
    }


}