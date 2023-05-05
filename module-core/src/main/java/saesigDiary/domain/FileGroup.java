package saesigDiary.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Entity(name = "file_group")
public class FileGroup extends BaseEntity {
    @Id
    private Long id;

    @Column(name = "directory_path")
    private String directoryPath;

}
