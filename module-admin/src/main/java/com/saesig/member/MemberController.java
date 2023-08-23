package com.saesig.member;

import com.saesig.common.mybatis.DataTablesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.saesig.mail.MailContorller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberSerivce memberService;

    @GetMapping({"/member", "/member/memberList.html"})
    public String example() {
        return "member/memberList";
    }

    @GetMapping(value = "/member/selectMemberList.do")
    @ResponseBody
    public DataTablesDto selectMemberList(MemberDto mbd) throws Exception {
        DataTablesDto dtd = new DataTablesDto();

        List<MemberDto> list = memberService.selectMemberList(mbd);

        dtd.setDraw(mbd.getDraw());
        dtd.setData(list);
        dtd.setRecordsFiltered(list.get(0).getRecordsTotal());
        dtd.setRecordsTotal(list.get(0).getRecordsTotal());

        return dtd;
    }

    @PostMapping("/sendMail.do")
    public void sendMail() throws Exception {
        MailContorller.Send();
    }

}
