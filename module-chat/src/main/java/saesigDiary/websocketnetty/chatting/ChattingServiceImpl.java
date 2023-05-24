package saesigDiary.websocketnetty.chatting;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService{

    private final ChattingMapper chattingMapper;

    @Override
    public List<ChatMemberDto> getMemberList() throws Exception {
        return chattingMapper.getMemberList();
    }

    private final ChatDataDao ChatDataDao;
    @Override
    public ChatDataSearchResponseDto getChatDataList(int chatId) throws Exception {
        System.out.println(chatId);
        List<ChatDataDto> chatDataDtoList = ChatDataDao.findByChatId(chatId);
        return new ChatDataSearchResponseDto(chatDataDtoList);
    }

    @Override
    public List<ChattingRoomDto> getChattingRoomList(int member_id) throws Exception {
        return chattingMapper.getChattingRoomList(member_id);
    }

    @Override
    public int saveChattingData(int chatId, String text, int memberId) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//dd/MM/yyyy
        Date now = new Date();
        String strDate = sdfDate.format(now);

        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(chatId);
        ChatDataDto.setText(text);
        ChatDataDto.setSender_seq(memberId);
        ChatDataDto.setReg_date(strDate);
        ChatDataDao.save(ChatDataDto);
        return chatId;
    }

    @Override
    public int getLastChat(int chat_id) {
        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(chat_id);
        ChatDataDto.setText("hello");
        ChatDataDao.save(ChatDataDto);
        return chat_id;
    }

    @Override
    public void insertChattingRoom(@Param("chat_id")int chat_id, @Param("member_id") int member_id, @Param("created_by_member_id") int created_by_member_id) {
        chattingMapper.insertChattingRoom(chat_id, member_id, created_by_member_id);
    }

    @Override
    public List<ChatMemberDto> getMemberData(int member_id) throws Exception {
        return chattingMapper.getMemberData(member_id);
    }
}
