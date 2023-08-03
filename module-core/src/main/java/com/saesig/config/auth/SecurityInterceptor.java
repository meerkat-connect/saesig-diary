package com.saesig.config.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class SecurityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            SecurityContext emptyContext = SecurityContextHolder.createEmptyContext();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken("saesig", "saesig");
            emptyContext.setAuthentication(usernamePasswordAuthenticationToken);
            SecurityContextHolder.setContext(emptyContext);
            RequestContextHolder.currentRequestAttributes().setAttribute("SPRING_SECURITY_CONTEXT", usernamePasswordAuthenticationToken, RequestAttributes.SCOPE_SESSION);
        }

        log.info("======================================== SecurityInterceptor preHandler ========================================");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("======================================== SecurityInterceptor postHandler ========================================");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("======================================== SecurityInterceptor afterCompletion ========================================");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
