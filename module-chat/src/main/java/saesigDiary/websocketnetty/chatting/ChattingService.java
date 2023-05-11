package saesigDiary.websocketnetty.chatting;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ChattingService {

    public List<ChattingDto> getMemberList() throws Exception;
}
