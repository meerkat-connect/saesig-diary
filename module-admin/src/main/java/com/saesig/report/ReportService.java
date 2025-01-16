package com.saesig.report;

import com.saesig.role.DataTablesResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final ReportRepository reportRepository;
    public DataTablesResponseDto findAll(ReportRequestDto requestDto) {
        Integer pageNum = requestDto.getStart() / requestDto.getLength();
        PageRequest of = PageRequest.of(pageNum, requestDto.getLength(), Sort.by(Sort.Direction.DESC, "created_at"));

        Page<ReportResponseDto> reports = reportRepository.findAll(requestDto, of);

        return new DataTablesResponseDto(reports, reports.getContent());
    }

    public ReportResponseDto findById(Long reportId) {
        return reportRepository.findDetailById(reportId).orElseThrow(() -> new IllegalArgumentException("신고 데이터가 존재하지 않습니다."));
    }
}
