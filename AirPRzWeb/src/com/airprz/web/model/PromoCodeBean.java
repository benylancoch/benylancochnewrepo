package com.airprz.web.model;

import java.math.BigDecimal;
import java.util.Date;
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
	private Date validFrom;
	private Date validTo;
	
	private Boolean usedForm;
	
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

	public PromoCodeService getPromoCode() {
		return promoCode;
	}
	
	public Boolean getUsedForm() {
		return usedForm;
	}

	public void setUsedForm(Boolean usedForm) {
		this.usedForm = usedForm;
	}
	
//	public List<PromoCode> getListPromoCodes(Boolean used) {
//		return promoCode.getPromoCodes(used);
//	}
	public List<PromoCode> listPromoCodes(Boolean usedForm) {
		return promoCode.getPromoCodes(usedForm);
	}


}
