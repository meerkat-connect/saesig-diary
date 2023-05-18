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
            model.addAttribute("name",e);
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
    public String chattingRoom(@RequestBody String requestData, HttpSession session, Model model){
        String[] requestDataArray = requestData.split("&");
        String targetId = requestDataArray[0].split("=")[1];
        String chat_id = requestDataArray[1].split("=")[1];
        String meId = requestDataArray[2].split("=")[1];
        if (chat_id.equals("null")){
            String chat = meId.concat(targetId).concat(Integer.toString((int)(Math.random()*100000)));
            chattingService.insertChattingRoom(chat,targetId,meId);
            chattingService.insertChattingRoom(chat,meId,meId);
        }
        model.addAttribute("name", targetId);
        return "chattingRoom";
    }

    @GetMapping("/chat/chatdatatest/{title}")
    public ChatDataSearchResponseDto findById(@PathVariable String title) throws Exception {
        System.out.println("title");
//        return null;
        return (ChatDataSearchResponseDto) chattingService.getChatDataList(title);
    }

    @GetMapping("/chat/save/{name}&{text}")
    public ChatDataSearchResponseDto findById(@PathVariable String name, @PathVariable String text) throws Exception {
        String K = chattingService.saveChattingRoom(name,text);
//        return null;
        return null;
    }
}
