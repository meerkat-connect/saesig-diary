package com.saesig.common.log;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.log.AdminAccessLog;
import com.saesig.domain.log.QAdminAccessLog;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Repository
public class AdminAccessLogRepository {
    private final EntityManager entityManager;

    private final JPAQueryFactory queryFactory;

    @Transactional
    public void insertLog(AdminAccessLogResponseDto adminAccessLogResponseDto) {
        AdminAccessLog adminAccessLog = adminAccessLogResponseDto.toEntity();
        entityManager.persist(adminAccessLog);
        entityManager.flush();
    }


    public Page<AdminAccessLogResponseDto> findAll(AdminAccessLogRequestDto adminAccessLogRequestDto) {
        Integer pageNum = adminAccessLogRequestDto.getStart() / adminAccessLogRequestDto.getLength();
        PageRequest pageRequest = PageRequest.of(pageNum, adminAccessLogRequestDto.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));
        QAdminAccessLog qAdminAccessLog = QAdminAccessLog.adminAccessLog;

        QueryResults<AdminAccessLogResponseDto> dormantMembers =
                queryFactory.select(
                                Projections.fields(
                                        AdminAccessLogResponseDto.class,
                                        qAdminAccessLog.ip,
                                        qAdminAccessLog.userAgent,
                                        qAdminAccessLog.action,
                                        qAdminAccessLog.resourceUrl,
                                        qAdminAccessLog.resourceId,
                                        qAdminAccessLog.resourceName
                                )
                        ).from(qAdminAccessLog)
//                        .where(searchEq(request.getSearchType(), request.getSearchKeyword()),
//                                dormancyDateEq(request.getDormancyStartDate(), request.getDormancyEndDate()))
                        .offset(pageRequest.getOffset())
                        .limit(pageRequest.getPageSize())
                        .fetchResults();

        return new PageImpl<>(dormantMembers.getResults(), pageRequest, dormantMembers.getTotal());
    }


}
