package com.saesig.webSocketNetty.chatting;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatDataDao extends MongoRepository<ChatDataResponseDto, String> {
    List<ChatDataResponseDto> findByChatId(Long chatId);
}
