package com.saesig.config;

import com.saesig.config.auth.SecurityResourceService;
import com.saesig.config.auth.UrlBasedFilterInvocationSecurityMetadataSource;
import com.saesig.config.auth.formLogin.*;
import com.saesig.config.auth.oauth.CustomOAuth2LoginFailHandler;
import com.saesig.config.auth.oauth.CustomOAuth2LoginSuccessHandler;
import com.saesig.config.auth.oauth.CustomOAuth2UserService;
import com.saesig.domain.member.MemberApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
@Profile("prod")
public class SecurityConfig {
    private final SecurityResourceService securityResourceService;
    private final MemberApiService memberApiService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2LoginFailHandler customOAuth2LoginFailHandler;
    private final CustomOAuth2LoginSuccessHandler customOAuth2LoginSuccessHandler;
    private final CacheManager cacheManager;

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
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers(ENDPOINT_WHITELIST);
    }

    @Bean
    public SecurityFilterChain mainFilterChain(HttpSecurity httpSecurity) throws Exception {

        // 인증 정책
        httpSecurity
                .antMatcher("/admin/**")
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

//    @Bean
//    @Order(3)
//    public SecurityFilterChain oauthFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity
//                .csrf()
//                .disable()
//                .headers()
//                .frameOptions()
//                .disable()
//                .and()
//                .requestMatchers(matchers -> matchers.antMatchers("/**/*"))
//                .oauth2Login()
//                .userInfoEndpoint()
//                .userService(customOAuth2UserService)
//                .and()
//                .successHandler(customOAuth2LoginSuccessHandler)
//                .failureHandler(customOAuth2LoginFailHandler);
//
//        return httpSecurity.build();
//    }


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

    public UrlBasedFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlBasedFilterInvocationSecurityMetadataSource(securityResourceService, cacheManager);
    }

    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler("/error");
    }

    public CustomLoginSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler("/admin", memberApiService);
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler(memberApiService);
    }

    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    /**
     * spring security에서 제공하는 custom tag에서 다음과 같이 url을 이용해서 접근 권한을 체크할 수 있다
     * &lt;sec:authorize url="/unitedBoard/unitedBoardList.do?boardTypeIdx=1"&gt;
     * 공지사항
     * &lt;/sec:authorize&gt;
     * 위의 예는 /unitedBoard/unitedBoardList.do?boardTypeIdx=1 이 url을 접근할 수 있는 권한이 있을 경우 sec:authorize 태그로 감싸진 부분을 브라우저로 표현하게 된다
     * &lt;sec:authorize access="hasRole('MEMBER')"&gt; 같이 hasRole을 이용한 권한 체크를 url로 대신 체크하는 것이다.
     * 즉 정리하면 주어진 url에 접근가능한지의 여부에 따라 동작하는 태그 기능이라 볼 수 있는데
     * 이 기능을 수행할려면 WebInvocationPrivilegeEvaluator 인터페이스를 구현한 Bean을 등록해서 사용해야 한다
     * Spring Security에서는 기본적으로 WebInvocationPrivilegeEvaluator 인터페이스를 구현한 클래스로 DefaultWebInvocationPrivilegeEvaluator를 제공하는데
     * 이 클래스를 이용해서 등록했다
     * <p>
     * 원래 이 bean을 등록하지 않아도 Spring Security는 Java Config 방식으로 등록할때 자동으로  WebInvocationPrivilegeEvaluator 인터페이스를 구현한 클래스가
     * 만들어져 이 기능을 수행할 수 있다.
     * 그러나 이렇게 자동으로 만들어질 경우 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 이용해서 권한 접근 체크를 하지 않기 때문에
     * 원하지 않는 결과가 나오게된다
     * 그래서 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 이용해서 이 태그가 동작하게끔 하기 위해
     * 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 DefaultWebInvocationPrivilegeEvaluator 클래스 bean을 만들때 사용하고
     * 이를 configure(WebSecurity web) 메소드에서 WebSecurity 클래스의 privilegeEvaluator 메소드를 이용해 등록함으로써 이 기능을 정상적으로 동작하게끔 설정했다
     *
     * @return
     */
    @Bean
    public WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator() {
        return null;
//        return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
    }
}
