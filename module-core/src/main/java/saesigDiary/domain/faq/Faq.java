package saesigDiary.domain.faq;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import saesigDiary.domain.common.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
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

    @Column(name="is_enabled")
    private Character isEnabled;

}
