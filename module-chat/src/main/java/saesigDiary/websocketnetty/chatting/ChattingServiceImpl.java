package saesigDiary.websocketnetty.chatting;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import saesigDiary.websocketnetty.chatting.ChattingDto;
import saesigDiary.websocketnetty.chatting.ChattingMapper;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService{

    private final ChattingMapper chattingMapper;

    @Override
    public List<ChattingDto> getMemberList() throws Exception {
        return chattingMapper.getMemberList();
    }
}
