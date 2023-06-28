package saesigDiary.websocketnetty.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatDataDao extends MongoRepository<ChatDataDto, String> {
    List<ChatDataDto> findByChatId(int chatId);
}
