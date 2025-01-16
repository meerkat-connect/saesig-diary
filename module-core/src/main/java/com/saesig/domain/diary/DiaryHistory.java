package com.saesig.domain.diary;


import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class DiaryHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_id")
    private Diary adopt;

    @Column(name = "before_status")
    private Long beforeStatus;

    @Column(name = "after_status")
    private Long afterStatus;

    @Column
    private Long reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member_id;
}
