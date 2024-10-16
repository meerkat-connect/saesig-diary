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
    public boolean existsByNickname(String nickname) {
        return signMapper.existsByNickname(nickname) > 0 ? true : false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return signMapper.existsByEmail(email) > 0 ? true : false;
    }

    @Override
    public SignDto findEmailByNickname(String nickname) {
        SignDto signDto = signMapper.findEmailByNickname(nickname);
        if (signDto != null && signDto.getEmail() != null) {
            // 이메일 아이디 뒤에서 *** 만큼 마스킹
            signDto.setEmail(maskingEmail(signDto.getEmail()));
        }
        return signDto;
    }

    @Override
    public SignDto findEmailBySms(String mobileNumber) {
        SignDto signDto = signMapper.findEmailBySms(mobileNumber);
        if (signDto != null && signDto.getEmail() != null) {
            // 이메일 아이디 뒤에서 *** 만큼 마스킹
            signDto.setEmail(maskingEmail(signDto.getEmail()));
        }
        return signDto;
    }

    @Override
    public int updatePassword(SignDto param) {
        return signMapper.updatePassword(param);
    }

    @Override
    public int resign(Long id) {
        return signMapper.resign(id);
    }

    public String maskingEmail(String email) {
        int atIndex = email.indexOf('@');
        String maskedEmail = "";
        if (atIndex >= 0) {
            String prefix = email.substring(0, atIndex);
            String maskedPrefix = prefix.length() <= 3 ? prefix.replaceAll(".", "*") : prefix.substring(0, prefix.length() - 3) + "***";
            String domain = email.substring(atIndex);
            maskedEmail = maskedPrefix + domain;
        }
        return maskedEmail;
    }

}
