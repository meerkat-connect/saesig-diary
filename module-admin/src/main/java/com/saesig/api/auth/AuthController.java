package com.saesig.api.auth;

import com.saesig.config.auth.jwt.JwtTokenProvider;
import com.saesig.config.auth.jwt.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {
    private final JwtTokenProvider jwtTokenProvider;;

    @GetMapping("/login")
    public String login(){
        log.debug("/api/login");
        return null;
    }

    @PostMapping("/token/refresh")
    public TokenResponseDto updateToken(String username) {
        return jwtTokenProvider.updateToken(username);
    }
}
