package com.saesig.api.member.auth;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SignMapper {

    public int signup(SignDto param);
    public int duplicate(SignDto param);
    public String findEmail(String phoneNumber);

}
