package com.saesig.json;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JsonValueEnum {
    C("CC"),
    D("DD");

    private final String value;

    @JsonValue
    private String getJsonValue() {
        return String.format("%s/%s", name(), getValue());
    }
}
