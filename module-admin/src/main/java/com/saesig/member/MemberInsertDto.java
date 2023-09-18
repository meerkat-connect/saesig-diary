package com.saesig.member;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
public class MemberInsertDto {
    @NotEmpty
    private String email;

    @NotEmpty
    private String signupMethod;

    @NotEmpty
    private String nickname;

    @NotEmpty
    private String password;

    @NotEmpty
    private String passwordCheck;
}
