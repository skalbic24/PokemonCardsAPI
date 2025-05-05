package com.pokemoncards.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI pokemonCardsOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokemon Cards API")
                        .version("1.0")
                        .description("API documentation for Pokemon Cards project"));
    }
}
