package com.saesig.common.log;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.log.AdminAccessLog;
import com.saesig.domain.log.QAdminAccessLog;
import com.saesig.domain.member.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@Repository
public class AdminAccessLogRepository {
    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;
    private static final QAdminAccessLog qAdminAccessLog = QAdminAccessLog.adminAccessLog;
    private static final QMember qMember = QMember.member;

    @Transactional
    public void insertLog(AdminAccessLogResponseDto adminAccessLogResponseDto) {
        AdminAccessLog adminAccessLog = adminAccessLogResponseDto.toEntity();
        entityManager.persist(adminAccessLog);
        entityManager.flush();
    }


    public Page<AdminAccessLogResponseDto> findAll(AdminAccessLogRequestDto adminAccessLogRequestDto) {
        Integer pageNum = adminAccessLogRequestDto.getStart() / adminAccessLogRequestDto.getLength();
        PageRequest pageRequest = PageRequest.of(pageNum, adminAccessLogRequestDto.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        QueryResults<AdminAccessLogResponseDto> dormantMembers =
                queryFactory.select(
                                Projections.fields(
                                        AdminAccessLogResponseDto.class,
                                        qAdminAccessLog.ip,
                                        qAdminAccessLog.userAgent,
                                        qAdminAccessLog.action,
                                        qAdminAccessLog.resourceUrl,
                                        qAdminAccessLog.resourceId,
                                        qAdminAccessLog.resourceName,
                                        qMember.nickname,
                                        qAdminAccessLog.createdAt
                                )
                        ).from(qAdminAccessLog)
                        .innerJoin(qMember)
                        .on(qMember.id.eq(qAdminAccessLog.createdBy.id))
                        .where(dateSearchEq(adminAccessLogRequestDto.getDateSearchStart(), adminAccessLogRequestDto.getDateSearchEnd()))
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetchResults();

        return new PageImpl<>(dormantMembers.getResults(), pageRequest, dormantMembers.getTotal());
    }

    private BooleanExpression dateSearchEq(LocalDate dateSearchStart, LocalDate dateSearchEnd) {
        if(dateSearchStart != null && dateSearchEnd != null) {
            BooleanExpression isGoeStartDate = qAdminAccessLog.createdAt.goe(dateSearchStart.atStartOfDay());
            BooleanExpression isLoeEndDate = qAdminAccessLog.createdAt.loe(dateSearchEnd.atTime(LocalTime.MAX));

            return Expressions.allOf(isGoeStartDate, isLoeEndDate);
        }

        return null;
    }



}
