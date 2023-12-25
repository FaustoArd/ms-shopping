package com.lord.itemservice.service_impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.dto.ItemStockDto;
import com.lord.itemservice.mapper.ItemMapper;
import com.lord.itemservice.model.Item;
import com.lord.itemservice.repository.ItemRepository;
import com.lord.itemservice.service.ItemService;

import reactor.core.publisher.Mono;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private final ItemRepository itemRepository;

	

	@Autowired
	private final ItemMapper itemMapper;

	private final WebClient webClient;

	final int ITEM_SKU_LENGTH = 12;

	final String CHARACTERS = "0123456789abcdefghyjklmnopqrstuvwxyz";

	public ItemServiceImpl(ItemRepository itemRepository, ItemMapper itemMapper, WebClient.Builder webClientBuilder) {
		super();
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
		this.webClient = webClientBuilder.baseUrl("http://localhost:8090").build();
	
	}

	@Override
	public List<ItemDto> findAllByProductId(Long productId) {
		List<Item> items = itemRepository.findAllByProductId(productId);
		return itemMapper.toItemsDto(items);
	}

	@Override
	public int findTotalProductQuantity(List<String> itemsId) {
		Mono<List<ItemStockDto>> itemsStockDtoMono = webClient.get()
				.uri("/api/item-stock/all", uriBuilder -> uriBuilder.queryParam("itemsId", itemsId).build()).retrieve()
				.bodyToMono(new ParameterizedTypeReference<List<ItemStockDto>>() {
				});

		return itemsStockDtoMono.block().stream().mapToInt(m -> m.getQuantity()).sum();
	}

	@Override
	public ItemDto findById(String itemId) {
		Item item = itemRepository.findById(new ObjectId(itemId))
				.orElseThrow(() -> new RuntimeException("Item not found"));
		ItemDto itemDto = itemMapper.toItemDto(item);
		try {
			Mono<ItemStockDto> itemStockMono = webClient.get().uri("/api/item-stock/{id}", itemId)
					.accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(ItemStockDto.class);
			itemDto.setQuantity(itemStockMono.block().getQuantity());

		} catch (WebClientException ex) {
			throw new RuntimeException(ex.getMessage());
		}
		return itemDto;

	}

	
	@Override
	public String save(ItemDto itemDto) {
		Item item = itemMapper.toItem(itemDto);
		item.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH, CHARACTERS));
		Item savedItem = itemRepository.save(item);
		if (Integer.toString(itemDto.getQuantity()) == null) {
			return "Item saved, Id: " + savedItem.getId();
		}
		try {
			int savedQuantity = saveStock(savedItem.getId().toString(), itemDto.getQuantity());
			return "Item saved, Id: " + savedItem.getId() + ", Quantity: " + savedQuantity;
		} catch (Exception ex) {
			throw new RuntimeException(ex.getMessage());
		}

	}

	@Override
	public ItemDto findByItemSku(String itemSku) {
		Item item = itemRepository.findByItemSku(itemSku).orElseThrow(() -> new RuntimeException("Item not found"));
		return itemMapper.toItemDto(item);
	}

	/**
	 * This method perform a post request and save stock quantity in
	 * item-stock-service
	 **/
	
	@Override
	public int saveStock(String itemId, int quantity) {
		ItemStockDto itemStockDto = itemMapper.toItemStockDto(itemId, quantity);
		Mono<ItemStockDto> result = webClient.post().uri("api/item-stock/").contentType(MediaType.APPLICATION_JSON)
				.bodyValue(itemStockDto).retrieve().bodyToMono(ItemStockDto.class);
		return result.block().getQuantity();
	}

	@Override
	public BigDecimal getItemPrice(String itemId) {

		return itemRepository.findById(new ObjectId(itemId)).get().getPrice();
	}

}
