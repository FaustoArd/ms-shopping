package com.lord.itemstockservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lord.itemstockservice.dto.ItemStockDto;
import com.lord.itemstockservice.service.ItemStockService;

@RestController
@RequestMapping("/api/item-stock")
public class ItemStockController {
	
	@Autowired
	private final ItemStockService itemStockService;

	public ItemStockController( ItemStockService itemStockService) {
		this.itemStockService = itemStockService;
	}
	
	@GetMapping("/{itemId}")
	ResponseEntity<ItemStockDto> findByItemId(@PathVariable("itemId")String itemId){
		ItemStockDto itemStockDto = itemStockService.findByItemId(itemId);
		return ResponseEntity.ok(itemStockDto);
	}
	@GetMapping("/all")
	ResponseEntity<List<ItemStockDto>> findAllByItemId(@RequestParam("itemsId")List<String> itemsId){
		List<ItemStockDto> itemsStockDto = itemStockService.findAllbyItemId(itemsId);
		return new ResponseEntity<List<ItemStockDto>>(itemsStockDto,HttpStatus.OK);
	}
	
	@PostMapping("/")
	ResponseEntity<ItemStockDto> save(@RequestBody ItemStockDto itemStockDto){
		ItemStockDto savedItemStockDto = itemStockService.save(itemStockDto);
		return new ResponseEntity<ItemStockDto>(savedItemStockDto,HttpStatus.CREATED);
	}
	
	 @GetMapping("/is-in-stock")
	 ResponseEntity<Boolean> isInStock(@RequestParam("itemId")String itemId,@RequestParam("quantity")int quantity){
		 boolean result = itemStockService.isInStock(itemId, quantity);
		 return ResponseEntity.ok(result);
	 }
	
	
}

