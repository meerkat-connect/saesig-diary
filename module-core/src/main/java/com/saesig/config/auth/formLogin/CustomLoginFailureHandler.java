package com.saesig.config.auth.formLogin;

import com.saesig.domain.member.MemberApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Slf4j
@RequiredArgsConstructor
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private final MemberApiService memberApiService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws ServletException, IOException {
        String failureMessage = "로그인에 실패하였습니다.";

        if(exception instanceof BadCredentialsException) {
            String username = request.getParameter("username");
            failureMessage = exception.getMessage();
            try{
                memberApiService.afterLoginFail(username);
            } catch(Exception ex) {
                // 로그 생성
                log.info("afterLoginFail Exception = {} ", ex.getMessage());
            }
        }

        if(exception instanceof AccountStatusException) {
            failureMessage = exception.getMessage();
        }

        setDefaultFailureUrl("/admin/login?failureMessage=" + URLEncoder.encode(failureMessage, "UTF-8"));

            super.onAuthenticationFailure(request, response, exception);
    }
}
