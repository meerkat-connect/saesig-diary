package com.saesig.config.auth;

import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Component
public class SessionMemberArgumentResolver implements HandlerMethodArgumentResolver {
    private final HttpSession httpSession;
    private final MemberRepository memberRepository;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginMemberAnnotation = parameter.getParameterAnnotation(LoginMember.class) != null;
        boolean isSessionMemberClass = SessionMember.class.equals(parameter.getParameterType());

        return isLoginMemberAnnotation && isSessionMemberClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //예제용 더미 데이터
        Object testMember = httpSession.getAttribute("testMember");
        if(testMember == null) {
            Member member = memberRepository.findById(1L).get();
            httpSession.setAttribute("testMember", new SessionMember(member));
        }

        return httpSession.getAttribute("testMember");
    }
}
