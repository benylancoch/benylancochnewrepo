package com.airprz.web.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Tax;
import com.airprz.service.TaxService;
import com.airprz.service.impl.TaxServiceImpl;

@ManagedBean
@SessionScoped
public class TaxBean {
	
	private final TaxService taxService;
	
	private Long taxId;
	private BigDecimal value;
	private String description;
	private Date validFrom;
	private Date validTo;
	
	private Boolean oldForm;
	
	public TaxBean() {
		taxService = new TaxServiceImpl();
		oldForm = false;
	}

	public Long getTaxId() {
		return taxId;
	}

	public void setTaxId(Long taxId) {
		this.taxId = taxId;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public TaxService getTaxService() {
		return taxService;
	}
	
	public Boolean getOldForm() {
		return oldForm;
	}
	
	public void setOldForm(Boolean oldForm) {
		this.oldForm = oldForm;
	}
	
	public List<Tax> listTaxes(Boolean showOld) {
		return taxService.getTaxes(showOld);
	}

}
