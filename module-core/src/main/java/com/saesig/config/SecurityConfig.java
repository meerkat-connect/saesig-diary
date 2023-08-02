package com.saesig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
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
    *
    * WebSecurity 요청을 무시하도록 구성하는 대신에 바로 위에서 HttpSecurity를 구성할 때 authorizeHttpRequests에서 그 경로를 permitAll 하는것이 좋다.
    * 공식문서에서는 스프링 시큐리티에서 해당 경로를 무시하는 것이기 때문에 CSRF, XSS, Clickjacking 등에서 보호하지 않는다.
    * 따라서 이 취약점으로부터 보호하려면 HttpSecurity에서 permitAll을 하자.
    * */
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring()
//                .antMatchers("/static/**/*", "/templates/**", "/h2-console/**", "/example/**/*");
//    }

    @Bean
    @Order(1)
    public SecurityFilterChain ignoringSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers((matchers) -> matchers.antMatchers("/static/**/*", "/templates/**", "/h2-console/**", "/**/*"))
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll())
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
                .authorizeHttpRequests((auth) -> auth.anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
