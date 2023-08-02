package com.saesig.webSocketNetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatMemberDao {
    private List<ChatDataDto> ChatDataDto;

    public ChatMemberDao(List<ChatDataDto> list){
        this.ChatDataDto = list;
    }
}
