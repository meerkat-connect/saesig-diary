package com.saesig.webSocketNetty.websocket;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ChatUserJsonData {
        private String type;
        private String text;
        private Long memberId;
        private Long receiverId;
        private LocalDateTime sendTime;
}