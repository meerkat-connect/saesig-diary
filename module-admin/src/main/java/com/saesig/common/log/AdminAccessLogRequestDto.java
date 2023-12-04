package com.saesig.common.log;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class AdminAccessLogRequestDto extends RequestDto {
    public AdminAccessLogRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword) {
        super(start, length, pageNum, searchType, searchKeyword);
    }
}
