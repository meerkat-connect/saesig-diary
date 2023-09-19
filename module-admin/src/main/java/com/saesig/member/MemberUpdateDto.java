package com.saesig.member;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberUpdateDto {
    @NotEmpty
    @NicknameDuplicate
    private String nickname;

    @NotEmpty
    private String status;
}
