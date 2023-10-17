package com.saesig.api.member.auth;

public interface SignService {

    int signup(SignDto param);
    int duplicate(SignDto param);
    SignDto findEmailByNickname(String nickname);
    SignDto findEmailBySms(String mobileNumber);
    int updatePassword(SignDto param);

}
