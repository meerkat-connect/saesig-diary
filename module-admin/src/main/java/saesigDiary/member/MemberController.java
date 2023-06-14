package saesigDiary.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import saesigDiary.mail.MailContorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MemberController {

    @Autowired
    private MemberSerivce memberService;

    @GetMapping({"/member", "/member/memberList.html"})
    public String example() {
        return "member/memberList";
    }

    @RequestMapping(value = "/member/selectMemberList.do")
    @ResponseBody
    public Map<String, Object> selectMemberList(MemberDto memberDto) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        List<MemberDto> list = memberService.selectMemberList(memberDto);

//        if (list.size() > 0) {
//            resultMap.put("totalCount", (result.get(0).getTotalCount()));
//        } else {
//            resultMap.put("totalCount", 0);
//        }
        resultMap.put("list", list);

        return resultMap;
    }

    @PostMapping("/sendMail.do")
    public void sendMail() throws Exception {
        MailContorller.Send();
    }

}
