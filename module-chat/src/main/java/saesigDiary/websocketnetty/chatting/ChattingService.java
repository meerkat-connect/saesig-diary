package saesigDiary.websocketnetty.chatting;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChattingService {

    public List<ChatMemberDto> getMemberList() throws Exception;

    public List<ChatMemberDto> getMemberData(int member_id) throws Exception;

    public ChatDataSearchResponseDto getChatDataList(int chatId) throws Exception;

    public List<ChattingRoomDto> getChattingRoomList(int member_id) throws Exception;

    public int saveChattingData(int chatId, String text, int memberId, int isRead);

    public List<ChatDataDto> getLastChat(int chatId);

    public void insertChattingRoom(int chat, String title ,int member_id, int created_by_member_id);

    public int makeChattingRoom(int member_id, int target_id) throws Exception;


}
