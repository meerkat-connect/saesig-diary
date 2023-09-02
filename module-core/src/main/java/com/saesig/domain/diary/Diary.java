package com.saesig.domain.diary;

import com.saesig.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private Long imageFileGroupId;

    @Column
    @Enumerated(EnumType.STRING)
    private WeatherCategory weatherCategory;

    @Column
    private String isSecret;

    @Column
    private String isDeleted;

    @Column
    private Integer hits;

    @Column
    private Long tagGroupId;
}
