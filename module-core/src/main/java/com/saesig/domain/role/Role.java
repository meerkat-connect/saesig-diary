package com.saesig.domain.role;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import com.saesig.domain.common.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Role extends BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column(name = "is_enabled")
    private Character isEnabled;

    @OneToMany(mappedBy = "role")
    private List<MemberRole> memberRoles = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "upper_id")
    private Role parentRole;

    @OneToMany(mappedBy = "parentRole")
    private List<Role> childRoles = new ArrayList<>();

    @Builder
    public Role(Long id, String name, String description, Character isEnabled, List<MemberRole> memberRoles, Role parentRole, List<Role> childRoles) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isEnabled = isEnabled;
        this.memberRoles = memberRoles;
        this.parentRole = parentRole;
        this.childRoles = childRoles;
    }

    public void updateInfo(String name, Character isEnabled, String description) {
        this.name = name;
        this.isEnabled = isEnabled;
        this.description = description;
    }
}
