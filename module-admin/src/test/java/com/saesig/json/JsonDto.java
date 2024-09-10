package com.saesig.json;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class JsonDto {
    private Long id;

    private String lastName;

    @JsonSerialize(using = NameSerialize.class)
    private String firstName;

    @JsonDeserialize(using = NameDeSerialize.class)
    private String fullName;

    private JsonEnum jsonEnum;

    public JsonDto(Long id, String lastName, String firstName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.fullName = this.lastName + " " + this.firstName;
    }


    public JsonDto(Long id, String lastName, String firstName, JsonEnum jsonEnum) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.jsonEnum = jsonEnum;
        this.fullName = this.lastName + " " + this.firstName;
    }

    public String toString() {
        return id + "," + lastName + "," + firstName + "," + jsonEnum;
    }
}
