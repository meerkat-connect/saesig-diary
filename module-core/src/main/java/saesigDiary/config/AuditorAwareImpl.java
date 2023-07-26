package saesigDiary.config;

import org.springframework.data.domain.AuditorAware;
import saesigDiary.domain.member.Member;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<Member> {
    @Override
    public Optional<Member> getCurrentAuditor() {
        Member member = new Member();
        member.setId(2L);
        // TODO : OAUTH / JWT 방식 선택 후 로직 구현 해야함
        return Optional.of(member);
    }
}
