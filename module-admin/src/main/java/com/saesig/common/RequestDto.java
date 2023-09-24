package com.saesig.common;

import lombok.Getter;

@Getter
public class RequestDto {
    private String searchType;
    private String searchKeyword;
    private Integer start;
    private Integer length;
    private Integer pageNum;

    public RequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword) {
        this.start = start;
        this.length = length;
        this.pageNum = pageNum;
        this.searchType = searchType;
        this.searchKeyword = searchKeyword;
    }
}
