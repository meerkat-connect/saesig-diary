package com.saesig.member;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import com.saesig.config.MybatisConfig;

import static org.assertj.core.api.Assertions.assertThat;

@MybatisTest
@ContextConfiguration(classes = {MybatisConfig.class})
class MemberMapperTest {
    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("이메일로 회원 검색")
    public void 이메일로_회원_검색() throws Exception {
        //given
        String email = "wonjjong.dev@gmail.com";

        //when
        MemberDto memberByEmail = memberMapper.findByEmail(email);

        //then
        assertThat(memberByEmail.getEmail()).isEqualTo("wonjjong.dev@gmail.com");
    }
}