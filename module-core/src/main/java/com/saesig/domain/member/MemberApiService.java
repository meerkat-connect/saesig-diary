package com.saesig.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberApiService {
    private final QueryDslMemberRepository memberRepository;

    @Transactional
    public void afterLoginSuccess(String username) {
        Member member = memberRepository
                .findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        member.afterLoginSuccess();
    }

    @Transactional
    public void afterLoginFail(String username) {
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        member.afterLoginFail();
    }
}
