package saesigDiary.config;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@ContextConfiguration(classes = {SecurityConfig.class})
@Slf4j
@SpringBootTest
class SecurityConfigTest {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @DisplayName("BCryptPassword 패스워드 인코딩 테스트 ")
    @Test
    void 패스워드_인코딩() {
        //when
        String password = "saesigDiary";

        //given
        String encodedPassword = bCryptPasswordEncoder.encode(password);

        //then
        log.info("평문 패스워드 = {} ", password);
        log.info("암호화된 패스워드 = {}", encodedPassword);

        assertThat(bCryptPasswordEncoder.matches(password, encodedPassword)).isTrue();
    }

}