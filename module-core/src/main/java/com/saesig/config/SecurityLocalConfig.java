package com.saesig.config;

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

    @Bean
    public SecurityFilterChain localSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers(matchers -> matchers.antMatchers("/**/*"))
                .headers()
                .frameOptions()
                .disable();

        return httpSecurity.build();
    }
}
