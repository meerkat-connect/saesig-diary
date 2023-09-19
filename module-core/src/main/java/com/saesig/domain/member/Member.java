package com.saesig.domain.member;


import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.role.MemberRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    @Column(name = "prev_password")
    private String prevPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "signup_method")
    private SignupMethod signupMethod;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    @Column
    private String nickname;

    @Column(name = "last_logged_at")
    private LocalDateTime lastLoggedAt;

    @Column(name ="deleted_at")
    private LocalDateTime deletedAt;

    @Column(name = "password_modified_at")
    private LocalDateTime passwordModifiedAt;

    @Column(name = "service_agreement")
    private String serviceAgreement;

    @Column(name = "location_service_agreement")
    private String locationServiceAgreement;

    @Column(name = "privacy_agreement")
    private String privacyAgreement;

    @Column(name = "marketing_service_agreement")
    private String marketingServiceAgreement;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    List<MemberRole> memberRoles = new ArrayList<>();

    @Builder
    public Member(Long id, String email, String password, SignupMethod signupMethod, MemberStatus status, String nickname, String serviceAgreement, String locationServiceAgreement, String privacyAgreement, String marketingServiceAgreement) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.signupMethod = signupMethod;
        this.status = status;
        this.nickname = nickname;
        this.serviceAgreement = serviceAgreement;
        this.locationServiceAgreement = locationServiceAgreement;
        this.privacyAgreement = privacyAgreement;
        this.marketingServiceAgreement = marketingServiceAgreement;
    }

    public void setTemporaryPassword(String oldPassword, String newPassword) {
        this.password = newPassword;
        this.prevPassword = oldPassword;
        this.passwordModifiedAt = LocalDateTime.now();
    }
}
