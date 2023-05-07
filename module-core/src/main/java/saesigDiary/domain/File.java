package saesigDiary.domain;


import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class File extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
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

    @Builder
    public File(Long id, FileGroup fileGroup, String savedName, String originName, String extension, Integer size, Integer ord) {
        this.id = id;
        this.fileGroup = fileGroup;
        this.savedName = savedName;
        this.originName = originName;
        this.extension = extension;
        this.size = size;
        this.ord = ord;
    }
}
