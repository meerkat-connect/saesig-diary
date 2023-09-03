package com.saesig.domain.diary;

import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import com.saesig.domain.report.ReportCategory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity(name = "diary_report")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryReport extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportCategory category;

    @Column
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member reportMember;

}
