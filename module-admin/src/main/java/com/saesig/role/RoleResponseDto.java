package com.saesig.role;

import lombok.Getter;
import com.saesig.domain.role.Role;

@Getter
public class RoleResponseDto {
    private Long id;

    private String name;

    private String description;

    private Character isEnabled;

    private Long parentId;

    public RoleResponseDto(Role role) {
        this.id = role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.isEnabled = role.getIsEnabled();

        if(role.getParentRole() != null) {
            this.parentId = role.getParentRole().getId();
        }
    }
}
