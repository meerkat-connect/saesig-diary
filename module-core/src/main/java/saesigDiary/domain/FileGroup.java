package saesigDiary.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "file_group")
public class FileGroup extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "directory_path")
    private String directoryPath;

    @Builder
    public FileGroup(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
