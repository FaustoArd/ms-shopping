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
import com.lord.itemmanagementservice.model.ItemManagement;
import com.lord.itemmanagementservice.repository.ItemManagementRepository;
import com.lord.itemmanagementservice.service.ItemManagementService;

@Service
public class ItemManagementServiceImpl implements ItemManagementService {
	
	private static final List<BigDecimal> shares = List.of(new BigDecimal(1), new BigDecimal(3), new BigDecimal(6), new BigDecimal(12),
			new BigDecimal(18));
	
	@Autowired
	private final ItemManagementRepository itemManagementRepository;
	
	public ItemManagementServiceImpl(ItemManagementRepository itemManagementRepository) {
		this.itemManagementRepository = itemManagementRepository;
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
	public List<ItemManagement> findByProductId(Long productId) {
		return itemManagementRepository.findByProductId(productId);
	}

	@Override
	public List<ItemManagement> findAllByItemId(List<ObjectId> itemId) {
	return itemManagementRepository.findAllByItemId(itemId);
	}

	@Override
	public ItemManagement save(ItemManagementDto itemManagementDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemManagement findByItemId(ObjectId itemId) {
		// TODO Auto-generated method stub
		return null;
	}

}
