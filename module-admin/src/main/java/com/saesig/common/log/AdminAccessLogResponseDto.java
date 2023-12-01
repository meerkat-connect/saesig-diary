package com.saesig.common.log;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminAccessLogResponseDto {
    private String ip;
    private String action;
    private String resourceName;
    private String resourceUrl;
}
