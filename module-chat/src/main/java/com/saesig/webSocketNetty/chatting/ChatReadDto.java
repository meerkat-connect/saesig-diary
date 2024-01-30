package com.saesig.webSocketNetty.chatting;

import lombok.Data;

@Data
public class ChatReadDto {
    private Long chatId;
    private Long memberId;
}