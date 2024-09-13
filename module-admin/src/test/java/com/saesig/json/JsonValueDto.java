package com.saesig.json;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class JsonValueDto {
    private Long id;

    @JsonValue
    private String name;

    @JsonValue
    public String getJsonValue() {
        return String.format("id = %s, name = %s", id, name);
    }
}
