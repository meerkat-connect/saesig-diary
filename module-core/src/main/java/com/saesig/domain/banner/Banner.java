package com.saesig.domain.banner;


import com.saesig.domain.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Banner extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    @Enumerated(EnumType.STRING)
    private ExposureLocation exposureLocation;

    @Column(name = "image_file_group_id")
    private Long imageFileGroupid;

    @Column
    private String url;

    @Column
    private String ord;

    @Column(name = "is_enabled")
    private String isEnabled;
}
