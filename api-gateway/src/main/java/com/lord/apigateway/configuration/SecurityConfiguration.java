package com.lord.apigateway.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtGrantedAuthoritiesConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.web.server.ServerWebExchange;


@Configuration
public class SecurityConfiguration {
	
	/*@Autowired
	private TokenRelayGatewayFilterFactory filterFactory;
	
	@Bean
	RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		  return builder.routes()
		            .route("resource", r -> r.path("/resource")
		              .filters(f -> f.filters(filterFactory.apply())
		                .removeRequestHeader("Cookie"))
		            .uri("http://resource:8081")) 
		            .build();
	}
	
	@Bean
	SecurityWebFilterChain webFilterChain(ServerHttpSecurity serverHttp) {
		serverHttp.csrf(csrf -> csrf.disable())
		.authorizeExchange(exchange -> {
			exchange.pathMatchers("/eureka/**").permitAll();
			exchange.pathMatchers("/api/authorization/**").permitAll();
			exchange.pathMatchers("/api/product/**").hasRole("USER");
			exchange.anyExchange().authenticated();
			
			
			
		});
		serverHttp.oauth2Client(oauth2 -> oauth2.);
	
		return serverHttp.build();
	}
	
	ReactiveJwtAuthenticationConverter reactiveJwtConverter() {
		JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
		jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
	jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
		ReactiveJwtAuthenticationConverter reactiveJwtConverter  = new ReactiveJwtAuthenticationConverter();
		reactiveJwtConverter.setJwtGrantedAuthoritiesConverter(new ReactiveJwtGrantedAuthoritiesConverterAdapter(jwtGrantedAuthoritiesConverter));
		return reactiveJwtConverter;
	*/
}
