package com.saesig.common.log;

import com.saesig.common.RequestDto;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class AdminAccessLogRequestDto extends RequestDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSearchStart;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateSearchEnd;

    public AdminAccessLogRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword, LocalDate dateSearchStart, LocalDate dateSearchEnd) {
        super(start, length, pageNum, searchType, searchKeyword);
        this.dateSearchStart = dateSearchStart;
        this.dateSearchEnd = dateSearchEnd;
    }
}
