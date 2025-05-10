package com.saesig.report;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.adopt.QAdoptHistory;
import com.saesig.domain.diary.QDiary;
import com.saesig.domain.diary.QDiaryHistory;
import com.saesig.domain.member.QMember;
import com.saesig.domain.report.QReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ReportQuerydslRepositoryImpl implements ReportQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ReportResponseDto> findAll(ReportRequestDto reportRequestDto, PageRequest pageable) {
        QReport qReport = QReport.report;
        QDiary qDiary = QDiary.diary;
        QAdopt qAdopt = QAdopt.adopt;
        QMember qMember = QMember.member;

        QueryResults<ReportResponseDto> results = queryFactory.select(
                        Projections.fields(
                                ReportResponseDto.class,
                                qReport.id
                                , new CaseBuilder().when(qDiary.isNull()).then("새로운 식구").otherwise("일상기록").as("type")
                                , qReport.category
                                , qReport.createdAt
                                , qReport.modifiedAt
                                , qMember.nickname.as("reporterName")
                                , Projections.constructor(
                                        ReportResponseDto.AdoptDto.class,
                                        qAdopt.id
                                        , qAdopt.title
                                        , qAdopt.content
                                        , qAdopt.status
                                        , ExpressionUtils.as(
                                                JPAExpressions.select(qMember.nickname)
                                                        .from(qMember)
                                                        .where(qMember.id.eq(qAdopt.createdBy.id)),
                                                "createdBy"
                                        )
                                ).as("adoptDto")
                                , Projections.constructor(
                                        ReportResponseDto.DiaryDto.class,
                                        qDiary.id
                                        , qDiary.title
                                        , qDiary.content
                                        , qDiary.status
                                        , ExpressionUtils.as(
                                                JPAExpressions.select(qMember.nickname)
                                                        .from(qMember)
                                                        .where(qMember.id.eq(qDiary.createdBy.id)),
                                                "createdBy"
                                        )
                                ).as("diaryDto")
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

    @Override
    public Optional<ReportResponseDto> findDetailById(Long reportId) {
        QReport qReport = QReport.report;
        QDiary qDiary = QDiary.diary;
        QAdopt qAdopt = QAdopt.adopt;
        QMember qMember = QMember.member;
        QAdoptHistory qAdoptHistory = QAdoptHistory.adoptHistory;
        QDiaryHistory qDiaryHistory = QDiaryHistory.diaryHistory;
        QAdoptHistory qLatestAdoptHistory = new QAdoptHistory("latestAdoptHistory");
        QDiaryHistory qLatestDiaryHistory = new QDiaryHistory("latestDiaryHistory");

        ReportResponseDto report = queryFactory.select(
                        Projections.fields(
                                ReportResponseDto.class,
                                qReport.id
                                , qReport.content
                                , new CaseBuilder().when(qDiary.isNull()).then("새로운 식구").otherwise("일상기록").as("type")
                                , qReport.category
                                , qReport.createdAt
                                , qReport.modifiedAt
                                , qReport.reportMember.nickname.as("reporterName")
                                , Projections.constructor(
                                        ReportResponseDto.AdoptDto.class,
                                        qAdopt.id
                                        , qAdopt.title
                                        , qAdopt.content
                                        , qAdopt.status
                                        , qAdopt.createdBy.nickname.as("createdBy")
                                        , qAdoptHistory.beforeStatus.as("beforeStatus")
                                        , qAdoptHistory.createdAt.as("beforeChangedAt")
                                ).as("adoptDto")
                                , Projections.constructor(
                                        ReportResponseDto.DiaryDto.class,
                                        qDiary.id
                                        , qDiary.title
                                        , qDiary.content
                                        , qDiary.status
                                        , qDiary.createdBy.nickname.as("createdBy")
                                        , qDiaryHistory.beforeStatus.as("beforeStatus")
                                        , qDiaryHistory.createdAt.as("beforeChangedAt")
                                ).as("diaryDto")
                        )
                ).from(qReport)
                .leftJoin(qReport.diary, qDiary)
                .leftJoin(qDiaryHistory).on(qDiary.id.eq(qDiaryHistory.diary.id)
                        .and(qDiaryHistory.createdAt.eq(
                                JPAExpressions.select(qLatestDiaryHistory.createdAt.max())
                                        .from(qLatestDiaryHistory)
                                        .where(qLatestDiaryHistory.diary.id.eq(qDiary.id))
                        )))
                .leftJoin(qReport.adopt, qAdopt)
                .leftJoin(qAdoptHistory).on(qAdopt.id.eq(qAdoptHistory.adopt.id)
                        .and(qAdoptHistory.createdAt.eq(
                                JPAExpressions.select(qLatestAdoptHistory.createdAt.max())
                                        .from(qLatestAdoptHistory)
                                        .where(qLatestAdoptHistory.adopt.id.eq(qAdopt.id))
                        )))
                .leftJoin(qReport.reportMember, qMember)
                .leftJoin(qDiary.createdBy, qMember)
                .leftJoin(qAdopt.createdBy, qMember)
                .where(qReport.id.eq(reportId))
                .fetchOne();

        return Optional.ofNullable(report);
    }
}


