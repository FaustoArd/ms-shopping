package com.lord.apigateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
	
	@Bean
	SecurityWebFilterChain webFilterChain(ServerHttpSecurity serverHttp) {
		serverHttp.csrf(csrf -> csrf.disable())
		.authorizeExchange(exchange -> {
			exchange.pathMatchers("/eureka/**").hasRole("USER");
			exchange.anyExchange().authenticated();
		});
		serverHttp.oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt);
		return serverHttp.build();
	}

}
