package com.lord.orderservice;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.apache.groovy.parser.antlr4.GroovyParser.AndExprAltContext;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.PostgreSQLContainer;

import com.lord.orderservice.exception.OutOfStockException;
import com.lord.orderservice.repository.OrderRepository;


import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderServiceTest {
	
	
	@LocalServerPort
	private Integer port;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	MockMvc mockMvc;
	
	MvcResult mvcResult;
	
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine"
			);
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}
	
	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}
	
	@AfterAll
	static void afterAll() {
		postgres.stop();
	}
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost:" + port;
	}
	
	@Test
	void shouldPlaceOrder()throws Exception{
		
		
	 	 mockMvc.perform(post("/api/order/")
				.content("{\"itemId\":\"658b9164521c574b55ea636b\",\"purchaseCart\":1,\"quantity\":10}").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.itemId", is("658b9164521c574b55ea636b")))
		.andExpect(jsonPath("$.orderTotalPrice",is( 76570)))
		.andExpect(jsonPath("$.purchaseCart", is(1)))
		.andExpect(jsonPath("$.quantity", is(10)));
	}
	
	@Test
	void WhenQuantityIsGreaterThanStockThrowException()throws Exception {
		
		mockMvc.perform(post("/api/order/")
					.content("{\"itemId\":\"658b9164521c574b55ea636b\",\"purchaseCart\":1,\"quantity\":100}")
					.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is(417)) 
				 .andExpect(content().string(containsString(("Item out of stock"))));
				
				
		
		
				
			
		
	}

}
