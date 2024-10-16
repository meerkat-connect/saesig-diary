package com.saesig.global.menu;

import com.saesig.domain.role.Resource;
import com.saesig.domain.role.ResourceType;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class ResourceItem {
    private Long id;
    private Long upperId;
    private String name;
    private String type;
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

    public boolean isMenu() {
        return ResourceType.MENU.equals(ResourceType.from(this.type));
    }

    public boolean isDirectory() {
        return ResourceType.DIRECTORY.equals(ResourceType.from(this.type));
    }

    public boolean isVisible() {
        return isMenu() || isDirectory();
    }

    @Deprecated
    public static ResourceItem fromEntity(Resource resource) {
        return ResourceItem.builder()
                .id(resource.getId())
                .upperId(resource.getParentResource() != null ? resource.getParentResource().getId() : null)
                .name(resource.getName())
                .type(resource.getType() != null ? resource.getType().name() : null)
                .httpMethod(resource.getHttpMethod())
                .styleClass(resource.getStyleClass())
                .isEnabled(resource.getIsEnabled())
                .url(resource.getUrl())
                .depth(resource.getDepth())
                .ord(resource.getOrd())
                .category(resource.getCategory())
                .isLoginDisallowed(resource.getIsLoginDisallowed())
                .build();
    }
}
