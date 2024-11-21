package com.saesig.domain.report;

import com.saesig.domain.adopt.Adopt;
import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.diary.Diary;
import com.saesig.domain.member.Member;
import lombok.AccessLevel;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

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
