package com.saesig.report;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportQuerydslRepository {
    Page<ReportResponseDto> findAll(ReportRequestDto requestDto, PageRequest of);

    Optional<ReportResponseDto> findDetailById(Long reportId);
}
