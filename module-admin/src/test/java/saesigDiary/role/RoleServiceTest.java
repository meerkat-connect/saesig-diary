package saesigDiary.role;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.SaesigDiaryApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = SaesigDiaryApplication.class)
@ActiveProfiles("local")
class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    @DisplayName("회원 역할 등록")
    void 회원_역할_등록(){
        //given
        Long roleId = 1L;
        Long memberId = 1L;

        //when
        roleService.addCheckedMembers(1L, new Long[]{memberId});

        //then

    }

}