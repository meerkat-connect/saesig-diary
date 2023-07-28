package com.saesig.webSocketNetty.chatting;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChattingMapper {
    public List<ChatMemberDto> getMemberList();

    public ChatMemberDto getMemberData(int memberId);

    public List<ChattingRoomDto> getChattingRoomList(int memberId);

    public List<ChatReadDto> getChatMemberData(int chatId);

    public ChatMemberDto getTargetMemberData(@Param("chatId")int chatId, @Param("senderId")int senderId);

    public void insertChattingRoom(@Param("chatId")int chatId,@Param("title")String title, @Param("memberId") int memberId, @Param("createdByMemberId") int createdByMemberId);

}
