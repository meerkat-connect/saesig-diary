package com.saesig.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlockResponseDto {
    private String category;

    private String blockingMemberName;

    private String blockedMemberName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime blockedAt;
}
