package com.saesig.webSocketNetty.chatting;

import lombok.Data;

@Data
public class ChatMemberDto {

        private Long ID;
        private String EMAIL;
        private String PASSWORD;
        private String NICKNAME;
}
