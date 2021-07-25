package com.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class FlightSwaggerConfig {

	 @Bean
	public Docket mySwaggerConfiguration () {
		return new Docket(DocumentationType.SWAGGER_2)
		.select() // configuration object
		.paths(PathSelectors.ant("/api/v1.0/**"))
		.apis(RequestHandlerSelectors.basePackage("com.user"))
		.build()
	    .apiInfo(metadata())
	    ;
		}
	 
	  private ApiInfo metadata() {
        return new ApiInfoBuilder()
            .title( "Flight Swagger" )
            .description( "All the requests that the server will respond to." )
            .version( "1.0.0" )
            .build();
	  	}

	  /**
	   *  http://localhost:9052/swagger-ui.html 
	   *  Hit the above link in browser
	   */

}
