package com.airprz.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Tax;

public interface TaxService {
	
	Tax getTax(Long taxId);
	
	List<Tax> getTaxes(boolean oldTaxes);
	
	Tax addTax(BigDecimal value, String description, Timestamp validFrom, Timestamp validTo);
	
	Tax updateTax(Long taxId, BigDecimal value, String description, Timestamp validFrom, Timestamp validTo);
	
	void deleteTax(Long taxId);

}
