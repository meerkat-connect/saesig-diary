package saesigDiary.resource;


import lombok.Builder;
import lombok.Getter;
import saesigDiary.domain.role.Resource;

@Getter
public class ResourceRequestDto {
    private Long id;
    private String url;
    private String name;
    private String httpMethod;
    private String type;
    private Character isEnabled;

    public Resource toEntity() {
        return Resource
                .builder()
                .id(id)
                .url(url)
                .name(name)
                .httpMethod(httpMethod)
                .type(type)
                .isEnabled(isEnabled)
                .build();
    }
}
