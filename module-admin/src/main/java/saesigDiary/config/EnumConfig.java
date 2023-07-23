package saesigDiary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import saesigDiary.common.enumCode.EnumMapperFactory;
import saesigDiary.common.enumCode.ExampleEnum;

import java.util.LinkedHashMap;

@Configuration
public class EnumConfig {
    @Bean
    public EnumMapperFactory enumMapperFactory() {
        EnumMapperFactory factory = new EnumMapperFactory(new LinkedHashMap<>());

        factory.put("exampleEnum", ExampleEnum.class);
        // 해당 라인에 새롭게 추가한 Enum 클래스들 추가


        return factory;
    }
}
