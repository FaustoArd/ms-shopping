package com.lord.securityservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import com.lord.securityservice.utils.RSAKeyProperties;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	
	@Autowired
	private final RSAKeyProperties keys;
	
	public SecurityConfiguration(RSAKeyProperties keys) {
		this.keys = keys;
	}
	
	@Bean
	AuthenticationManager authManager(UserDetailsService detailsService) {
		DaoAuthenticationProvider daoProvider = new DaoAuthenticationProvider();
		daoProvider.setUserDetailsService(detailsService);
		daoProvider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(daoProvider);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
		http.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> {
			auth.requestMatchers("/api/authorization/**").permitAll();
			auth.requestMatchers("/api/test/**").hasRole("USER");
			auth.anyRequest().authenticated();
			
		});
		
		http.oauth2ResourceServer(oauth -> {
			oauth.jwt(jwt -> {
				jwt.jwtAuthenticationConverter(jwtAuthenticationConverter());
				});
		});
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		
		return http.build();
	}
	
	@Bean
	JwtDecoder jwtDecoder() {
		return NimbusJwtDecoder.withPublicKey(keys.getPublicKey()).build();
	}
	
	 @Bean
	 JwtEncoder jwtEncoder() {
		 JWK jwk = new RSAKey.Builder(keys.getPublicKey()).privateKey(keys.getPrivateKey()).build();
		 JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
		 return new NimbusJwtEncoder(jwks);
	 }
	 
	 @Bean
	 JwtAuthenticationConverter jwtAuthenticationConverter() {
		 JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
			jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("roles");
			jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
			JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
			jwtConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
			return jwtConverter;
	 }
	
	
	
	
	
}
