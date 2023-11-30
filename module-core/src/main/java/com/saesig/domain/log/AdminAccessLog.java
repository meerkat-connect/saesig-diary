package com.saesig.domain.log;

import com.saesig.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name= "admin_access_log")
public class AdminAccessLog extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ip;

    @Column
    private String action;

    @Column(name="resource_name")
    private String resourceName;

    @Column(name = "resource_url")
    private String resourceUrl;

    @Column(name = "resource_id")
    private Long resourceId;

    @Column
    private String message;

    @Column(name = "user_agent")
    private String userAgent;

    @Builder
    public AdminAccessLog(String ip, String action, String resourceName, String resourceUrl, Long resourceId, String message, String userAgent) {
        this.ip = ip;
        this.action = action;
        this.resourceName = resourceName;
        this.resourceUrl = resourceUrl;
        this.resourceId = resourceId;
        this.message = message;
        this.userAgent = userAgent;
    }
}
