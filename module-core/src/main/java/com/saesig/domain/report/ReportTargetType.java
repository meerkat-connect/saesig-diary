package com.saesig.domain.report;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saesig.global.enumCode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Stream;

/**
 * [클래스 요약].
 *
 * <pre>
 *
 * -
 * </pre>
 *
 * @author :
 * @ClassName :
 * @Description :
 * @date :
 * @Version :
 * @Company : CopyrightⒸ KBRAIN Company. All Rights Reserved
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@RequiredArgsConstructor
public enum ReportTargetType implements EnumMapperType {
    ADOPT("ADOPT"), DIARY("DIARY");

    @Getter
    private final String value;

    @Override
    public String getKey() {
        return name();
    }

    @JsonCreator
    public static ReportTargetType from(String sub) {
        return Stream.of(ReportTargetType.values())
                .filter(type -> type.toString().equals(sub.toUpperCase()))
                .findFirst()
                .orElse(null);
    }
}
