package saesigDiary.common.enumCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExampleEnum implements EnumMapperType{
    EXAMPLE1("예제1"),
    EXAMPLE2("예제2");

    @Getter
    private final String value;


    @Override
    public String getKey() {
        return name();
    }
}
