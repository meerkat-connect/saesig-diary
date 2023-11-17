package com.saesig.resource;

import com.saesig.domain.role.ResourceType;
import lombok.Getter;
import com.saesig.domain.role.Resource;

@Getter
public class ResourceInsertDto {
    private String url;
    private String name;
    private String httpMethod;
    private String type;
    private Character isEnabled;
    private String styleClass;
    private Integer ord;
    private Integer depth;
    private Long upperId;
    private String category;
    private Character isLoginDisallowed;

    public Resource toEntity() {
        return Resource
                .builder()
                .url(url)
                .name(name)
                .depth(depth)
                .ord(ord)
                .styleClass(styleClass)
                .parentResource(setParentResource(upperId))
                .httpMethod(httpMethod)
                .type(ResourceType.from(type))
                .isEnabled(isEnabled)
                .category(category)
                .isLoginDisallowed(isLoginDisallowed)
                .build();
    }

    public Resource setParentResource(Long upperId) {
        return Resource.builder().id(upperId).build();
    }

}
