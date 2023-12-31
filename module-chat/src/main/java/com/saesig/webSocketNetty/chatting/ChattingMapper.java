package com.saesig.webSocketNetty.chatting;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChattingMapper {
    public List<ChatMemberDto> getMemberList();

    public ChatMemberDto getMemberData(Long memberId);

    public List<ChattingRoomDto> getChattingRoomList(Long memberId);

    public List<ChatReadDto> getChatMemberData(Long chatId);

    public ChatMemberDto getTargetMemberData(@Param("chatId")Long chatId, @Param("senderId")Long senderId);

    public void insertChattingRoom(@Param("chatId")Long chatId,@Param("title")String title, @Param("memberId") Long memberId, @Param("createdByMemberId") Long createdByMemberId);

    public Long getMemberDataByAdoptId(@Param("adoptId")Long adoptId);

}
