package saesigDiary.resource;

import lombok.Getter;
import saesigDiary.domain.role.Resource;

@Getter
public class ResourceInsertDto {
    private String url;
    private String name;
    private String httpMethod;
    private String type;
    private Character isEnabled;
    private Integer ord;
    private Integer depth;
    private Long upperId;

    public Resource toEntity() {
        return Resource
                .builder()
                .url(url)
                .name(name)
                .depth(depth)
                .ord(ord)
                .parentResource(setParentResource(upperId))
                .httpMethod(httpMethod)
                .type(type)
                .isEnabled(isEnabled)
                .build();
    }

    public Resource setParentResource(Long upperId) {
        return Resource.builder().id(upperId).build();
    }

}
