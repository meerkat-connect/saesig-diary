package com.saesig.webSocketNetty.chatting;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
@Data
public class ChatDataResponseDto {
    private Long chatId;
    private Long senderId;
    private Long receiverId;
    private String text;
    private LocalDateTime regDate;
    private Long isRead;
}