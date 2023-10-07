package com.saesig.api.member.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {

    @Autowired
    private SignMapper signMapper;

    @Override
    public int signup(SignDto param) {

        // TODO 기존에 가입된 회원인지 검사

        return signMapper.signup(param);
    }

    @Override
    public int duplicate(SignDto param) {
        return signMapper.duplicate(param);
    }

    @Override
    public String findEmail(String phoneNumber) {
        return signMapper.findEmail(phoneNumber);
    }

}
