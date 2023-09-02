package com.saesig.domain.adopt;

import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import com.saesig.domain.report.ReportCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "adopt_report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AdoptReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_id")
    private Adopt adopt;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportCategory category;

    @Column
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member reportMember;
}
