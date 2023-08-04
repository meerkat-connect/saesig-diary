package com.saesig.config;

import com.saesig.config.auth.CustomAuthenticationProvider;
import com.saesig.config.auth.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomAuthenticationProvider customAuthenticationProvider;

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
    public SecurityFilterChain mainFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/admin/login").permitAll()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/login_proc")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .userDetailsService(customUserDetailsService)
                .authenticationProvider(customAuthenticationProvider);

        return httpSecurity.build();
    }
}
