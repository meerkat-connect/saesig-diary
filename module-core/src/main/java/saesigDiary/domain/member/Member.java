package saesigDiary.domain.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saesigDiary.domain.common.BaseEntity;
import saesigDiary.domain.role.MemberRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
public class Member extends BaseEntity {
    @Id
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_method")
    private SignupMethod signupMethod;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column
    private String nickname;

    @OneToMany(mappedBy = "member")
    List<MemberRole> memberRoles = new ArrayList<>();

    
}
