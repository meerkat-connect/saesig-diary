package com.saesig.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.role.QMemberRole;
import com.saesig.domain.role.QRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QueryDslMemberRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<Member> findByEmail(String email) {
        QMember member =QMember.member;
        QMemberRole memberRole = QMemberRole.memberRole;
        QRole role = QRole.role;

        Member findedMember = queryFactory.select(
                        QMember.member
                ).from(member)
                .leftJoin(member.memberRoles, memberRole).fetchJoin()
                .innerJoin(memberRole.role, role).fetchJoin()
                .where(member.email.eq(email))
                .fetchOne();

        return Optional.ofNullable(findedMember);
    }

}