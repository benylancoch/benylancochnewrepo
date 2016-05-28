package com.airprz.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.airprz.model.PromoCode;


public interface PromoCodeService {
	
	PromoCode getPromoCode(Long codeId);
	
	List<PromoCode> getPromoCodes(boolean valid);
	
	PromoCode addPromoCode(String code, String description, BigDecimal discount, String multiple, Date validFrom, Date validTo);
	
	PromoCode updatePromoCode(Long codeId, String code, String description, BigDecimal discount, String multiple, String used, Date validFrom, Date validTo);
	
	void deletePromoCode(Long codeId);

}
