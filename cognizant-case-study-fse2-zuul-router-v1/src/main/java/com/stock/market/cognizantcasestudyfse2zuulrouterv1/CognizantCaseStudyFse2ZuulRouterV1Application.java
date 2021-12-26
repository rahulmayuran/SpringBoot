package com.stock.market.cognizantcasestudyfse2zuulrouterv1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@CrossOrigin(origins = "*",allowedHeaders = "*",allowCredentials = "true")
public class CognizantCaseStudyFse2ZuulRouterV1Application {

	public static void main(String[] args) {
		SpringApplication.run(CognizantCaseStudyFse2ZuulRouterV1Application.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOrigin("*");
		corsConfig.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
		corsConfig.setExposedHeaders(Arrays.asList("x-auth-token"));
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.addAllowedMethod("HEAD");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("DELETE");
		corsConfig.addAllowedMethod("PATCH");
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfig);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}
