package saesigDiary.websocketnetty.chatting;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChattingMapper {
    public List<ChatMemberDto> getMemberList();

    public List<ChattingRoomDto> getChattingRoomList(int member_id);

    public void insertChattingRoom(@Param("chat_id")String chat_id, @Param("member_id") String member_id, @Param("created_by_member_id") String created_by_member_id);

}
