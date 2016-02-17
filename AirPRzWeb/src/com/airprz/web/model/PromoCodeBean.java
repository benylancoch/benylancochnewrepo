package com.airprz.web.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.PromoCode;
import com.airprz.service.PromoCodeService;
import com.airprz.service.impl.PromoCodeServiceImpl;

@ManagedBean
@SessionScoped
public class PromoCodeBean {
	
	private final PromoCodeService promoCode;
	
	private Long codeId;
	private String code;
	private String description;
	private BigDecimal discount;
	private String multiple;
	private String used;
	private Timestamp validFrom;
	private Timestamp validTo;
	
	public PromoCodeBean() {
		promoCode = new PromoCodeServiceImpl();
	}

	public Long getCodeId() {
		return codeId;
	}

	public void setCodeId(Long codeId) {
		this.codeId = codeId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getMultiple() {
		return multiple;
	}

	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getUsed() {
		return used;
	}

	public void setUsed(String used) {
		this.used = used;
	}

	public Timestamp getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Timestamp validFrom) {
		this.validFrom = validFrom;
	}

	public Timestamp getValidTo() {
		return validTo;
	}

	public void setValidTo(Timestamp validTo) {
		this.validTo = validTo;
	}

	public PromoCodeService getPromoCode() {
		return promoCode;
	}
	
	public List<PromoCode> getListPromoCodes() {
		return promoCode.getPromoCodes(true);
	}

}
