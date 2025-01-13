package com.saesig.report;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.adopt.QAdopt;
import com.saesig.domain.diary.QDiary;
import com.saesig.domain.member.QMember;
import com.saesig.domain.report.QReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;

@RequiredArgsConstructor
@Repository
public class ReportQuerydslRepositoryImpl implements ReportQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public Page<ReportResponseDto> findAll(ReportRequestDto reportRequestDto, PageRequest pageable) {
        QReport qReport = QReport.report;
        QDiary qDiary = QDiary.diary;
        QAdopt qAdopt = QAdopt.adopt;
        QMember qMember = QMember.member;

        QueryResults<ReportResponseDto> results = queryFactory.select(
                Projections.fields(ReportResponseDto.class,
                    qReport.id
                    , qAdopt.title.as("adoptTitle")
                    , qDiary.title.as("diaryTitle")
                    , qAdopt.content.as("adoptContent")
                    , qDiary.content.as("diaryContent")
                    , qAdopt.id.as("diaryId")
                    , qDiary.id.as("adoptId")
                    , qReport.category
                    , qMember.nickname.as("memberName")
                )
            ).from(qReport)
            .leftJoin(qDiary).on(qReport.adopt.id.eq(qDiary.id))
            .leftJoin(qAdopt).on(qReport.diary.id.eq(qAdopt.id))
            .leftJoin(qMember).on(qReport.reportMember.id.eq(qMember.id))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
