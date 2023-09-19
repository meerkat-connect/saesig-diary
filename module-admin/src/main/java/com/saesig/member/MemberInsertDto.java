package com.saesig.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
public class MemberInsertDto {
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String signupMethod;

    @NotEmpty
    @NicknameDuplicate
    private String nickname;

    @NotEmpty
    private String password;
}
