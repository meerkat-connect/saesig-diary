package com.saesig.report;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReportQuerydslRepositoryImpl implements ReportQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<ReportReponseDto> findAll(ReportRequestDto reportRequestDto) {

        return List.of();
    }
}
