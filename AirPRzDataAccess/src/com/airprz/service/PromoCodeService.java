package com.airprz.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.PromoCode;


public interface PromoCodeService {
	
	PromoCode getPromoCode(Long codeId);
	
	List<PromoCode> getPromoCodes(boolean valid);
	
	PromoCode addPromoCode(String code, String description, BigDecimal discount, String multiple, Timestamp validFrom, Timestamp validTo);
	
	PromoCode updatePromoCode(Long codeId, String code, String description, BigDecimal discount, String multiple, String used, Timestamp validFrom, Timestamp validTo);
	
	void deletePromoCode(Long codeId);

}
