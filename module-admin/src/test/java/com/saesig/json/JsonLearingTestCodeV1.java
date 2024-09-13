package com.saesig.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        JsonDto jsonDto = new JsonDto(1L, "Won", "jjong", JsonValueEnum.C);

        //when
        String serializedJsonString  = objectMapper.writeValueAsString(jsonDto); // 직렬화

        //then
        System.out.println(serializedJsonString);

        JsonDto deSerializedJsonString = objectMapper.readValue(serializedJsonString, JsonDto.class);
        System.out.println(deSerializedJsonString);
        /*assertThat(serializedJson).contains("\"jsonEnum\":\"TEST,test\"");*/

    }

    @Test
    @DisplayName("JsonValue 어노테이션 복수 사용 ")
    public void test3() {
        //given
        JsonValueDto jsonValueDto = new JsonValueDto(1L, "wonjjong");

        //then
        assertThatThrownBy(() -> {
            objectMapper.writeValueAsString(jsonValueDto);
        }).isInstanceOf(InvalidDefinitionException.class);
    }

    @Test
    @DisplayName("test")
    public void test4() throws JsonProcessingException {
        //given
        JsonDto jsonDto = new JsonDto(1L, "Won", "jjong", JsonValueEnum.C, JsonFormatEnum.A);
        String json = "{\"id\":1,\"lastName\":\"Won\",\"firstName\":\"jjong\", \"jsonValueEnum\":\"C\",\"jsonFormatEnum\":\"A\"}";

        //when
        String s = objectMapper.writeValueAsString(jsonDto);
        JsonDto jsonDto1 = objectMapper.readValue(json, JsonDto.class);

        //then
        System.out.println(s);
        System.out.println(jsonDto1);

    }

    @Test
    @DisplayName("test5")
    public void test5() throws JsonProcessingException {
        //given
        JsonSerializeDto2 wonjjong = new JsonSerializeDto2(1L, "wonjjong");

        //when
        String s = objectMapper.writeValueAsString(wonjjong);

        //then
        System.out.println(s);
    }

    @Test
    @DisplayName("test6")
    public void test6() throws JsonProcessingException {
        //given
        String json = "{\"id\":\"1\",\"name\":\"wonjjong\"}";

        //when
        JsonSerializeDto2 jsonSerializeDto2 = objectMapper.readValue(json, JsonSerializeDto2.class);

        //then
        System.out.println(jsonSerializeDto2);
    }
}
