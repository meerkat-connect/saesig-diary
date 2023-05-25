package saesigDiary.websocketnetty.chatting;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ChattingMapper {
    public List<ChatMemberDto> getMemberList();

    public List<ChatMemberDto> getMemberData(int member_id);

    public List<ChattingRoomDto> getChattingRoomList(int member_id);

    public void insertChattingRoom(@Param("chat_id")int chat_id,@Param("title")String title, @Param("member_id") int member_id, @Param("created_by_member_id") int created_by_member_id);

}
