package com.saesig.config.auth.formLogin;

import com.saesig.domain.member.MemberApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final MemberApiService memberApiService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        String failureMessage = "login failure";

        if(exception instanceof BadCredentialsException) {
            String username = request.getParameter("username");
            failureMessage = "invalid password.";
            try{
                memberApiService.afterLoginFail(username);
            } catch(Exception ex) {
                // 로그 생성
                log.info("afterLoginFail Exception = {} ", ex.getMessage());
            }
        }

        setDefaultFailureUrl("/admin/login?failureMessage=" + failureMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
