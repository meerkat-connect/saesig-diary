package com.saesig.domain.adopt;

import com.saesig.domain.animalDivision.AnimalDivision1;
import com.saesig.domain.animalDivision.AnimalDivision2;
import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import lombok.*;

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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_division1_id")
    private AnimalDivision1 animalDivision1;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_division2_id")
    private AnimalDivision2 animalDivision2;

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

    @Builder
    public Adopt(Long id, String content, String gender, String ageCategory, AdoptStatus status, AnimalDivision1 animalDivision1, AnimalDivision2 animalDivision2, String sido, String sigungu) {
        this.id = id;
        this.content = content;
        this.gender = gender;
        this.ageCategory = ageCategory;
        this.status = status;
        this.animalDivision1 = animalDivision1;
        this.animalDivision2 = animalDivision2;
        this.sido = sido;
        this.sigungu = sigungu;
    }
}
