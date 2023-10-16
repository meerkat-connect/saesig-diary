package com.saesig.dormantMember.dto;

import com.saesig.common.RequestDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class DormantMemberRequestDto extends RequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dormancyStartDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dormancyEndDate;


    public DormantMemberRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword, LocalDate dormancyStartDate, LocalDate dormancyEndDate) {
        super(start, length, pageNum, searchType, searchKeyword);
        this.dormancyStartDate = dormancyStartDate;
        this.dormancyEndDate = dormancyEndDate;
    }
}

