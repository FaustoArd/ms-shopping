package com.lord.itemmanagementservice.service_impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lord.itemmanagementservice.dto.ItemManagementDto;
import com.lord.itemmanagementservice.dto.ItemManagementShareResponse;
import com.lord.itemmanagementservice.mapper.ItemManagementMapper;
import com.lord.itemmanagementservice.model.ItemManagement;
import com.lord.itemmanagementservice.service.ItemManagementService;
import com.lord.itemmanagementservice.service.ItemManagementServiceDao;
@Service
public class ItemManagementServiceImpl implements ItemManagementService {
	
	private static final List<BigDecimal> shares = List.of(new BigDecimal(1), new BigDecimal(3), new BigDecimal(6), new BigDecimal(12),
			new BigDecimal(18));
	
	@Autowired
	private final ItemManagementServiceDao itemManagementServiceDao;
	
	@Autowired
	private final ItemManagementMapper itemManagementMapper;
	
	public ItemManagementServiceImpl(ItemManagementServiceDao itemManagementServiceDao,ItemManagementMapper itemManagementMapper) {
		this.itemManagementServiceDao = itemManagementServiceDao;
		this.itemManagementMapper = itemManagementMapper;
	}
	
	

	@Override
	public BigDecimal calculatePriceByMaxShare(BigDecimal price, int maxShare, BigDecimal interest) {
		BigDecimal priceByMaxShare = price.multiply(interest);
		priceByMaxShare = priceByMaxShare.divide(new BigDecimal(maxShare));
		return priceByMaxShare;
	}

	@Override
	public Map<Integer,BigDecimal> calculatePricesByShare(BigDecimal price, int maxShare, BigDecimal interest) {
		Map<Integer, BigDecimal> prices =new HashMap<>();
		BigDecimal itemInterest =  price.multiply(interest);
		itemInterest = itemInterest.divide(new BigDecimal(100));
		BigDecimal priceWithInterest = price.add(itemInterest);
		for (BigDecimal share : shares) {
			if (share.intValue() > maxShare) {
				break;
			} else {
				BigDecimal totalByShare =  priceWithInterest.divide(share,RoundingMode.HALF_UP);
				prices.put(share.intValue(), totalByShare);
			}
		}
		return prices;
	}



	@Override
	public List<ItemManagementShareResponse> findByItemIdAndCalculateShares(List<ObjectId> itemId) {
		List<ItemManagementShareResponse> itemShares = itemManagementServiceDao.findAllByItemId(itemId).stream()
				.map(item -> {
				ItemManagementShareResponse itemManagementShareResponse = new ItemManagementShareResponse();
				itemManagementShareResponse.setItemId(item.getItemId().toString());
				itemManagementShareResponse.setMaxShare(item.getMaxShare());
				itemManagementShareResponse.setPriceByMaxShare(calculatePriceByMaxShare(item.getItemPrice(), item.getMaxShare(), item.getInterest()));
				return itemManagementShareResponse;
				}).toList();
		
		return itemShares;
	
	}

	

}
