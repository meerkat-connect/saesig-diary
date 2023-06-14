package saesigDiary.websocketnetty.chatting;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import saesigDiary.websocketnetty.websocket.chatJsonData;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ChattingController {

    @Autowired
    private ChattingService chattingService;
    private TextWebSocketHandler textWebSocketHandler;

    public ChattingController(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @PostMapping({"", "/chat"})
    public String getMemberList(int memberId,Model model) throws Exception {
        List<ChattingRoomDto> chattingRoomList = chattingService.getChattingRoomList(memberId);
        for (int i= (chattingRoomList.size()-1); i > -1 ; i--){
                List<ChatDataDto> LastChat = chattingService.getLastChat(Integer.parseInt(chattingRoomList.get(i).getChatId()));
                long UnreadChat = chattingService.getUnreadChatCnt(Integer.parseInt(chattingRoomList.get(i).getChatId()),memberId);
                if (LastChat.size() != 0){
                    chattingRoomList.get(i).setLastMsg(LastChat.get(0).getText());
                    chattingRoomList.get(i).setUnreadCnt(UnreadChat);
                }else{
                    chattingRoomList.remove(i);
                }
        }
        model.addAttribute("chatList", chattingRoomList);
        model.addAttribute("userId", memberId);
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
    public String chattingRoom(int userId, int chatId, int memberId,HttpSession session, Model model) throws Exception {
        ChatMemberDto currentMemberData = chattingService.getMemberData(userId);
        ChatMemberDto targetMemberData = chattingService.getMemberData(memberId);
        int chatIdValue;
        if (chatId == 0){
            chatIdValue = chattingService.makeChattingRoom(userId, memberId);

        }else{
            chatIdValue = chatId;
        }
        ChatDataSearchResponseDto chatDataLog = chattingService.getChatDataList(chatIdValue);
        model.addAttribute("chatId", chatIdValue);
        model.addAttribute("chatDataLog", chatDataLog);
        model.addAttribute("currentMemberData", currentMemberData);
        model.addAttribute("targetMemberData", targetMemberData);
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
    @PostMapping("/chat/getMemberData")
    @ResponseBody
    public Map<String, Object> getMemberData(@RequestParam Map<String, Object> data, Model model) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        Integer Id = Integer.parseInt(data.get("memberId").toString());
        ChatMemberDto memberData = chattingService.getMemberData(Id);

        resultMap.put("memberData", memberData);
        return resultMap;
    }
}
