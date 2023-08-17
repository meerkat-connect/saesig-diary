package com.saesig.webSocketNetty.chatting;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatDataSearchResponseDto {
    private List<ChatDataDto> ChatDataDto;

    public ChatDataSearchResponseDto(List<ChatDataDto> list){
        this.ChatDataDto = list;
    }
}