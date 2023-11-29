package com.saesig.global.menu;

import com.saesig.domain.role.ResourceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
}
