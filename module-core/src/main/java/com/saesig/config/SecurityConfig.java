package com.saesig.config;

import com.saesig.config.auth.*;
import com.saesig.config.auth.formLogin.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
@Profile("!local")
public class SecurityConfig {
    private final SecurityResourceService securityResourceService;

    @Bean
    @Order(1)
    public SecurityFilterChain ignoringSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .requestMatchers(matchers -> matchers.antMatchers(
                        "/static/**/*",
                        "/templates/**",
                        "/h2-console/**",
                        "/**/*.js",
                        "/**/*.css",
                        "/css/**/*",
                        "/fonts/**/*",
                        "/images/**/*",
                        "/files/**/*",
                        "/favicon.ico"))
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
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/admin/login")
                .loginProcessingUrl("/login_proc")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutSuccessUrl("/admin/login")
                .logoutSuccessHandler(logoutSuccessHandler())
                .and()
                .anonymous().authorities("ANONYMOUS")
                .and()
                .addFilterBefore(filterSecurityInterceptor(), FilterSecurityInterceptor.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .authenticationEntryPoint(new CustomEntryPoint());
//                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/admin/login"));
        return httpSecurity.build();
    }

    @Bean
    public AffirmativeBased affirmativeBased() {
        return new AffirmativeBased(Collections.singletonList(new RoleVoter()));
    }

    @Bean
    public UrlBasedFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource() {
        return new UrlBasedFilterInvocationSecurityMetadataSource(urlResourceFactoryBean().getObject());
    }

    @Bean
    public UrlResourceFactoryBean urlResourceFactoryBean() {
        return new UrlResourceFactoryBean(securityResourceService);
    }

    @Bean
    public FilterSecurityInterceptor filterSecurityInterceptor() {
        FilterSecurityInterceptor filterSecurityInterceptor = new FilterSecurityInterceptor();
        filterSecurityInterceptor.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource());
        filterSecurityInterceptor.setAccessDecisionManager(affirmativeBased());

        return filterSecurityInterceptor;
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler("/admin/403");
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new CustomLoginSuccessHandler("/admin");
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomLoginFailureHandler();
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new CustomLogoutSuccessHandler();
    }

    /**
     * spring security에서 제공하는 custom tag에서 다음과 같이 url을 이용해서 접근 권한을 체크할 수 있다
     * &lt;sec:authorize url="/unitedBoard/unitedBoardList.do?boardTypeIdx=1"&gt;
     *			공지사항
     * &lt;/sec:authorize&gt;
     * 위의 예는 /unitedBoard/unitedBoardList.do?boardTypeIdx=1 이 url을 접근할 수 있는 권한이 있을 경우 sec:authorize 태그로 감싸진 부분을 브라우저로 표현하게 된다
     * &lt;sec:authorize access="hasRole('MEMBER')"&gt; 같이 hasRole을 이용한 권한 체크를 url로 대신 체크하는 것이다.
     * 즉 정리하면 주어진 url에 접근가능한지의 여부에 따라 동작하는 태그 기능이라 볼 수 있는데
     * 이 기능을 수행할려면 WebInvocationPrivilegeEvaluator 인터페이스를 구현한 Bean을 등록해서 사용해야 한다
     * Spring Security에서는 기본적으로 WebInvocationPrivilegeEvaluator 인터페이스를 구현한 클래스로 DefaultWebInvocationPrivilegeEvaluator를 제공하는데
     * 이 클래스를 이용해서 등록했다
     *
     * 원래 이 bean을 등록하지 않아도 Spring Security는 Java Config 방식으로 등록할때 자동으로  WebInvocationPrivilegeEvaluator 인터페이스를 구현한 클래스가
     * 만들어져 이 기능을 수행할 수 있다.
     * 그러나 이렇게 자동으로 만들어질 경우 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 이용해서 권한 접근 체크를 하지 않기 때문에
     * 원하지 않는 결과가 나오게된다
     * 그래서 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 이용해서 이 태그가 동작하게끔 하기 위해
     * 우리가 커스터마이징한 FilterSecurityInterceptor 클래스를 DefaultWebInvocationPrivilegeEvaluator 클래스 bean을 만들때 사용하고
     * 이를 configure(WebSecurity web) 메소드에서 WebSecurity 클래스의 privilegeEvaluator 메소드를 이용해 등록함으로써 이 기능을 정상적으로 동작하게끔 설정했다
     * @return
     */
    @Bean
    public WebInvocationPrivilegeEvaluator webInvocationPrivilegeEvaluator(){
        return null;
//        return new DefaultWebInvocationPrivilegeEvaluator(filterSecurityInterceptor);
    }
}
