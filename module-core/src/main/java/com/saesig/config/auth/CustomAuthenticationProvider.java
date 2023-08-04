package com.saesig.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
        }

        httpSession.setAttribute("user", new SessionMember(user.getMember()));

        return new UsernamePasswordAuthenticationToken(user.getMember(), null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
