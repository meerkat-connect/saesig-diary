package com.saesig.resource;


import com.saesig.domain.role.Resource;
import com.saesig.domain.role.ResourceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ResourceResponseDto {
    private Long id;
    private Long upperId;
    private String name;
    private ResourceType type;
    private String httpMethod;
    private String styleClass;
    private Character isEnabled;
    private String url;
    private Integer depth;
    private Integer ord;
    private String treeOrd;
    private String treeName;
    private String category;
    private Character isLoginDisallowed;

    public ResourceResponseDto(Resource resource) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.url = resource.getUrl();
        this.httpMethod = resource.getHttpMethod();
        this.styleClass = resource.getStyleClass();
        this.isEnabled = resource.getIsEnabled();
        this.depth = resource.getDepth();
        this.ord = resource.getOrd();
        this.type = resource.getType();
        this.category = resource.getCategory();
        this.isLoginDisallowed = resource.getIsLoginDisallowed();

        if(this.depth > 1) {
            Resource parentResource = resource.getParentResource();
            this.upperId = parentResource.getId();
        }
    }
}
