package com.saesig.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "dormant_member")
public class DormantMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

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
}
