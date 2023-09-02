package com.saesig.member;

import com.saesig.common.RequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CustomMemberRepository {
    Page<MemberResponseDto> findAll(MemberRequestDto memberRequestDto, Pageable pageable);

    Page<AdoptedListResponseDto> findAdoptedList(Long id, RequestDto request, Pageable pageable);

    Page<AdoptionListResponseDto> findAdoptionList(Long id, RequestDto request, PageRequest pageable);

    Page<ReportResponseDto> findReportList(Long id, RequestDto request, PageRequest of);
}
