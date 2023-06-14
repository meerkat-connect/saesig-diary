package saesigDiary.websocketnetty.chatting;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

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
    public ChatDataSearchResponseDto getChatDataList(int chatId) throws Exception {
        List<ChatDataDto> chatDataDtoList = ChatDataDao.findByChatId(chatId);
        return new ChatDataSearchResponseDto(chatDataDtoList);
    }

    @Override
    public List<ChattingRoomDto> getChattingRoomList(int memberId) throws Exception {
        return chattingMapper.getChattingRoomList(memberId);
    }

    @Override
    public int saveChattingData(int chatId, String text, int memberId, int receiverId,int isRead) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(chatId);
        ChatDataDto.setReceiverId(receiverId);
        ChatDataDto.setText(text);
        ChatDataDto.setSenderId(memberId);
        ChatDataDto.setRegDate(strDate);
        ChatDataDto.setIsRead(isRead);
        ChatDataDao.save(ChatDataDto);
        return chatId;
    }

    @Override
    public List<ChatDataDto> getLastChat(int chatId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId)).limit(1);
        query.with(Sort.by(Sort.Direction.DESC,"regDate"));
        return mongoTemplate.find(query, ChatDataDto.class);
    }

    @Override
    public void insertChattingRoom(@Param("chatId")int chatId, @Param("title") String title ,@Param("memberId") int memberId, @Param("createdByMemberId") int createdByMemberId) {
        chattingMapper.insertChattingRoom(chatId, title, memberId, createdByMemberId);
    }

    @Override
    public ChatMemberDto getMemberData(int memberId) throws Exception {
        return chattingMapper.getMemberData(memberId);
    }

    @Override
    public int makeChattingRoom(int memberId, int target_id) throws Exception{
        ChatMemberDto currentMemberData = getMemberData(memberId);
        ChatMemberDto targetMemberData = getMemberData(target_id);
        int chat = Integer.parseInt(Integer.toString(memberId).concat(Integer.toString(target_id)).concat(Integer.toString((int)(Math.random()*100000))));
        insertChattingRoom(chat,currentMemberData.getNICKNAME(),target_id,memberId);
        insertChattingRoom(chat,targetMemberData.getNICKNAME(),memberId,memberId);
        return chat;
    }

    public boolean readMessage(int memberId, int chatId){
        Update update = new Update();
        Query query = new Query(Criteria.where("receiverId").is(memberId)
                .and("chatId").is(chatId));
        update.set("isRead",1);
        mongoTemplate.updateMulti(query, update, "chat");
        return true;
    }

    public List<ChatReadDto> getChatMemberData(int chatId){
        return chattingMapper.getChatMemberData(chatId);
    }

    public long getUnreadChatCnt(int chatId, int memberId){
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chatId).and("receiverId").is(memberId).and("isRead").is(0));
        return mongoTemplate.count(query, ChatDataDto.class);
    }
}
