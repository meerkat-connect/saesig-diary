package com.saesig.json;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JsonEnum {
    TEST("test"),
    TEST2("test2");

    private final String name;

    @JsonValue
    private String getJsonValue() {
        return "11111";
    }
}
