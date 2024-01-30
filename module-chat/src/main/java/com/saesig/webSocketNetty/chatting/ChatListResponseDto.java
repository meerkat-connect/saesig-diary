package com.saesig.webSocketNetty.chatting;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatListResponseDto {
    Long id ;
    String avatar ;
    String username ;
    String latestContent ;
    LocalDateTime latestTimestamp ;
    Long isRead ;
    Long isReserved ;
}
