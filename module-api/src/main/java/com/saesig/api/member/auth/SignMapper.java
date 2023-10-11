package com.saesig.api.member.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {

    int signup(SignDto param);
    int duplicate(SignDto param);
    String findEmail(String mobileNumber);
    int updatePassword(SignDto param);

}
