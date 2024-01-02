package com.lord.itemmanagementservice;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculateSharesTest {

	
	@Test
	void calculateMaxShare() {
		BigDecimal price = new BigDecimal(10000);
		int maxShare = 12;
		BigDecimal interest = new BigDecimal(50);
		BigDecimal priceByMaxShare = price.multiply(interest);
		priceByMaxShare = priceByMaxShare.divide(new BigDecimal(100));
		priceByMaxShare = price.add(priceByMaxShare);
		priceByMaxShare = priceByMaxShare.divide(new BigDecimal(maxShare),RoundingMode.HALF_UP);
		
		assertEquals(priceByMaxShare.doubleValue(), 1250.00);
	}
	
	@Test
	void calculatePricesByShare() {

		BigDecimal price = new BigDecimal(10000);
		int maxShare = 18;
		BigDecimal interest = new BigDecimal(50);

		List<BigDecimal> prices = new ArrayList<>();
		BigDecimal itemInterest =  price.multiply(interest);
		itemInterest = itemInterest.divide(new BigDecimal(100));
		BigDecimal priceWithInterest = price.add(itemInterest);
		System.out.println(price.doubleValue());
		List<BigDecimal> shares = List.of(new BigDecimal(1), new BigDecimal(3), new BigDecimal(6), new BigDecimal(12),
				new BigDecimal(18));
		for (BigDecimal share : shares) {
			if (share.intValue() > maxShare) {
				break;
			} else {
				BigDecimal totalByShare =  priceWithInterest.divide(share,RoundingMode.HALF_UP);
				prices.add(totalByShare);
			}
		}
		
		assertEquals(price.doubleValue(), 10000.00);
		assertEquals(prices.get(0).doubleValue(), 15000.00);
		assertEquals(prices.get(1).doubleValue(), 5000.00);
		assertEquals(prices.get(2).doubleValue(), 2500.00);
		assertEquals(prices.get(3).doubleValue(), 1250.00);

	}
}
