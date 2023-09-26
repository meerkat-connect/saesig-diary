package com.saesig.resource;


import com.saesig.domain.role.Resource;
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
    private String type;
    private String httpMethod;
    private Character isEnabled;
    private String url;
    private Integer depth;
    private Integer ord;
    private String treeOrd;
    private String treeName;

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
            this.upperId = parentResource.getId();
        }
    }
}
