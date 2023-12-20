package com.lord.securityservice.configuration;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class WebConfiguration {

	@Bean
	CorsFilter corsFilter() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.addAllowedOrigin("https://localhost:8080");
		corsConfiguration.setAllowedHeaders(Arrays.asList(
				 
				 HttpHeaders.AUTHORIZATION,
				 HttpHeaders.CONTENT_TYPE,
				 HttpHeaders.ACCEPT
				 ));
		corsConfiguration.setAllowedMethods(Arrays.asList(
				 HttpMethod.GET.name(),
				 HttpMethod.POST.name(),
				 HttpMethod.PUT.name(),
				 HttpMethod.DELETE.name()
				 ));
		
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
}
