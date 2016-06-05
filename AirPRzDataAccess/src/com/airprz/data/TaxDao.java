package com.airprz.data;

import java.util.List;

import com.airprz.model.Tax;


public interface TaxDao {
	
	Tax getTax(Long taxId);
	
	Tax getCurrentTax();
	
	List<Tax> getTaxes(boolean oldTaxes);
	
	Tax saveTax(Tax tax);
	
	void deleteTax(Long taxId);

}
