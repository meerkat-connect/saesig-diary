package com.saesig.resource;


import lombok.Getter;
import lombok.ToString;
import com.saesig.domain.role.Resource;

@Getter
@ToString
public class ResourceResponseDto {
    private Long id;

    private String name;

    private String url;

    private String httpMethod;

    private Character isEnabled;

    private Integer depth;

    private Integer ord;

    private Long parentId;

    private String type;

    private String parentUrl;

    public ResourceResponseDto(Resource resource) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.url = resource.getUrl();
        this.httpMethod = resource.getHttpMethod();
        this.isEnabled = resource.getIsEnabled();
        this.depth = resource.getDepth();
        this.ord = resource.getOrd();
        this.type = resource.getType();

        if(this.depth > 1) {
            Resource parentResource = resource.getParentResource();
            this.parentId = parentResource.getId();
            this.parentUrl = parentResource.getUrl();

        }
    }
}
