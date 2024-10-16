package com.saesig.domain.role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.saesig.domain.common.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class RoleResource extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public RoleResource(Role role, Resource resource) {
        this.role = role;
        this.resource = resource;
    }
}
