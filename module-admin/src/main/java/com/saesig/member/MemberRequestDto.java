package com.saesig.member;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class MemberRequestDto extends RequestDto {

    public MemberRequestDto(Integer start, Integer length, Integer pageNum) {
        super(start, length, pageNum);
    }
}
