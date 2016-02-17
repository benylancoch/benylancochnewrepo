package com.airprz.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.PromoCode;
import com.airprz.service.PromoCodeService;
import com.airprz.service.impl.PromoCodeServiceImpl;
import com.airprz.web.model.PromoCodeBean;

@ManagedBean
@SessionScoped
public class PromoCodeController {
	
	private final PromoCodeService promoCodeService;
	
	@ManagedProperty("#{promoCodeBean}")
	private PromoCodeBean promoCodeBean;
	
	public PromoCodeController() {
		promoCodeService = new PromoCodeServiceImpl();
	}
	
	public String savePromoCode() {
		
		if(promoCodeBean.getCodeId() == null) {
			promoCodeService.addPromoCode(promoCodeBean.getCode(), promoCodeBean.getDescription(), promoCodeBean.getDiscount(), 
					promoCodeBean.getMultiple(), promoCodeBean.getValidFrom(), promoCodeBean.getValidTo());
		}
		else {
			promoCodeService.updatePromoCode(promoCodeBean.getCodeId(), promoCodeBean.getCode(), promoCodeBean.getDescription(), promoCodeBean.getDiscount(), 
					promoCodeBean.getMultiple(), promoCodeBean.getUsed(), promoCodeBean.getValidFrom(), promoCodeBean.getValidTo());
		}
		
		
		return "index?faces-redirect=true";
	}
	
	public String deletePromoCode(PromoCode promoCode) {
		
		promoCodeBean.setCodeId(promoCode.getCodeId());
		promoCodeBean.setCode(promoCode.getCode());
		promoCodeBean.setDescription(promoCode.getDescription());
		promoCodeBean.setDiscount(promoCode.getDiscount());
		promoCodeBean.setMultiple(promoCode.getMultiple());
		promoCodeBean.setUsed(promoCode.getUsed());
		promoCodeBean.setValidFrom(promoCode.getValidFrom());
		promoCodeBean.setValidTo(promoCode.getValidFrom());
		
		promoCodeService.deletePromoCode(promoCodeBean.getCodeId());
		
		return "index?faces-redirect=true";
	}
	
	public String redirectNew() {
		promoCodeBean.setCodeId(null);
		promoCodeBean.setCode(null);
		promoCodeBean.setDescription(null);
		promoCodeBean.setDiscount(null);
		promoCodeBean.setMultiple(null);
		promoCodeBean.setUsed(null);
		promoCodeBean.setValidFrom(null);
		promoCodeBean.setValidTo(null);
		
		return "new?faces-redirect=true";
	}
	
	public String redirectEdit(PromoCode promoCode) {
		
		promoCodeBean.setCodeId(promoCode.getCodeId());
		promoCodeBean.setCode(promoCode.getCode());
		promoCodeBean.setDescription(promoCode.getDescription());
		promoCodeBean.setDiscount(promoCode.getDiscount());
		promoCodeBean.setMultiple(promoCode.getMultiple());
		promoCodeBean.setUsed(promoCode.getUsed());
		promoCodeBean.setValidFrom(promoCode.getValidFrom());
		promoCodeBean.setValidTo(promoCode.getValidFrom());


		return "new?faces-redirect=true";
	}
	
	public void setPromoCodeBean(PromoCodeBean promoCodeBean) {
		this.promoCodeBean = promoCodeBean;
	}

}
