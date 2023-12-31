package com.saesig.webSocketNetty.websocket;

import com.mongodb.lang.Nullable;
import com.saesig.config.auth.SessionMember;
import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class MyHttpSessionHandShakeInterceptor extends HttpSessionHandshakeInterceptor {

    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {

        HttpSession session = getSession(request);
        if (session != null) {
            if (isCopyHttpSessionId()) {
                attributes.put(HTTP_SESSION_ID_ATTR_NAME, session.getId());
            }
            Enumeration<String> names = session.getAttributeNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                if (isCopyAllAttributes() || getAttributeNames().contains(name)) {
                    attributes.put(name, session.getAttribute(name));
                }
            }
        }
        return true;
    }

    @Nullable
    private HttpSession getSession(ServerHttpRequest request) {
        ServletServerHttpRequest serverRequest = (ServletServerHttpRequest) request;
        HttpSession httpSession = serverRequest.getServletRequest().getSession();
        Object testMember = httpSession.getAttribute("testMember");
        if(testMember == null) {
            Member member = new Member();
            /*예제 데이터*/
            member.setId(1L);
            member.setNickname("서정도1");
            member.setEmail("email1@email.com");
            httpSession.setAttribute("testMember", new SessionMember(member));
        }
        return httpSession;
    }
}