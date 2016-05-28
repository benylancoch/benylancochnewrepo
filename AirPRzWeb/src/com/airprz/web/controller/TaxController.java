package com.airprz.web.controller;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Tax;
import com.airprz.service.TaxService;
import com.airprz.service.impl.TaxServiceImpl;
import com.airprz.web.model.TaxBean;

@ManagedBean
@SessionScoped
public class TaxController {
	
private final TaxService taxService;
	
	@ManagedProperty("#{taxBean}")
	private TaxBean taxBean;
	
	public TaxController() {
		taxService = new TaxServiceImpl();
	}
	
	public String saveTax() {
		
		if(taxBean.getTaxId() == null) {
			taxService.addTax(taxBean.getValue().divide(new BigDecimal("100")), taxBean.getDescription(), taxBean.getValidFrom(), taxBean.getValidTo());
		}
		else {
			taxService.updateTax(taxBean.getTaxId(), taxBean.getValue().divide(new BigDecimal("100")), taxBean.getDescription(), taxBean.getValidFrom(), taxBean.getValidTo());
		}
		
		
		return "index?faces-redirect=true";
	}
	
	public String deleteTax(Tax tax) {
		
		taxBean.setTaxId(tax.getTaxId());
		taxBean.setValue(tax.getValue());
		taxBean.setDescription(tax.getDescription());
		taxBean.setValidFrom(tax.getValidFrom());
		taxBean.setValidTo(tax.getValidTo());
		
		taxService.deleteTax(taxBean.getTaxId());
		
		return "index?faces-redirect=true";
	}
	
	public String redirectNew() {
		Date date = new Date();
		
		taxBean.setTaxId(null);
		taxBean.setValue(null);
		taxBean.setDescription(null);
		taxBean.setValidFrom(date);
		taxBean.setValidTo(null);
		
		return "new?faces-redirect=true";
	}
	
	public String redirectEdit(Tax tax) {
		
		taxBean.setTaxId(tax.getTaxId());
		taxBean.setValue(tax.getValue().multiply(new BigDecimal("100")));
		taxBean.setDescription(tax.getDescription());
		taxBean.setValidFrom(tax.getValidFrom());
		taxBean.setValidTo(tax.getValidTo());


		return "new?faces-redirect=true";
	}
	
	public void setTaxBean(TaxBean taxBean) {
		this.taxBean = taxBean;
	}
	
	public String showOld(Boolean oldForm) {
		
		taxBean.setOldForm(oldForm);
		
		return "index?faces-redirect=true";
	}

}
