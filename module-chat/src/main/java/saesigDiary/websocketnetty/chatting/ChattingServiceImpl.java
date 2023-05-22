package saesigDiary.websocketnetty.chatting;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

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
    public ChatDataSearchResponseDto getChatDataList(String title) throws Exception {
        String Regtitle = ".*" + title + ".*";
        System.out.println(Regtitle);
        List<ChatDataDto> chatDataDtoList = ChatDataDao.findBytextRegex(Regtitle);
        return new ChatDataSearchResponseDto(chatDataDtoList);
    }

    @Override
    public List<ChattingRoomDto> getChattingRoomList(int member_id) throws Exception {
        return chattingMapper.getChattingRoomList(member_id);
    }

    @Override
    public String saveChattingRoom(String name, String text) {
        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(name);
        ChatDataDto.setText(text);
        ChatDataDao.save(ChatDataDto);
        return name;
    }

    @Override
    public String getLastChat(String chat_id) {
        ChatDataDto ChatDataDto = new ChatDataDto();
        ChatDataDto.setChatId(chat_id);
        ChatDataDto.setText("hello");
        ChatDataDao.save(ChatDataDto);
        return chat_id;
    }

    @Override
    public void insertChattingRoom(@Param("chat_id")String chat_id, @Param("member_id") String member_id, @Param("created_by_member_id") String created_by_member_id) {
        chattingMapper.insertChattingRoom(chat_id, member_id, created_by_member_id);
    }

    @Override
    public List<ChatMemberDto> getMemberData(int member_id) throws Exception {
        return chattingMapper.getMemberData(member_id);
    }
}
