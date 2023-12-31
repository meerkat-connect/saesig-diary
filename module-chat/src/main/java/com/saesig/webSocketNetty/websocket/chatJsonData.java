package com.saesig.webSocketNetty.websocket;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class chatJsonData {
    private String type;
    private String text;
        private Long chatId;

        private Long memberId;

        private Long receiverId;
        private LocalDateTime sendTime;

        public String getText() {
            return text;
        }

        public String getType() {
        return type;
    }
        public Long getChatId() {
            return chatId;
        }

        public Long getMemberId(){return memberId;}

        public Long getReceiverId(){return receiverId;}
        @Override
        public String toString() {
            return "Member [text=" + text + ", name=" + chatId + "]";
        }
}