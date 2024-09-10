package com.saesig.json;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class JsonSerializeDto {
    private Long id;

    @JsonSerialize(using = NameSerialize.class)
    @JsonDeserialize(using = NameDeSerialize.class)
    private String name;

}
