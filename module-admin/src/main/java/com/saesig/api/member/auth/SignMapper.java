package com.saesig.api.member.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {

    int signup(SignDto param);
    int duplicate(SignDto param);
    SignDto findEmailByNickname(String nickname);
    SignDto findEmailBySms(String mobileNumber);
    int updatePassword(SignDto param);
    int resign(Long id);

}
