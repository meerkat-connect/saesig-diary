package com.saesig.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestDto {
    private Integer start;

    private Integer length;

    private Integer pageNum;

    private String searchType;

    private String searchKeyword;
}
