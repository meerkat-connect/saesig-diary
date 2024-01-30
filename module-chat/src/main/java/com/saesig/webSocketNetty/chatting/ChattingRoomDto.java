package com.saesig.webSocketNetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "chat")
@Getter
@Setter
@NoArgsConstructor
public class ChattingRoomDto {

    @Id
    private Long id;
    private String title;
    private Long chatId;
    private String createdAt;
    private String lastMsg;
    private Long unreadCnt;
}