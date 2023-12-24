package com.lord.itemservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lord.itemservice.dto.ItemDto;
import com.lord.itemservice.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

	@Autowired
	private final ItemService itemService;
	
	private static final Gson gson = new Gson(); 
	
	public ItemController(ItemService itemService) {
		this.itemService = itemService;
	}
	
	@PostMapping("/")
	ResponseEntity<String> save(@RequestBody ItemDto itemDto){
		String itemId = itemService.save(itemDto);
		return new ResponseEntity<String>(gson.toJson(itemId),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<ItemDto> findById(@PathVariable("id")String id){
		ItemDto itemDto = itemService.findById(id);
		return ResponseEntity.ok(itemDto);
	}
	
	@GetMapping("/all/{productId}")
	ResponseEntity<List<ItemDto>> findAllByProductId(@PathVariable("productId")Long productId){
		List<ItemDto> itemsDto = itemService.findAllByProductId(productId);
		return new ResponseEntity<List<ItemDto>>(itemsDto,HttpStatus.OK);
	}
}
