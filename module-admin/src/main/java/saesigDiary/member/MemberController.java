package saesigDiary.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import saesigDiary.mail.MailContorller;

@Controller
public class MemberController {

    @GetMapping({"/member", "/member/memberList.html"})
    public String example() {
        return "member/memberList";
    }

    @PostMapping("/sendMail.do")
    public void sendMail() throws Exception {
        MailContorller.Send();
    }

}
