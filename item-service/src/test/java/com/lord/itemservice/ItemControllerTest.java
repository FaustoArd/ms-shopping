package com.lord.itemservice;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.Arrays;

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
import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.repository.ItemRepository;
import com.lord.itemservice.service.ItemService;
import io.restassured.RestAssured;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc

public class ItemControllerTest {

	final int ITEM_SKU_LENGTH = 12;

	final String CHARACTERS = "0123456789abcdefghyjklmnopqrstuvwxyz";

	@LocalServerPort
	private Integer port;

	@Autowired
	private ItemService itemService;

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
	void shouldSaveItem() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/api/item/").content(
				"{\"productName\":\"Topper tennis\",\"description\":\"tennis\",\"color\":\"Black\",\"productId\":1,"
						+ "\"quantity\":10,\"price\":3290}")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id", is(notNullValue())))
				.andExpect(jsonPath("$.itemName", is("Topper tennis")))
				.andExpect(jsonPath("$.description", is("tennis"))).andExpect(jsonPath("$.color", is("Black")))
				.andExpect(jsonPath("$.productId", is(1))).andExpect(jsonPath("$.quantity", is(10)))
				.andExpect(jsonPath("$.itemSku", is(notNullValue()))).andExpect(jsonPath("$.price", is(3290)))
				.andReturn();
	}

	@Test
	void shouldGetItem() throws Exception {
		ItemDto item1 = new ItemDto();
		item1.setItemName("Motorola Catch");
		item1.setDescription("16 inch");
		item1.setColor("White");
		item1.setProductId(1L);
		item1.setQuantity(20);
		item1.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH, CHARACTERS));
		item1.setPrice(new BigDecimal(2343));
		ItemDto savedItem = itemService.save(item1);

		mockMvc.perform(get("/api/item/" + savedItem.getId()).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", is(savedItem.getId())))
				.andExpect(jsonPath("$.itemName", is("Motorola Catch")))
				.andExpect(jsonPath("$.description", is("16 inch"))).andExpect(jsonPath("$.color", is("White")))
				.andExpect(jsonPath("$.productId", is(1))).andExpect(jsonPath("$.quantity", is(20)))
				.andExpect(jsonPath("$.itemSku", is(notNullValue()))).andExpect(jsonPath("$.price", is(2343)));

	}

	@Test
	void shouldGetItemListWithStockIncluded() throws Exception {
		ItemDto item1 = new ItemDto();
		item1.setItemName("Motorola Catch");
		item1.setDescription("16 inch");
		item1.setColor("White");
		item1.setProductId(1L);
		item1.setQuantity(20);
		item1.setPrice(new BigDecimal(2343));
		ItemDto savedItem1 = itemService.save(item1);

		ItemDto item2 = new ItemDto();
		item2.setItemName("Motorola Catch");
		item2.setDescription("17 inch");
		item2.setColor("Red");
		item2.setProductId(1L);
		item2.setQuantity(10);
		item2.setPrice(new BigDecimal(22343));
		ItemDto savedItem2 = itemService.save(item2);

		mockMvc.perform(get("/api/item/all/" + 1).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.[0].id", is(notNullValue())))
				.andExpect(jsonPath("$.[0].productName", is("Motorola Catch")))
				.andExpect(jsonPath("$.[0].description", is("16 inch"))).andExpect(jsonPath("$.[0].color", is("White")))
				.andExpect(jsonPath("$.[0].productId", is(1))).andExpect(jsonPath("$.[0].quantity", is(20)))
				.andExpect(jsonPath("$.[0].itemSku", is(savedItem1.getItemSku())))
				.andExpect(jsonPath("$.[0].price", is(2343))).andExpect(jsonPath("$.[1].id", is(notNullValue())))
				.andExpect(jsonPath("$.[1].productName", is("Motorola Catch")))
				.andExpect(jsonPath("$.[1].description", is("17 inch"))).andExpect(jsonPath("$.[1].color", is("Red")))
				.andExpect(jsonPath("$.[1].productId", is(1))).andExpect(jsonPath("$.[1].quantity", is(10)))
				.andExpect(jsonPath("$.[1].itemSku", is(savedItem2.getItemSku())))
				.andExpect(jsonPath("$.[1].price", is(22343)));

	}

}
