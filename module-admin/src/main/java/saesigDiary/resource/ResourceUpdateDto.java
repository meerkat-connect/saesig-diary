package saesigDiary.resource;

import lombok.Getter;
import saesigDiary.domain.role.Resource;

@Getter
public class ResourceUpdateDto {
    private Long id;
    private String url;
    private String name;
    private String httpMethod;
    private String type;
    private Character isEnabled;
}
