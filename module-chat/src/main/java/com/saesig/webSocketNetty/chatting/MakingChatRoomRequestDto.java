package com.saesig.webSocketNetty.chatting;

import lombok.Data;

@Data
public class MakingChatRoomRequestDto {
    Long adoptId;
    Long memberId;
}
