package com.saesig.member;

import com.saesig.common.RequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@Mapper
public interface MemberMapper {
    Page<ReportResponseDto> findReportList(@Param("id") Long id, @Param("request") RequestDto request, @Param("pageable") PageRequest pageable);
}
