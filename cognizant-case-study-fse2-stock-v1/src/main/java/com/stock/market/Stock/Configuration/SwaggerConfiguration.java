package com.stock.market.Stock.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/*
 * For raw json - http://localhost:9051/v3/api-docs
 * In yml format - http://localhost:9051/v3/api-docs.yaml
 * In Swagger UI - http://localhost:9051/swagger-ui.html 
*/

@Configuration
public class SwaggerConfiguration { 
	
	@Bean
    public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(new Components())
            .info(new Info(
            		).title("Stock Application API")
            		.description("Test OpenApi3"));
}
	
}
