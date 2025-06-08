package com.saesig.config;

import com.saesig.config.auth.formLogin.CustomLoginFailureHandler;
import com.saesig.config.auth.jwt.ApiAuthenticationProvider;
import com.saesig.config.auth.jwt.JwtAuthenticationFilter;
import com.saesig.config.auth.jwt.JwtTokenProvider;
import com.saesig.config.auth.oauth.CustomOAuth2LoginFailHandler;
import com.saesig.config.auth.oauth.CustomOAuth2LoginSuccessHandler;
import com.saesig.config.auth.oauth.CustomOAuth2UserService;
import com.saesig.domain.member.MemberApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Profile("local")
public class ApiSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2LoginFailHandler customOAuth2LoginFailHandler;
    private final CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;
    private final JwtTokenProvider jwtTokenProvider;
    private final ApiAuthenticationProvider apiAuthenticationProvider;
    private final AuthenticationSuccessHandler jwtLoginSuccessHandler;
    private final MemberApiService memberApiService;

    @Bean
    public SecurityFilterChain apiFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .antMatcher("/api/**")
                .authenticationManager(apiAuthenticationManager(httpSecurity))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginProcessingUrl("/api/login_proc") // optional
                        .successHandler(jwtLoginSuccessHandler)
                        .failureHandler(authenticationFailureHandler())
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint()
                        .userService(customOAuth2UserService)
                        .and()
                        .successHandler(customOAuth2LoginSuccessHandler)
                        .failureHandler(customOAuth2LoginFailHandler)
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);
//                .exceptionHandling(eh -> eh
//                        .authenticationEntryPoint(jwtAuthenticationEntryPoint())
//                );

        return httpSecurity.build();
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler(memberApiService);
    }

    @Bean
    public AuthenticationManager apiAuthenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(apiAuthenticationProvider)
                .build();
    }

    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }

}
