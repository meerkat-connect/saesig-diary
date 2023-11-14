package com.saesig.resource;

import lombok.Getter;

@Getter
public class ResourceUpdateDto {
    private Long id;
    private String url;
    private String name;
    private String httpMethod;
    private String type;
    private String styleClass;
    private Character isEnabled;
    private String category;
    private Character isLoginDisallowed;
}
