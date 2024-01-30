package com.saesig.webSocketNetty.chatting;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatDataSearchResponseDto {
    private List<ChatDataResponseDto> ChatDataResponseDto;

    public ChatDataSearchResponseDto(List<ChatDataResponseDto> list){
        this.ChatDataResponseDto = list;
    }
}