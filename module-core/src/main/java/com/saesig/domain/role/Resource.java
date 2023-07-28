package com.saesig.domain.role;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import com.saesig.domain.common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
@ToString(exclude = "parentResource")
public class Resource extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String url;

    @Column(name = "http_method")
    private String httpMethod;

    @Column(name = "is_enabled")
    private Character isEnabled;

    @Column
    private Integer depth;

    @Column
    private Integer ord;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upper_id")
    private Resource parentResource;

    @OneToMany(mappedBy = "parentResource", fetch = FetchType.LAZY)
    private List<Resource> childResources = new ArrayList<>();

    @Builder
    public Resource(Long id, String name, String url, String httpMethod, Character isEnabled, Integer depth, Integer ord, String type, Resource parentResource, List<Resource> childResources) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.httpMethod = httpMethod;
        this.isEnabled = isEnabled;
        this.depth = depth;
        this.ord = ord;
        this.type = type;
        this.parentResource = parentResource;
        this.childResources = childResources;
    }

    public void updateInfo(String name, String url, String httpMethod, Character isEnabled, String type) {
        this.name = name;
        this.url =url;
        this.httpMethod = httpMethod;
        this.isEnabled = isEnabled;
        this.type = type;
    }

    public void move(Long upperId, Integer depth, Integer ord) {
        parentResource.getParentResource().changeParentId(upperId);
        this.depth = depth;
        this.ord = ord;
    }

    public void changeOrd(Integer ord) {
        this.ord = ord;
    }

    public void changeParentId(Long upperId) {
        this.getParentResource().changeParentId(upperId);
    }
}