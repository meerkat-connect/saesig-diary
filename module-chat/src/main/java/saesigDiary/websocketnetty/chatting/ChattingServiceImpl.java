package saesigDiary.websocketnetty.chatting;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
    public List<ChattingRoomDto> getChattingRoomList(int member_id) throws Exception {
        return chattingMapper.getChattingRoomList(member_id);
    }

    @Override
    public int saveChattingData(int chatId, String text, int memberId, int isRead) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(chatId);
        ChatDataDto.setText(text);
        ChatDataDto.setSender_seq(memberId);
        ChatDataDto.setReg_date(strDate);
        ChatDataDto.setIsRead(isRead);
        ChatDataDao.save(ChatDataDto);
        return chatId;
    }

    @Override
    public List<ChatDataDto> getLastChat(int chat_id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("chatId").is(chat_id)).limit(1);
        query.with(Sort.by(Sort.Direction.DESC,"_id"));
        return mongoTemplate.find(query, ChatDataDto.class);
    }

    @Override
    public void insertChattingRoom(@Param("chat_id")int chat_id, @Param("title") String title ,@Param("member_id") int member_id, @Param("created_by_member_id") int created_by_member_id) {
        chattingMapper.insertChattingRoom(chat_id, title, member_id, created_by_member_id);
    }

    @Override
    public List<ChatMemberDto> getMemberData(int member_id) throws Exception {
        return chattingMapper.getMemberData(member_id);
    }

    @Override
    public int makeChattingRoom(int member_id, int target_id) throws Exception{
        List<ChatMemberDto> currentMemberData = getMemberData(member_id);
        List<ChatMemberDto> targetMemberData = getMemberData(target_id);
        int chat = Integer.parseInt(Integer.toString(member_id).concat(Integer.toString(target_id)).concat(Integer.toString((int)(Math.random()*100000))));
        insertChattingRoom(chat,currentMemberData.get(0).getNICKNAME(),target_id,member_id);
        insertChattingRoom(chat,targetMemberData.get(0).getNICKNAME(),member_id,member_id);
        return chat;
    }
}
