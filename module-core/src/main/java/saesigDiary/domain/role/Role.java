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
@Entity(name="role")
public class Role extends BaseEntity {
    @Id
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
    @JoinColumn(name = "upperId")
    private Role parentRole;

    @OneToMany(mappedBy = "parentRole")
    private List<Role> childRoles = new ArrayList<>();

}
