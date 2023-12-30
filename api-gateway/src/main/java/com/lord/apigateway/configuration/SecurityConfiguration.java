package com.lord.apigateway.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.server.ServerWebExchange;

import com.lord.apigateway.utils.RSAKeyProperties;

import reactor.core.publisher.Mono;


public class SecurityConfiguration {
	
	//private final RSAKeyProperties keys;
	
	/*@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;*/
	
	/*public SecurityConfiguration(RSAKeyProperties keys) {
		this.keys = keys;
	}*/
	

	/*SecurityWebFilterChain webFilterChain(ServerHttpSecurity serverHttp) {
		serverHttp.csrf(csrf -> csrf.disable())
		.authorizeExchange(exchange -> {
			exchange.pathMatchers("/eureka/**")
			.permitAll()
			.anyExchange()
			.authenticated();
			serverHttp.oauth2ResourceServer((oauth2) -> oauth2
				    .jwt(Customizer.withDefaults())
				);
		
		
			
		});
	
		serverHttp.oauth2ResourceServer(oauth2 -> oauth2
				.jwt(jwt -> jwt.jwtAuthenticationConverter(reactiveJwtConverter())));
		
		
		return serverHttp.build();
	}*/
	
	/*@Bean
	ReactiveJwtDecoder jwtDecoder() {
		return NimbusReactiveJwtDecoder.withPublicKey(keys.getPublicKey()).build();
	}*/
	
	/*@Bean
	JwtAuthenticationConverter jwtAuthenticationConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
		jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
		 
		return jwtConverter;
	}*/
	
	
	/*ReactiveJwtAuthenticationConverter reactiveJwtConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
	jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		ReactiveJwtAuthenticationConverter reactiveJwtConverter  = new ReactiveJwtAuthenticationConverter();
		reactiveJwtConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter));
		return reactiveJwtConverter;*/
	}

