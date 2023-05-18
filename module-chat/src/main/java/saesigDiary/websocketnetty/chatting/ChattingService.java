package saesigDiary.websocketnetty.chatting;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChattingService {

    public List<ChatMemberDto> getMemberList() throws Exception;

    public ChatDataSearchResponseDto getChatDataList(String title) throws Exception;

    public List<ChattingRoomDto> getChattingRoomList(int member_id) throws Exception;

    public String saveChattingRoom(String chatId, String text);

    public String getLastChat(String chatId);

    public void insertChattingRoom(String chat, String member_id, String created_by_member_id);


}
