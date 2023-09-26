package com.saesig;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.QMember;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("local")
class QuerydslTest {
    @Autowired
    EntityManager em;

    @Test
    @DisplayName("Querydsl 회원 조회")
    void Querydsl_회원_조회(){
        //given
        JPAQueryFactory query = new JPAQueryFactory(em);
        QMember member = QMember.member;

        //when
        List<Member> members = query.selectFrom(member).fetch();

        //then
        assertThat(members).isNotNull();

    }

}
