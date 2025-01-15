package com.saesig.report;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.diary.QDiary;
import com.saesig.domain.member.QMember;
import com.saesig.domain.report.QReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ReportQuerydslRepositoryImpl implements ReportQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ReportResponseDto> findAll(ReportRequestDto reportRequestDto, PageRequest pageable) {
        QReport qReport = QReport.report;
        QDiary qDiary = QDiary.diary;
        QAdopt qAdopt = QAdopt.adopt;
        QMember qMember = QMember.member;
        QMember qAdoptMember = QMember.member;
        QMember qDiaryMember = QMember.member;

        QueryResults<ReportResponseDto> results = queryFactory.select(
                        Projections.constructor(
                                ReportResponseDto.class,
                                qReport.id
                                , qReport.category
                                , qReport.createdAt
                                , qReport.modifiedAt
                                , qMember.nickname.as("reporterName")
                                , Projections.constructor(
                                        ReportResponseDto.AdoptDto.class,
                                        qAdopt.id
                                        , qAdopt.content
                                        , qAdopt.title
                                        , qAdopt.status
                                        , ExpressionUtils.as(
                                                JPAExpressions.select(qAdoptMember.nickname)
                                                        .from(qMember)
                                                        .where(qMember.id.eq(qAdopt.createdBy.id)),
                                                "createdBy"
                                        )
                                )
                                , Projections.constructor(
                                        ReportResponseDto.DiaryDto.class,
                                        qDiary.id
                                        , qDiary.title
                                        , qDiary.content
                                        , qDiary.status
                                        , ExpressionUtils.as(
                                                JPAExpressions.select(qDiaryMember.nickname)
                                                        .from(qMember)
                                                        .where(qMember.id.eq(qDiary.createdBy.id)),
                                                "createdBy"
                                        )
                                )
                        )
                ).from(qReport)
                .leftJoin(qReport.diary, qDiary)
                .leftJoin(qReport.adopt, qAdopt)
                .leftJoin(qReport.reportMember, qMember)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
