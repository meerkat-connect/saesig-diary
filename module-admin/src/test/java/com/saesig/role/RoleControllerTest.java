package com.saesig.role;

import com.saesig.role.RoleInsertDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.saesig.SaesigDiaryApplication;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
class RoleControllerTest {
    @Autowired
    private Validator validator;

/*    @Autowired
    MockMvc mvc;

    @Test
    @DisplayName("valid 테스트")
    void validTest() throws Exception {
        // given

        MockHttpServletRequestBuilder builder = post("/202008/regist/member")
                .content("{\"name\": \"아이유\", \"age\": 9}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
//                .andExpect(status().isOk())
                .andReturn();

        String message = result.getResolvedException().getMessage();
        System.out.println("message = " + message);

// 만약 ControllerAdvice를 이용하고 있다면 메세지를 다음으로 받을 수 있다
//        String message = result.getResponse().getContentAsString(Charset.forName("UTF-8"))

        Assertions.assertThat(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(message).contains("직업은 필수입니다"
                , "직업은 필수입니다");
    }*/

    @Test
    @DisplayName("validation 테스트")
    public void validation_테스트(){
        //given
        //DTO 생성
        String name = "roleName";
        RoleInsertDto roleInsertDto = new RoleInsertDto();
        roleInsertDto.setName(name);

        //when
        Set<ConstraintViolation<RoleInsertDto>> validate = validator.validate(roleInsertDto);

        //then
        Iterator<ConstraintViolation<RoleInsertDto>> iterator = validate.iterator();
        List<String> messages = new ArrayList<>();

        while (iterator.hasNext()) {
            ConstraintViolation<RoleInsertDto> next = iterator.next();
            messages.add(next.getMessage());
            System.out.println("message = " + next.getMessage());
        }

//        Assertions.assertThat(messages).contains("20살 이후부터 가입 가능합니다" , "직업은 필수입니다");
    }

}