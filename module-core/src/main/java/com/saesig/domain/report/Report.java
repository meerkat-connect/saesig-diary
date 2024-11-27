package com.saesig.domain.report;

import com.saesig.domain.adopt.Adopt;
import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.diary.Diary;
import com.saesig.domain.member.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Report extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long targetId;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportTargetType targetType;

    @Column
    @Enumerated(EnumType.STRING)
    private ReportCategory category;

    @Column
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member reportMember;

    @Builder
    public Report(Long id, Long targetId, ReportCategory category, String content, Member reportMember, ReportTargetType targetType) {
        this.id = id;
        this.targetId = targetId;
        this.category = category;
        this.content = content;
        this.reportMember = reportMember;
        this.targetType = targetType;
    }
}
