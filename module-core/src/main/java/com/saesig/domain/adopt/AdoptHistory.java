package com.saesig.domain.adopt;

import com.saesig.domain.common.BaseEntity;
import com.saesig.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AdoptHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adopt_id")
    private Adopt adopt;

    @Column(name = "before_status")
    private AdoptStatus beforeStatus;

    @Column(name = "after_status")
    private AdoptStatus afterStatus;

    @Column
    private Long reason;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member_id;
}
