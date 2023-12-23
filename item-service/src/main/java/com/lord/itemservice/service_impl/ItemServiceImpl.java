package com.lord.itemservice.service_impl;

import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.mapper.ItemMapper;
import com.lord.itemservice.model.Item;
import com.lord.itemservice.repository.ItemRepository;
import com.lord.itemservice.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private final ItemRepository itemRepository;
	
	@Autowired
	private final ItemMapper itemMapper;
	
	final int ITEM_SKU_LENGTH = 12;
	
	final String CHARACTERS = "0123456789abcdefghyjklmnopqrstuvwxyz";
	
	public ItemServiceImpl(ItemRepository itemRepository,ItemMapper itemMapper) {
		super();
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	@Override
	public List<ItemDto> findAllByProductId(Long productId) {
	List<Item> items = itemRepository.findAllByProductId(productId);
	List<ItemDto> itemsDto =  itemMapper.toItemsDto(items);
	return itemsDto;
	
	}

	@Override
	public ItemDto findById(String id) {
		Item item = itemRepository.findById(new ObjectId(id)).orElseThrow(()-> new RuntimeException("Item not found"));
		return itemMapper.toItemDto(item);
	}

	@Override
	public String save(ItemDto itemDto) {
		Item item = itemMapper.toItem(itemDto);
		item.setItemSku(RandomStringUtils.random(ITEM_SKU_LENGTH,CHARACTERS));
		return itemRepository.save(item).getId().toString();
		}

	@Override
	public ItemDto findByItemSku(String itemSku) {
	Item item =  itemRepository.findByItemSku(itemSku).orElseThrow(()-> new RuntimeException("Item not found"));
	return itemMapper.toItemDto(item);
	}
	
	
}
