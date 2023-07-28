package com.saesig.domain.role;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RoleResourceResponseDto {
    private Long id;
    private String name;
    private String url;
    private Character isMapped;
    private Long parentId;
    private String type;

    public RoleResourceResponseDto(String name, String url, Character isMapped, Long parentId, String type, Long id) {
        this.name = name;
        this.url = url;
        this.isMapped = isMapped;
        this.parentId = parentId;
        this.type = type;
        this.id = id;
    }
}
