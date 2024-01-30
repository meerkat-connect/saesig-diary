package com.saesig.webSocketNetty.chatting;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService{

    @Autowired
    private MongoTemplate mongoTemplate;

    private final ChattingMapper chattingMapper;

    @Override
    public List<ChatMemberDto> getMemberList() throws Exception {
        return chattingMapper.getMemberList();
    }

    private final ChatDataDao ChatDataDao;
    @Override
    public List<ChatDataResponseDto> getChatDataList(Long chatId) throws Exception {
        return ChatDataDao.findByChatId(chatId);
    }

    @Override
    public List<ChattingRoomDto> getChattingRoomList(Long memberId) throws Exception {
        return chattingMapper.getChattingRoomList(memberId);
    }

    @Override
    public Long saveChattingData(Long chatId, String text, Long memberId, Long receiverId,Long isRead,  LocalDateTime sendTime) {
        ChatDataResponseDto ChatDataResponseDto = new ChatDataResponseDto();
        ChatDataResponseDto.setChatId(chatId);
        ChatDataResponseDto.setReceiverId(receiverId);
        ChatDataResponseDto.setText(text);
        ChatDataResponseDto.setSenderId(memberId);
        ChatDataResponseDto.setRegDate(sendTime);
        ChatDataResponseDto.setIsRead(isRead);
        ChatDataDao.save(ChatDataResponseDto);
        return chatId;
    }

    @Override
    public List<ChatDataResponseDto> getLastChat(Long chatId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId)).limit(1);
        query.with(Sort.by(Sort.Direction.DESC,"regDate"));
        return mongoTemplate.find(query, ChatDataResponseDto.class);
    }

    @Override
    public void insertChattingRoom(@Param("chatId")Long chatId, @Param("title") String title ,@Param("memberId") Long memberId, @Param("createdByMemberId") Long createdByMemberId) {
        chattingMapper.insertChattingRoom(chatId, title, memberId, createdByMemberId);
    }

    @Override
    public ChatMemberDto getMemberData(Long memberId) throws Exception {
        return chattingMapper.getMemberData(memberId);
    }

    @Override
    public Long makeChattingRoom(Long memberId, Long targetId) throws Exception{
        ChatMemberDto currentMemberData = getMemberData(memberId);
        ChatMemberDto targetMemberData = getMemberData(targetId);
        Long chat = Long.parseLong(Long.toString(memberId).concat(Long.toString(targetId)).concat(Long.toString((long)(Math.random()*100000))));
        insertChattingRoom(chat,currentMemberData.getNICKNAME(),targetId,memberId);
        insertChattingRoom(chat,targetMemberData.getNICKNAME(),memberId,memberId);
        return chat;
    }

    public boolean readMessage(Long memberId, Long chatId){
        Update update = new Update();
        Query query = new Query(Criteria.where("receiverId").is(memberId)
                .and("chatId").is(chatId));
        update.set("isRead",1);
        mongoTemplate.updateMulti(query, update, "chat");
        return true;
    }

    public List<ChatReadDto> getChatMemberData(Long chatId){
        return chattingMapper.getChatMemberData(chatId);
    }

    public long getUnreadChatCnt(Long chatId, Long memberId){
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId).and("receiverId").is(memberId).and("isRead").is(0));
        return mongoTemplate.count(query, ChatDataResponseDto.class);
    }
    public ChatMemberDto getTargetMemberData(Long chatId, Long senderId){
        return chattingMapper.getTargetMemberData(chatId, senderId);
    }

    public Long getMemberDataByAdoptId(Long adoptId) throws Exception {
        return chattingMapper.getMemberDataByAdoptId(adoptId);
    }
/* api용 service 위의 값들은 로그인 생성 이후 삭제---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/
public Long makeChattingRoom2(Long memberId, Long adoptId) throws Exception{
    Long targetId = getMemberDataByAdoptId(adoptId);
    ChatMemberDto currentMemberData = getMemberData(memberId);
    ChatMemberDto targetMemberData = getMemberData(targetId);
    Long chat = Long.parseLong(Long.toString(memberId).concat(Long.toString(targetId)).concat(Long.toString((long)(Math.random()*100000))));
    insertChattingRoom(chat,currentMemberData.getNICKNAME(),targetId,memberId);
    insertChattingRoom(chat,targetMemberData.getNICKNAME(),memberId,memberId);
    return chat;
}

    public List<ChatDataResponseDto> getChatDataList2(Long chatId) throws Exception {
        return ChatDataDao.findByChatId(chatId);
    }

}
