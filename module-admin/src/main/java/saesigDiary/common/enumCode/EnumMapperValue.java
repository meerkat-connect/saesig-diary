package saesigDiary.common.enumCode;


import lombok.Getter;

@Getter
public class EnumMapperValue {
    private String key;
    private String value;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.key = enumMapperType.getKey();
        this.value = enumMapperType.getValue();
    }

}
