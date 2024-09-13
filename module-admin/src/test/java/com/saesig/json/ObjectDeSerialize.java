package com.saesig.json;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectDeSerialize extends JsonDeserializer<JsonSerializeDto2> {
    @Override
    public JsonSerializeDto2 deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        Long id = (node.get("id") != null) ? node.get("id").asLong() : null;
        String name = (node.get("name") != null) ? node.get("name").asText() : null;
        return new JsonSerializeDto2(id, name);
    }
}
