package com.saesig.global.enumCode;

import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.global.enumCode.EnumMapperValue;
import com.saesig.global.enumCode.ExampleEnum;
import com.saesig.global.enumCode.ExampleUpperEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class EnumMapperFactoryTest {
    EnumMapperFactory factory;
    
    @BeforeEach
    void setUp() {
        factory = new EnumMapperFactory(new LinkedHashMap<>());

        factory.put("exampleEnum", ExampleEnum.class);
        factory.put("exampleUpperEnum", ExampleUpperEnum.class);
    }

    @Test
    @DisplayName("ExampleEnum 전체 조회")
    void ExampleEnum_전체_조회(){
        //given

        //when
        List<EnumMapperValue> exampleEnum = factory.get("exampleEnum");

        //then
        assertThat(exampleEnum).isNotNull();
    }
}