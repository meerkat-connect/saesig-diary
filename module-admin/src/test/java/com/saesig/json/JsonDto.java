package com.saesig.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

@NoArgsConstructor
@Getter
@ToString
public class JsonDto {
    private Long id;

    private String lastName;

    @JsonSerialize(using = NameSerialize.class)
    private String firstName;

    @JsonDeserialize(using = NameDeSerialize.class)
    private String fullName;

    private JsonValueEnum jsonValueEnum;

    private JsonFormatEnum jsonFormatEnum;

    public JsonDto(Long id, String lastName, String firstName, JsonValueEnum jsonValueEnum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jsonValueEnum = jsonValueEnum;
        this.fullName = this.lastName + " " + this.firstName;
    }

    public JsonDto(Long id, String lastName, String firstName, JsonValueEnum jsonValueEnum, JsonFormatEnum jsonFormatEnum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jsonValueEnum = jsonValueEnum;
        this.jsonFormatEnum = jsonFormatEnum;
    }

}
