package com.airprz.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Tax {
	
	private Long taxId;
	private BigDecimal value;
	private String description;
	private Timestamp validFrom;
	private Timestamp ValidTo;
	
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
	public Timestamp getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}
	public Timestamp getValidTo() {
		return ValidTo;
	}
	public void setValidTo(Timestamp validTo) {
		ValidTo = validTo;
	}
	

}
