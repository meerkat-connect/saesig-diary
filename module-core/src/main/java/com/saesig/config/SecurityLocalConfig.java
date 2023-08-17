package com.saesig.config;

import com.saesig.config.auth.oauth.CustomOAuth2LoginFailHandler;
import com.saesig.config.auth.oauth.CustomOAuth2LoginSuccessHandler;
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
@Profile("local")
public class SecurityLocalConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2LoginFailHandler customOAuth2LoginFailHandler;
    private final CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;

    @Bean
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .requestMatchers(matchers -> matchers.antMatchers("/**/*"))
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(customOAuth2LoginSuccessHandler)
                .failureHandler(customOAuth2LoginFailHandler);

        return httpSecurity.build();
    }
}
