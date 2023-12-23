package com.lord.itemservice.service_impl;

import java.util.List;

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
	
	private final ItemMapper itemMapper;
	
	public ItemServiceImpl(ItemRepository itemRepository,ItemMapper itemMapper) {
		super();
		this.itemRepository = itemRepository;
		this.itemMapper = itemMapper;
	}

	@Override
	public List<Item> findAllByProductId(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item findById(ObjectId id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(ItemDto itemDto) {
		Item item = itemMapper.toItem(itemDto);
		return itemRepository.save(item).getId().toString();
		
		
	}
	
	
}
