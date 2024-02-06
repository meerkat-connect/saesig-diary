package com.saesig.config.auth.formLogin;

import com.saesig.domain.member.MemberApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final MemberApiService memberService;
    private static final int MAX_INACTIVE_INTERVAL = 20;

    public CustomLoginSuccessHandler(String defaultTargetUrl, MemberApiService memberApiService) {
        this.memberService = memberApiService;
        setDefaultTargetUrl(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        try {
            String username = request.getParameter("username");
            memberService.afterLoginSuccess(username);
        } catch(Exception ex ) {
            log.info("afterLoginSuccess Exception = {} ", ex.getMessage());
            // InternalServerException 처리
        }
//        HttpSession session = request.getSession();
//        session.setMaxInactiveInterval(MAX_INACTIVE_INTERVAL);

        HttpSessionRequestCache httpSessionRequestCache = new HttpSessionRequestCache();
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        SavedRequest savedRequest = httpSessionRequestCache.getRequest(request, response);
        if(savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            redirectStrategy.sendRedirect(request,response, targetUrl);
        } else {
            setDefaultTargetUrl("/admin");
            redirectStrategy.sendRedirect(request, response, getDefaultTargetUrl());
        }
    }
}
