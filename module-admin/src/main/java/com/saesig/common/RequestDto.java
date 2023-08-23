package com.saesig.common;

import lombok.Getter;

@Getter
public class RequestDto {
    private String searchType;
    private String searchName;
    private Integer start;
    private Integer length;
    private Integer pageNum;

    public RequestDto(Integer start, Integer length, Integer pageNum) {
        this.start = start;
        this.length = length;
        this.pageNum = pageNum;
    }
}
