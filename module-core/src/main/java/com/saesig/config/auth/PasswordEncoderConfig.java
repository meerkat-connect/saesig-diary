package com.saesig.config.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;



/*
Spring Security 5.0 암호 변환 정책 변경 - 현대화된 비밀번호 인코딩

PasswordEncoder - 패스워드 단방향 변환
PasswordEncoderFactories.createDelegatingPasswordEncoder() : 패스워드의 접두사로 {id}를 붙여 PasswordEncoder 유형이 정의됨
default로 {bcrypt}가 붙음
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
