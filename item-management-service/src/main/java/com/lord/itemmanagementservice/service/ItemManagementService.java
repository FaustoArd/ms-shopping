package com.lord.itemmanagementservice.service;

import java.math.BigDecimal;
import java.util.List;

public interface ItemManagementService {
	
	public BigDecimal calculatePriceByMaxShare(BigDecimal price,int maxShare, BigDecimal interest);
	
	public List<BigDecimal> calculatePricesByShare(BigDecimal price,int maxShare, BigDecimal interest);

}
