package com.saesig.config;


import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Component
public class MdcFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            // 요청 ID 생성 (예: UUID)
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);

            // 추가적으로 사용자 정보 등도 넣을 수 있음
            String userId = request.getHeader("X-User-Id"); // 예: 헤더에서 사용자 ID 가져오기
            if (userId != null) {
                MDC.put("userId", userId);
            }

            filterChain.doFilter(request, response); // 다음 필터로 요청 전달
        } finally {
            // 요청 처리 후 MDC 데이터 제거 (메모리 누수 방지)
            MDC.clear();
        }
    }
}
