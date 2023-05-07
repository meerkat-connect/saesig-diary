package saesigDiary.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "file_group")
public class FileGroup extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "directory_path")
    private String directoryPath;

    @Builder
    public FileGroup(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
