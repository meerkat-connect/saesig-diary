package com.saesig.config.auth;

import com.saesig.domain.member.Member;
import com.saesig.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Slf4j
@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        매개변수 전달시 매개변수명은 닉네임의 경우 username, 비밀번호의 경우 password로 넘겨줘야 하며, content-type은 application/x-www-form-urlencoded로 전달되어야함
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("회원을 찾을 수 없습니다."));
        log.info("member : {} " , member.toString());

        return new CustomUserDetails(member);
    }
}
