package com.saesig.common;

import lombok.Getter;

@Getter
public class RequestDto {
    private String searchType;
    private String searchName;
    private Integer start;
    private Integer length;
    private Integer pageNum;
}
