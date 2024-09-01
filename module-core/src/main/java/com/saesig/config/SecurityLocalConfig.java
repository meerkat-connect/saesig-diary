package com.saesig.config;

import com.saesig.config.auth.oauth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Profile({"local", "dev"})
public class SecurityLocalConfig {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers(matchers -> matchers.antMatchers("/**/*"))
                .headers()
                .frameOptions()
                .disable()
                .and()
                .oauth2Login()
                .defaultSuccessUrl("/") // 기본값이 / 임
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
        return httpSecurity.build();
    }
}
