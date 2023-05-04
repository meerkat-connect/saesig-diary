package saesigDiary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Resource extends BaseEntity{
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

    @ManyToOne
    @JoinColumn(name = "upperId")
    private Role parentResource;

    @OneToMany(mappedBy = "parentResource")
    private List<Resource> childResources = new ArrayList<>();

}
