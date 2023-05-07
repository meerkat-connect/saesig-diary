package saesigDiary.domain;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class File extends BaseEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private FileGroup fileGroup;

    @Column(name = "saved_name")
    private String savedName;

    @Column(name = "origin_name")
    private String originName;

    @Column
    private String extension;

    @Column
    private Integer size;

    @Column
    private Integer ord;
}
