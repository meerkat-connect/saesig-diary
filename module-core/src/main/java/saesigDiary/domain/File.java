package saesigDiary.domain;


import javax.persistence.*;

@Entity
public class File extends BaseEntity {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private FileGroup fileGroup;

    /* 굳이 필요한가? */
    @Column
    private String path;

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
