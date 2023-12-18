package com.saesig.webSocketNetty.websocket;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class chatJsonData {
    private String type;
    private String text;
        private int chatId;

        private int memberId;

        private int receiverId;
        private LocalDateTime sendTime;

        public String getText() {
            return text;
        }

        public String getType() {
        return type;
    }
        public int getChatId() {
            return chatId;
        }

        public int getMemberId(){return memberId;}

        public int getReceiverId(){return receiverId;}
        @Override
        public String toString() {
            return "Member [text=" + text + ", name=" + chatId + "]";
        }
}