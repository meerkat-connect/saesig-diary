package com.saesig.config.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String exceptionMessage = "login failure";

        if(exception instanceof BadCredentialsException) {
            exceptionMessage = "invalid password.";
        }

        setDefaultFailureUrl("/admin/login?error=true&exceptionMessage=" + exceptionMessage);

        super.onAuthenticationFailure(request, response, exception);
    }
}
