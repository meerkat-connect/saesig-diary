package com.saesig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * 사용 지양 -> HttpSecurity 구성시 ignore할 경로를 지정해주는 것이 지향됨
     * 공식 문서에서 해당 설정을 하는 경우 CSRF, XSS, Clickjacking 등에서 보호되지 않는다고 명시되어 있음
     *
     */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/static/**/*", "/templates/**", "/h2-console/**", "/example/**/*");
//    }

    @Bean
    @Order(1)
    public SecurityFilterChain ignoringSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers(matchers -> matchers.antMatchers("/static/**/*", "/templates/**", "/h2-console/**"))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().permitAll())
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable();

        return httpSecurity.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login");

        return httpSecurity.build();
    }
}
