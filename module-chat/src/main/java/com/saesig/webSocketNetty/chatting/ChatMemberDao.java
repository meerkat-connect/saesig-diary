package com.saesig.webSocketNetty.chatting;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatMemberDao {
    private List<ChatDataResponseDto> ChatDataResponseDto;

    public ChatMemberDao(List<ChatDataResponseDto> list){
        this.ChatDataResponseDto = list;
    }
}
