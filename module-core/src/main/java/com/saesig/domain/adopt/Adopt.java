package com.saesig.domain.adopt;

import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Adopt extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_member_id")
    private Member adoptMember;

    @Column
    private Long hits;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String gender;

    @Column
    private Integer age;

    @Column(name = "age_category")
    private String ageCategory;

    @Column
    @Enumerated(EnumType.STRING)
    private AdoptStatus status;

    @Column(name = "is_deleted")
    private String isDeleted;

    @Column(name = "is_castrated")
    private String isCastrated;

    @Column(name = "responsibility_cost")
    private BigDecimal responsibilityCost;

    @Column(name = "etc_content")
    private String etcContent;

    @Column(name = "animal_division1_id")
    private Long animalDivision1Id;

    @Column(name = "animal_division2_id")
    private Long animalDivision2Id;

    @Column(name = "image_file_group_id")
    private Long imageFileGroupId;

    @Column
    private String sido;

    @Column
    private String sigungu;

    @Column(name = "stop_reason")
    private String stopReason;

    @Column(name = "stop_category")
    private String stopCategory;

}
