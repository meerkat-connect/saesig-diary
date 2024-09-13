package com.saesig.json;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@JsonSerialize(using = ObjectSerialize.class)
@JsonDeserialize(using = ObjectDeSerialize.class)
@Getter
@ToString
public class JsonSerializeDto2 {
    private Long id;
    private String name;
}
