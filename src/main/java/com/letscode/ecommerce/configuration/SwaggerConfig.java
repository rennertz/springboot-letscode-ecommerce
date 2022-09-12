package com.letscode.ecommerce.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI springShopOpenApi() {
        return new OpenAPI()
                .info(new Info()
                    .title("Ecommerce API")
                    .description("Lets Code - Banco do Brasil")
                    .version("v0.0.1")
                );
    }
}
