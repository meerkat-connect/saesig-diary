package com.saesig.global.enumCode;


import lombok.Getter;

@Getter
public class EnumMapperValue {
    private final String key;
    private final String value;

    public EnumMapperValue(EnumMapperType enumMapperType) {
        this.key = enumMapperType.getKey();
        this.value = enumMapperType.getValue();
    }

}
