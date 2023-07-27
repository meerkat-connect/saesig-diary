package saesigDiary.global.enumCode;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class EnumMapperFactory {
    private Map<String, List<EnumMapperValue>> factory;

    public void put(String key, Class<? extends EnumMapperType> enumMapperType){
        factory.put(key, toEnumValues(enumMapperType));
    }

    public List<EnumMapperValue> get(String key) {
        return factory.get(key);
    }

    //Enum의 내용들을 List로 변환
    private List<EnumMapperValue> toEnumValues(Class<? extends EnumMapperType> enumMapperType) {
        return Arrays.stream(enumMapperType.getEnumConstants())
                .map(EnumMapperValue::new)
                .collect(Collectors.toList());
    }

}
