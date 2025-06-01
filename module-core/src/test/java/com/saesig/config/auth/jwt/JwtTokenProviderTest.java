package com.saesig.config.auth.jwt;

import com.saesig.config.MybatisConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // DB연결 없이 테스트
class JwtTokenProviderTest {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @BeforeAll
    static void beforeAll() {
        System.setProperty("jasypt.encryptorKey", "saesig");
    }

    @Test
    @DisplayName("토큰생성 테스트")
    public void 토큰_생성() {
        //given
        final String username = "saesig";
        final List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");

        //when
        String token = jwtTokenProvider.createToken(username, roles);

        //then
        assertNotNull(token, "토큰이 생성되어야 합니다.");
        assertEquals(username, jwtTokenProvider.getUsername(token), "토큰에서 사용자 이름을 올바르게 가져와야 합니다.");
    }

    @Test
    @DisplayName("토큰 내 역할 목록 조회")
    public void 역할_목록_조회(){
        //given
        final String username = "saesig";
        final List<String> roles = List.of("ROLE_ADMIN", "ROLE_USER");

        //when
        String token = jwtTokenProvider.createToken(username, roles);

        //then
        assertNotNull(token, "토큰이 생성되어야 합니다.");
        assertEquals(2, jwtTokenProvider.getRoles(token).size(), "토큰에서 역할 목록의 크기가 올바르게 조회되어야 합니다.");
    }



}