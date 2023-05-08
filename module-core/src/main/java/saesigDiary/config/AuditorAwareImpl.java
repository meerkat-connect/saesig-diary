package saesigDiary.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;


public class AuditorAwareImpl implements AuditorAware<Long> {
    @Override
    public Optional<Long> getCurrentAuditor() {
        // TODO : OAUTH / JWT 방식 선택 후 로직 구현 해야함
        return Optional.of(1L);
    }
}
