package com.lord.itemservice;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.dto.ItemStockDto;
import com.lord.itemservice.model.Item;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
@TestInstance(Lifecycle.PER_CLASS)
public class StockToItemTest {

	final int ITEM_SKU_LENGTH = 12;

	final String CHARACTERS = "0123456789abcdefghyjklmnopqrstuvwxyz";

	private List<Item> items;

	private List<ItemStockDto> stocks;

	private List<ItemDto> itemsDto;

	@Test
	@Order(1)
	void setup() {
		Item item1 = new Item();
		item1.setId(new ObjectId());
		item1.setItemName("Motorola Catch");
		item1.setDescription("16 inch");
		item1.setColor("White");
		item1.setProductId(1L);
		item1.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH, CHARACTERS));
		item1.setPrice(new BigDecimal(2343));

		Item item2 = new Item();
		item2.setId(new ObjectId());
		item2.setItemName("Motorola Catch");
		item2.setDescription("16 inch");
		item2.setColor("Red");
		item2.setProductId(1L);
		item2.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH, CHARACTERS));
		item2.setPrice(new BigDecimal(2343));

		Item item3 = new Item();
		item3.setId(new ObjectId());
		item3.setItemName("Motorola Catch");
		item3.setDescription("16 inch");
		item3.setColor("Red");
		item3.setProductId(1L);
		item3.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH, CHARACTERS));
		item3.setPrice(new BigDecimal(2343));

		ItemStockDto stockDto1 = new ItemStockDto();
		stockDto1.setItemId(item1.getId().toString());
		stockDto1.setQuantity(20);

		ItemStockDto stockDto2 = new ItemStockDto();
		stockDto2.setItemId(item2.getId().toString());
		stockDto2.setQuantity(30);

		ItemStockDto stockDto3 = new ItemStockDto();
		stockDto3.setItemId(item3.getId().toString());
		stockDto3.setQuantity(10);

		items = List.of(item1, item2, item3);

		stocks = List.of(stockDto1, stockDto3, stockDto2);

		assertEquals(items.get(0).getId().toString(), stocks.get(0).getItemId());
		assertEquals(items.get(1).getId().toString(), stocks.get(2).getItemId());
		assertEquals(items.get(2).getId().toString(), stocks.get(1).getItemId());

	}

	@Test
	@Order(2)
	void ShouldMapStockItemToItem_AndReturnItemDto() {

		itemsDto = items.stream().map(item -> {
			ItemDto itemDto = new ItemDto();
			itemDto.setId(item.getId().toString());
			itemDto.setItemName(item.getItemName());
			itemDto.setDescription(item.getDescription());
			itemDto.setColor(item.getColor());
			itemDto.setProductId(item.getProductId());
			itemDto.setQuantity(stocks.stream().filter(stock -> stock.getItemId().equals(item.getId().toString()))
					.findFirst().get().getQuantity());
			itemDto.setItemSku(item.getItemSku());
			itemDto.setPrice(item.getPrice());
			return itemDto;
		}).toList();

		assertEquals(itemsDto.stream().filter(i -> i.getId().equals(stocks.get(0).getItemId()))
				.findFirst().get().getQuantity(), stocks.get(0).getQuantity());
		assertEquals(itemsDto.stream().filter(i -> i.getId().equals(stocks.get(1).getItemId()))
				.findFirst().get().getQuantity(), stocks.get(1).getQuantity());
		assertEquals(itemsDto.stream().filter(i -> i.getId().equals(stocks.get(2).getItemId()))
				.findFirst().get().getQuantity(), stocks.get(2).getQuantity());
		
		
		
	}

}
