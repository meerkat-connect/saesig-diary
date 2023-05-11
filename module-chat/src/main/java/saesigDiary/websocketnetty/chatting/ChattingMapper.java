package saesigDiary.websocketnetty.chatting;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface ChattingMapper {
    public List<ChattingDto> getMemberList();

}
