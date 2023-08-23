package com.saesig.common;

import com.saesig.util.StringUtil;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Data
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
