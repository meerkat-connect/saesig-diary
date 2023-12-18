package com.saesig.webSocketNetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
public class ChatDataDto {
    private int chatId;
    private int senderId;
    private int receiverId;
    private String text;
    private LocalDateTime regDate;
    private int isRead;
}