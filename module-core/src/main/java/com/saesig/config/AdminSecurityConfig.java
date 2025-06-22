package com.saesig.config;

import com.saesig.config.auth.SecurityResourceService;
import com.saesig.config.auth.UrlBasedFilterInvocationSecurityMetadataSource;
import com.saesig.config.auth.formLogin.*;
import com.saesig.domain.member.MemberAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Profile("local")
public class AdminSecurityConfig {
    private final SecurityResourceService securityResourceService;
    private final MemberAdminService memberAdminService;
    private final CacheManager cacheManager;
    private final CustomAuthenticationProvider customAuthenticationProvider;

    private static final String LOGIN_PAGE_URI = "/admin/login";
    private static final String[] ENDPOINT_WHITELIST = new String[]{
            "/static/**/*",
            "/error/**/*",
            "/templates/**",
            "/h2-console/**",
            "/**/*.js",
            "/img/favicon.ico",
            "/**/*.css",
            "/css/**/*",
            "/fonts/**/*",
            "/images/**/*",
            "/files/**/*",
            LOGIN_PAGE_URI,
            "/error",
            "/favicon.ico"
    };

    @Bean
    public SecurityFilterChain mainFilterChain(HttpSecurity httpSecurity) throws Exception {
        // 인증 정책
        httpSecurity
                .antMatcher("/admin/**")
                .authenticationManager(adminAuthenticationManager(httpSecurity))
                .formLogin()
                .loginPage(LOGIN_PAGE_URI)
                .loginProcessingUrl("/login_proc")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())

                .and()

                .logout()
                .logoutUrl("/logout") // default: POST 방식
                .logoutSuccessUrl(LOGIN_PAGE_URI)
                .logoutSuccessHandler(logoutSuccessHandler())
                .deleteCookies("JSESSIONID", "remember-me");

        // 세션 정책 설정
        httpSecurity.sessionManagement()
//                .invalidSessionUrl(LOGIN_PAGE_URI)
                .maximumSessions(1) // 최대 허용 가능 세션 수
//                .expiredUrl(LOGIN_PAGE_URI)
                .maxSessionsPreventsLogin(true)  // 동시 로그인 차단, false: 기존 세션 만료 (default)
                .and()
                .sessionFixation()
                .changeSessionId(); // 세션고정보호 (default)

        // 인가 정책
        httpSecurity
                .addFilterBefore(customFilterSecurityInterceptor(), FilterSecurityInterceptor.class);

        // 예외 정책
        httpSecurity
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        return httpSecurity.build();
    }

    public PermitAllFilter customFilterSecurityInterceptor() {
        PermitAllFilter permitAllFilter = new PermitAllFilter(ENDPOINT_WHITELIST);
        permitAllFilter.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        permitAllFilter.setAccessDecisionManager(affirmativeBased());
        permitAllFilter.setRejectPublicInvocations(true);

        return permitAllFilter;
    }

    public AffirmativeBased affirmativeBased() {
        return new AffirmativeBased(Collections.singletonList(new RoleVoter()));
    }


    @Bean
    public AuthenticationManager adminAuthenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenticationProvider)
                .build();
    }

    @Bean
    public WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator() {
        return null;
//        return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
    }

    public CustomLoginSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler("/admin", memberAdminService);
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler(memberAdminService);
    }

    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler("/error");
    }

    public UrlBasedFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlBasedFilterInvocationSecurityMetadataSource(securityResourceService, cacheManager);
    }

}
