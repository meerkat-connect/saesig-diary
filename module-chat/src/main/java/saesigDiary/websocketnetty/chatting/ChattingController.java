package saesigDiary.websocketnetty.chatting;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getMemberList(int member_id,Model model) {
        try {
            List<ChattingRoomDto> chattingRoomList = chattingService.getChattingRoomList(member_id);
            model.addAttribute("title", "회원목록조회");
            for (int i=0; i<chattingRoomList.size(); i++){
                if (chattingRoomList.get(i).getChat_id() == null){
                    chattingRoomList.get(i).setLast_msg("make new Chatting room");
                }else{
                    chattingRoomList.get(i).setLast_msg(chattingRoomList.get(i).getChat_id());
                }
            }
            model.addAttribute("memberList", chattingRoomList);
            model.addAttribute("meId", member_id);
        }catch (Exception e){
        }
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
    public String chattingRoom(@RequestBody String requestData, HttpSession session, Model model) throws Exception {
        String[] requestDataArray = requestData.split("&");
        int targetId = Integer.parseInt(requestDataArray[0].split("=")[1]);
        int meId = Integer.parseInt(requestDataArray[2].split("=")[1]);
        String chatIdStr = requestDataArray[1].split("=")[1];
        if (chatIdStr.equals("null")){
            int chat = Integer.parseInt(Integer.toString(meId).concat(Integer.toString(targetId)).concat(Integer.toString((int)(Math.random()*100000))));
            chattingService.insertChattingRoom(chat,targetId,meId);
            chattingService.insertChattingRoom(chat,meId,meId);
            model.addAttribute("chatId", chat);
            ChatDataSearchResponseDto chatDataLog = chattingService.getChatDataList(chat);
            model.addAttribute("chatDataLog", chatDataLog);
        }else{
            int chat_id = Integer.parseInt(requestDataArray[1].split("=")[1]);
            ChatDataSearchResponseDto chatDataLog = chattingService.getChatDataList(chat_id);
            model.addAttribute("chatId", chat_id);
            model.addAttribute("chatDataLog", chatDataLog);
        }
        List<ChatMemberDto> targetMemberData = chattingService.getMemberData(targetId);
        List<ChatMemberDto> currentMemberData = chattingService.getMemberData(meId);
        model.addAttribute("currentMemberData", currentMemberData);
        model.addAttribute("targetMemberData", targetMemberData);
        return "chattingRoom";
    }
}
