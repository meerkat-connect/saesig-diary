package com.saesig.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectSerialize extends JsonSerializer<JsonSerializeDto2> {
    @Override
    public void serialize(JsonSerializeDto2 value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        log.info("serialize = {}", value);
        gen.writeString(value.toString());
    }
}
