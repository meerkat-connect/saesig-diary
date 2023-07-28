package com.saesig.domain.faq;


import com.saesig.domain.common.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@ToString
public class Faq extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private FaqCategory category;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Long ord;

    @Column(name = "is_enabled")
    private Character isEnabled;

    @Builder
    public Faq(FaqCategory category, String title, String content, Long ord, Character isEnabled) {
        this.category = category;
        this.title = title;
        this.content = content;
        this.ord = ord;
        this.isEnabled = isEnabled;
    }

    public void updateInfo(String title, String content, FaqCategory category, Character isEnabled) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.isEnabled = isEnabled;
    }
}
