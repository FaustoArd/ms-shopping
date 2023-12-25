package com.lord.itemservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang.RandomStringUtils;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.lord.itemservice.repository.ItemRepository;

import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ItemControllerTest {
	
	
	final int ITEM_SKU_LENGTH = 12;

	final String CHARACTERS = "0123456789abcdefghyjklmnopqrstuvwxyz";
	
	@LocalServerPort
	private Integer port;
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	
	@Autowired
	private MockMvc mockMvc;
	
	static void configurePropreties(DynamicPropertyRegistry registry) {
		registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	@Autowired
	private ItemRepository itemRepository;
	
	@BeforeAll
	static void beforeAll() {
		mongoDBContainer.start();
	}
	
	@AfterAll
	static void afterAll() {
		mongoDBContainer.stop();
	}
	
	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost:" + port;
		itemRepository.deleteAll();
	}
	
	
	
	@Test
	void shouldSaveItem()throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/item/")
				.content("{\"productName\":\"Topper tennis\",\"description\":\"tennis\",\"color\":\"Black\",\"productId\":1,"
						+ "\"quantity\":10,\"price\":3290}").contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id", is(notNullValue())))
		.andExpect(jsonPath("$.productName", is("Topper tennis")))
		.andExpect(jsonPath("$.description", is("tennis")))
		.andExpect(jsonPath("$.color", is("Black")))
		.andExpect(jsonPath("$.productId", is(1)))
		.andExpect(jsonPath("$.quantity", is(10)))
		.andExpect(jsonPath("$.itemSku", is(notNullValue())))
		.andExpect(jsonPath("$.price", is(3290)));
				
	}

}
