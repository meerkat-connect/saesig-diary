package saesigDiary.global.enumCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/*
* 
* 
* 사용안함
* 
* */
@AllArgsConstructor
@Getter
public enum ExampleEnumList implements EnumMapperTypeList<ExampleEnum> {
    EXAMPLE_ENUM_GROUP_A("exampleGroupA", Arrays.asList(ExampleEnum.EXAMPLE1, ExampleEnum.EXAMPLE2)),
    EMPTY("없음", Collections.emptyList());

    private String title;
    private List<ExampleEnum> valueList;

    public ExampleEnumList findByExampleEnum(ExampleEnum exampleEnum) {
        return Arrays.stream(ExampleEnumList.values())
                .filter(exampleEnumList -> exampleEnumList.hasExampleEnum(exampleEnum))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasExampleEnum(ExampleEnum exampleEnum) {
        return valueList.stream()
                .anyMatch(example -> example == exampleEnum);
    }
}
