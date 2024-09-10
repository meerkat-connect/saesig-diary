package com.saesig.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class JsonLearingTestCodeV1 {
    private ObjectMapper objectMapper;

    @BeforeAll
    public void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    @DisplayName("JsonValue 테스트")
    public void JsonValue_테스트() throws JsonProcessingException {
        //given
        JsonValueDto jsonValue = new JsonValueDto(1L, "wonjjong");
        String expectedJsonString = "id = 1, name = wonjjong";

        //when
        String serializedJsonString = objectMapper.writeValueAsString(jsonValue);

        //then
        assertThat(serializedJsonString).isEqualTo(expectedJsonString);
    }


    @Test
    @DisplayName("Serialize 테스트")
    public void Serialize_테스트() throws JsonProcessingException {
        //given
        JsonSerializeDto jsonSerialize = new JsonSerializeDto(1L, "wonjjong");

        //when
        String serializedJsonString  = objectMapper.writeValueAsString(jsonSerialize);
        JsonSerializeDto jsonSerializeDto = objectMapper.readValue(serializedJsonString, JsonSerializeDto.class);

        //then
        System.out.println(serializedJsonString);
        System.out.println(jsonSerializeDto);
    }

    @Test
    @DisplayName("unitTest")
    public void unitTest() throws JsonProcessingException {
        //given
        JsonDto jsonDto = new JsonDto(1L, "Won", "jjong", JsonEnum.TEST2);

        //when
        String serializedJsonString  = objectMapper.writeValueAsString(jsonDto); // 직렬화

        //then
        System.out.println(serializedJsonString);

        JsonDto deSerializedJsonString = objectMapper.readValue(serializedJsonString, JsonDto.class);
        System.out.println(deSerializedJsonString);
        /*assertThat(serializedJson).contains("\"jsonEnum\":\"TEST,test\"");*/

    }


}
