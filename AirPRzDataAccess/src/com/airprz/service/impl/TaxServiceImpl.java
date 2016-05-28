package com.airprz.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.airprz.data.TaxDao;
import com.airprz.data.impl.TaxDaoImpl;
import com.airprz.model.Tax;
import com.airprz.service.TaxService;

public class TaxServiceImpl implements TaxService {
	
	private final TaxDao taxDao;
	
	public TaxServiceImpl() {
		this.taxDao = new TaxDaoImpl();
	}
	
	@Override
	public Tax getTax(Long taxId) {
		Tax tax = null;
		
		
		if (taxId != null) {
			tax = taxDao.getTax(taxId);
		}
		
		return tax;
	}
	
	@Override
	public List<Tax> getTaxes(boolean oldTaxes) {
		
		return taxDao.getTaxes(oldTaxes);
	}
	
	@Override
	public Tax addTax(BigDecimal value, String description, Date validFrom, Date validTo) {
		Tax tax = null;
		
		if (value != null && description != null && validFrom != null
				&& !"".equals(description)) {
			tax = new Tax();
			tax.setValue(value);
			tax.setDescription(description);
			tax.setValidFrom(new Timestamp(validFrom.getTime()));
			if(validTo != null) {
				tax.setValidTo(new Timestamp(validFrom.getTime()));
			}
			else {
				tax.setValidTo(null);
			}
			tax = taxDao.saveTax(tax);
		}
		else {
			tax = null;
		}
		
		return tax;
	}
	
	@Override
	public Tax updateTax(Long taxId, BigDecimal value, String description, Date validFrom, Date validTo) {
		
		Tax tax = taxDao.getTax(taxId);
		
		if (tax != null) {
			tax = new Tax();
			tax.setTaxId(taxId);
			tax.setValue(value);
			tax.setDescription(description);
			tax.setValidFrom(new Timestamp(validFrom.getTime()));
			if(validTo != null) {
				tax.setValidTo(new Timestamp(validFrom.getTime()));
			}
			else {
				tax.setValidTo(null);
			}
			tax = taxDao.saveTax(tax);
		}
		else {
			tax = null;
		}
		
		return tax;
	}
	
	@Override
	public void deleteTax(Long taxId) {
		
		Tax tax = taxDao.getTax(taxId);
		if (tax != null) {
			taxDao.deleteTax(taxId);
		}
		
	}

}
