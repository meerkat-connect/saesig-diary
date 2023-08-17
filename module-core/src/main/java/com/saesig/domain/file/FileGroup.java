package com.saesig.domain.file;

import lombok.*;
import com.saesig.domain.common.BaseEntity;

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

    public FileGroup(String directoryPath) {
        this.directoryPath = directoryPath;
    }
}
