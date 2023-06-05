package saesigDiary.resource;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import saesigDiary.domain.role.Resource;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
