package com.saesig.webSocketNetty.chatting;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChattingService {

    public List<ChatMemberDto> getMemberList() throws Exception;

    public ChatMemberDto getMemberData(int memberId) throws Exception;

    public ChatDataSearchResponseDto getChatDataList(int chatId) throws Exception;

    public List<ChattingRoomDto> getChattingRoomList(int memberId) throws Exception;

    public int saveChattingData(int chatId, String text, int memberId, int receiverId, int isRead, LocalDateTime sendTime);

    public List<ChatDataDto> getLastChat(int chatId);

    public void insertChattingRoom(int chat, String title ,int memberId, int createdByMemberId);

    public int makeChattingRoom(int memberId, int target_id) throws Exception;

    public boolean readMessage(int memberId, int chatId);

    public List<ChatReadDto> getChatMemberData(int chatId);

    public long getUnreadChatCnt(int chatId, int receiverId);

    public ChatMemberDto getTargetMemberData(int chatId, int senderId);
}
