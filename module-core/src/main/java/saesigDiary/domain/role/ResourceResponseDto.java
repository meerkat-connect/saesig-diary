package saesigDiary.domain.role;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    public ResourceResponseDto(Resource resource) {
        this.id = resource.getId();
        this.name = resource.getName();
        this.url = resource.getUrl();
        this.httpMethod = resource.getHttpMethod();
        this.isEnabled = resource.getIsEnabled();
        this.depth = resource.getDepth();
        this.ord = resource.getOrd();
        if(resource.getParentResource() != null)
            this.parentId = resource.getParentResource().getId();
    }
}
