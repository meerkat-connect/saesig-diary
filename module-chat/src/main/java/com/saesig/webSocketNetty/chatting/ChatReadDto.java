package com.saesig.webSocketNetty.chatting;

import lombok.Data;

@Data
public class ChatReadDto {
    private int chatId;
    private int memberId;
}