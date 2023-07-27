package saesigDiary.common.enumCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*
 *
 *
 * 사용안함
 *
 * */
@Getter
@AllArgsConstructor
public enum ExampleUpperEnum implements EnumMapperType {
    EXAMPLE_UPPER_ENUM_1_1("예제1-1", ExampleEnum.EXAMPLE1),
    EXAMPLE_UPPER_ENUM_1_2("예제1-2", ExampleEnum.EXAMPLE1),
    EXAMPLE_UPPER_ENUM_2_1("예제2-1", ExampleEnum.EXAMPLE2),
    EXAMPLE_UPPER_ENUM_2_2("예제2-2", ExampleEnum.EXAMPLE2);

    private final String value;
    private final EnumMapperType parentEnum;

    @Override
    public String getKey() {
        return name();
    }

}
