package com.saesig.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestDto {
    private String searchType;

    private String searchKeyword;

    private Integer start;

    private Integer length;

    private Integer pageNum;
}
