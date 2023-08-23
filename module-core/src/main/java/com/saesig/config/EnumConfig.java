package com.saesig.config;

import com.saesig.domain.templateManage.SendCategory;
import com.saesig.domain.templateManage.SendMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.saesig.domain.faq.FaqCategory;
import com.saesig.global.enumCode.EnumMapperFactory;
import com.saesig.global.enumCode.ExampleEnum;

import java.util.LinkedHashMap;

@Configuration
public class EnumConfig {
    @Bean
    public EnumMapperFactory enumMapperFactory() {
        EnumMapperFactory factory = new EnumMapperFactory(new LinkedHashMap<>());

        factory.put("exampleEnum", ExampleEnum.class);
        factory.put("faqCategory", FaqCategory.class);
        factory.put("sendMethod", SendMethod.class);
        factory.put("sendCategory", SendCategory.class);
        // 해당 라인에 새롭게 추가한 Enum 클래스들 추가


        return factory;
    }
}
