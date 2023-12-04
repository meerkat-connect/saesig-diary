package com.saesig.common.log;

import com.saesig.domain.log.AdminAccessLog;
import com.saesig.global.menu.ResourceItem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdminAccessLogResponseDto {
    private String ip;
    private String userAgent;
    private String action;
    private String resourceUrl;
    private Long resourceId;
    private String resourceName;


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
