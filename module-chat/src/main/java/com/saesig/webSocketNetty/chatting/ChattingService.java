package com.saesig.webSocketNetty.chatting;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChattingService {

    public List<ChatMemberDto> getMemberList() throws Exception;

    public ChatMemberDto getMemberData(Long memberId) throws Exception;

    public List<ChatDataResponseDto> getChatDataList(Long chatId) throws Exception;

    public List<ChattingRoomDto> getChattingRoomList(Long memberId) throws Exception;

    public Long saveChattingData(Long chatId, String text, Long memberId, Long receiverId, Long isRead, LocalDateTime sendTime);

    public List<ChatDataResponseDto> getLastChat(Long chatId);

    public void insertChattingRoom(Long chat, String title ,Long memberId, Long createdByMemberId);

    public Long makeChattingRoom(Long memberId, Long target_id) throws Exception;

    public boolean readMessage(Long memberId, Long chatId);

    public List<ChatReadDto> getChatMemberData(Long chatId);

    public long getUnreadChatCnt(Long chatId, Long receiverId);

    public ChatMemberDto getTargetMemberData(Long chatId, Long senderId);
}
