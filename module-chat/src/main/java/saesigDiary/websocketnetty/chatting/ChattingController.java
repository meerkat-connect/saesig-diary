package saesigDiary.websocketnetty.chatting;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import saesigDiary.websocketnetty.websocket.chatJsonData;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    @Autowired
    private ChattingService chattingService;

    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @PostMapping({"", "/chat"})
    public String getMemberList(int member_id,Model model) throws Exception {
        List<ChattingRoomDto> chattingRoomList = chattingService.getChattingRoomList(member_id);
        for (int i=0; i<chattingRoomList.size(); i++){
                List<ChatDataDto> LastChat = chattingService.getLastChat(Integer.parseInt(chattingRoomList.get(i).getChat_id()));
                if (LastChat.size() != 0){
                    chattingRoomList.get(i).setLast_msg(LastChat.get(0).getText());
                }else{
                    chattingRoomList.remove(i);
                }
        }
        model.addAttribute("chatList", chattingRoomList);
        model.addAttribute("meId", member_id);
        return "chattingList";
    }

    @GetMapping({"", "/login"})
    public String Login(Model model) { //현재 로그인이 없어 임시로 나를 지칭하는 페이지를 제작
        try {
            List<ChatMemberDto> memberList = chattingService.getMemberList();
            model.addAttribute("title", "회원목록조회");
            model.addAttribute("memberList", memberList);
        }catch (Exception e){
            model.addAttribute("name",e);
        }
        return "login";
    }

    @PostMapping("/chat/chatting")
    public String chattingRoom(int meId, int chatId, int memberId,HttpSession session, Model model) throws Exception {
        List<ChatMemberDto> currentMemberData = chattingService.getMemberData(meId);
        int chat_id;
        if (chatId == 0){
            chat_id = chattingService.makeChattingRoom(meId, memberId);
        }else{
            chat_id = chatId;
        }
        ChatDataSearchResponseDto chatDataLog = chattingService.getChatDataList(chat_id);
        model.addAttribute("chatId", chat_id);
        model.addAttribute("chatDataLog", chatDataLog);
        model.addAttribute("currentMemberData", currentMemberData);
        return "chattingRoom";
    }

    @PostMapping("/chat/getMemberList")
    public String getMemberList(Model model) throws Exception {
        Object[] Array = chattingService.getMemberList().toArray();
        Gson gson = new Gson();
        String param = gson.toJson(Array);
        model.addAttribute("msg",param);
        return "/chattingList :: #resultDiv";
    }

}
