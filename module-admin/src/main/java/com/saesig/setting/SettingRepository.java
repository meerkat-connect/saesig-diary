package com.saesig.setting;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.saesig.domain.file.File;
import com.saesig.domain.file.QFile;
import com.saesig.domain.file.QFileGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class SettingRepository {
    private final JPAQueryFactory queryFactory;

    Optional<File> findFile(String path) {
        QFile file = QFile.file;
        QFileGroup fileGroup = QFileGroup.fileGroup;

        File selectedFile = queryFactory.select(file)
                .from(file)
                .innerJoin(file.fileGroup, fileGroup)
                .orderBy(file.createdAt.desc())
                .where(file.fileGroup.path.eq(path))
                .fetchFirst();

        return Optional.ofNullable(selectedFile);
    }
}
