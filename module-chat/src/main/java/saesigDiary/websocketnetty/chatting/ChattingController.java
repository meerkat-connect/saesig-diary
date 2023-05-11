package saesigDiary.websocketnetty.chatting;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    @Autowired
    private ChattingService chattingService;

    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @GetMapping({"", "/chat"})
    public String getMemberList(Model model) {
        try {
            List<ChattingDto> memberList = chattingService.getMemberList();
            model.addAttribute("title", "회원목록조회");
            model.addAttribute("memberList", memberList);
        }catch (Exception e){
            model.addAttribute("name",e);
        }
        return "chattingRoom";
    }

    @GetMapping("/chat/{id}")
    public String chattingRoom(@PathVariable String id, HttpSession session, Model model){
        if(id.equals("guest")){
            model.addAttribute("name", "guest");
        }else if(id.equals("master")){
            model.addAttribute("name", "master");
        }else {
            return "error";
        }
        return "chattingRoom";
    }
}
