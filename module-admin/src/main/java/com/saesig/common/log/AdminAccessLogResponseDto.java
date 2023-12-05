package com.saesig.common.log;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.domain.log.AdminAccessLog;
import com.saesig.global.menu.ResourceItem;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AdminAccessLogResponseDto {
    private String ip;
    private String userAgent;
    private String action;
    private String resourceUrl;
    private Long resourceId;
    private String resourceName;
    private String nickname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDateTime createdAt;

    public AdminAccessLogResponseDto(HttpServletRequest request, ResourceItem resourceItem) {
        this.ip = request.getRemoteAddr();
        this.userAgent = request.getHeader(("user-agent"));
        this.action = resourceItem.getHttpMethod();
        this.resourceUrl = resourceItem.getUrl();
        this.resourceId = resourceItem.getId();
        this.resourceName = resourceItem.getName();
    }
    public AdminAccessLog toEntity() {
        return AdminAccessLog.builder()
                .ip(this.ip)
                .userAgent(this.userAgent)
                .action(this.action)
                .resourceUrl(this.resourceUrl)
                .resourceId(this.resourceId)
                .resourceName(this.resourceName)
                .build();
    }
}
