package com.lord.productservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import com.lord.productservice.model.Product;
import com.lord.productservice.repository.ProductRepository;
import io.restassured.RestAssured;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductServiceTest {
	
	@LocalServerPort
	private Integer port;
	
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(
			"postgres:15-alpine"
			);

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}
	
	@AfterAll
	static void afterAll(){
		postgres.stop();
	}
	
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
	}
	
	@Autowired
	ProductRepository productRepository;
	
	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost:" + port;
		productRepository.deleteAll();
	}
	
	@Autowired
	 MockMvc mockMvc;
	
	@Test
	void shouldGetAllProducts() throws Exception {
		Product p1 = new Product(null, "Adidas corse", 1L, true);
		Product p2 = new Product(null, "Nike fere", 1L, true);
		List<Product> products = new ArrayList<>();
		products.add(p1);
		products.add(p2);
		productRepository.saveAll(products);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/product/all").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", is(notNullValue()))).andExpect(jsonPath("$[0].name",is("Adidas corse")))
		.andExpect(jsonPath("$[0].categoryId", is(1))).andExpect(jsonPath("$[0].available", is(true)))
		.andExpect(jsonPath("$[1].id", is(notNullValue()))).andExpect(jsonPath("$[1].name",is("Nike fere")))
		.andExpect(jsonPath("$[1].categoryId", is(1))).andExpect(jsonPath("$[1].available", is(true)));
	
		
	}
	
	@Test
	void shouldSaveNewProduct()throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product/?categoryId=1")
				.content("{\"name\":\"Topper tennis\",\"available\":true}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.name", is("Topper tennis")))
		.andExpect(jsonPath("$.categoryId", is(1)))
		.andExpect(jsonPath("$.available", is(true)));
	}
}
