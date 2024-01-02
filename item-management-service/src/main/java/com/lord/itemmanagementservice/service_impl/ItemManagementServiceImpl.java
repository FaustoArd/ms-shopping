package com.lord.itemmanagementservice.service_impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<BigDecimal> calculatePricesByShare(BigDecimal price, int maxShare, BigDecimal interest) {
		List<BigDecimal> prices = new ArrayList<>();
		BigDecimal itemInterest =  price.multiply(interest);
		itemInterest = itemInterest.divide(new BigDecimal(100));
		BigDecimal priceWithInterest = price.add(itemInterest);
		for (BigDecimal share : shares) {
			if (share.intValue() > maxShare) {
				break;
			} else {
				BigDecimal totalByShare =  priceWithInterest.divide(share,RoundingMode.HALF_UP);
				prices.add(totalByShare);
			}
		}
		return prices;
	}

}
