package saesigDiary.domain.member;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import saesigDiary.domain.common.BaseEntity;
import saesigDiary.domain.role.MemberRole;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "member")
@ToString(exclude = "memberRoles")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="joined_at")
    private LocalDateTime joinedAt;

    @Column(name="service_agreement")
    private Character serviceAgreement;

    @Column(name="location_service_agreement")
    private Character locationServiceAgreement;

    @Column(name="privacy_agreement")
    private Character privacyAgreement;

    @Column(name="marketing_service_agreement")
    private Character marketingServiceAgreement;

    @OneToMany(mappedBy = "member")
    List<MemberRole> memberRoles = new ArrayList<>();

    
}
