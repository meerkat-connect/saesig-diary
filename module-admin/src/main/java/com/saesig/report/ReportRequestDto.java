package com.saesig.report;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class ReportRequestDto extends RequestDto {
    private String searchCategory;

    private String searchTitle;

    private String searchStatus;

    private String searchReportCategory;

    public ReportRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword, String searchCategory, String searchTitle, String searchStatus, String searchReportCategory) {
        super(start, length, pageNum, searchType, searchKeyword);
        this.searchCategory = searchCategory;
        this.searchTitle = searchTitle;
        this.searchStatus = searchStatus;
        this.searchReportCategory = searchReportCategory;
    }
}
