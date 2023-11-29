package com.saesig.config;

import com.saesig.global.menu.MenuService;
import com.saesig.global.menu.ResourceItem;
import com.saesig.global.menu.ResourceNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Slf4j
@Component
public class HttpInterceptor implements HandlerInterceptor {
    private final MenuService menuService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        ResourceNode rootResourceNode = menuService.getResourceTree("/ADMIN").getRoot();
        ResourceItem resource = menuService.getResourceItemBy(rootResourceNode, requestURI, httpMethod);

        request.setAttribute("RESOURCE", resource);

        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String httpMethod = request.getMethod();

        ResourceNode rootResourceNode = menuService.getResourceTree("/ADMIN").getRoot();
        ResourceItem resource = menuService.getResourceItemBy(rootResourceNode, requestURI, httpMethod);
        // 로그이력 생성

    }
}
