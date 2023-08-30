package com.saesig.api.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("!prod")
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        Info info = new Info().title("새식일기 API 문서")
                .version("0.0.1")
                .description("새식일기 API 명세서입니다.");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}
