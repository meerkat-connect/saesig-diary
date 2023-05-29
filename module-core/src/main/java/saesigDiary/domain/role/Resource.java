package saesigDiary.domain.role;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saesigDiary.domain.common.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Resource extends BaseEntity {
    @Id
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "upper_id")
    private Resource parentResource;

    @OneToMany(mappedBy = "parentResource", fetch = FetchType.LAZY)
    private List<Resource> childResources = new ArrayList<>();
}
