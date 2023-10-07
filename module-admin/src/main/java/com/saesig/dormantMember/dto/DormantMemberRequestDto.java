package com.saesig.dormantMember.dto;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class DormantMemberRequestDto extends RequestDto {
    public DormantMemberRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword) {
        super(start, length, pageNum, searchType, searchKeyword);
    }
}
