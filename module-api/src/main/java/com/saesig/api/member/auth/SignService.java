package com.saesig.api.member.auth;

public interface SignService {

    int signup(SignDto param);
    int duplicate(SignDto param);
    String findEmail(String mobileNumber);
    int updatePassword(SignDto param);

}
