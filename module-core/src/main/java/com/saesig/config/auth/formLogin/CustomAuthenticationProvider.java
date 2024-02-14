package com.saesig.config.auth.formLogin;

import com.saesig.config.auth.SessionMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Slf4j
@RequiredArgsConstructor
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        log.info("authentication = {} ", authentication);
        String name = authentication.getName();
        String password = String.valueOf(authentication.getCredentials());

        CustomUserDetails user = (CustomUserDetails) customUserDetailsService.loadUserByUsername(name);

        if(user.isDormant()) {
            throw new DisabledException("휴면계정 입니다.");
        }

        if(user.isLeaved()) {
            throw new DisabledException("탈퇴계정 입니다.");
        }

        if(user.isAccountLocked()) {
            throw new LockedException("잠김계정 입니다.");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
        }

        httpSession.setAttribute("member", new SessionMember(user.getMember()));

        /*
            TODO
            1. 로그인 실패 에러 세션 지우기
            2. 로그인 성공시 실패 카운터 초기화
         */

        return new UsernamePasswordAuthenticationToken(user.getMember().getId(), null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
