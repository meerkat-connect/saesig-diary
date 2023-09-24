package com.saesig.member;

import com.saesig.common.RequestDto;
import lombok.Getter;

@Getter
public class MemberRequestDto extends RequestDto {
    private String nickname;

    private String email;

    private String signupMethod;

    private String memberStatus;

    public MemberRequestDto(Integer start, Integer length, Integer pageNum, String searchType, String searchKeyword, String nickname, String email, String signupMethod, String memberStatus) {
        super(start, length, pageNum, searchType, searchKeyword);
        this.nickname = nickname;
        this.email = email;
        this.signupMethod = signupMethod;
        this.memberStatus = memberStatus;
    }
}
