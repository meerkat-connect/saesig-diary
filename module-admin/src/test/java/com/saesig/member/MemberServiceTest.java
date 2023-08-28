package com.saesig.member;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.SaesigDiaryApplication;
import com.saesig.domain.adopt.AdoptStatus;
import com.saesig.domain.adopt.QAdopt;
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

}