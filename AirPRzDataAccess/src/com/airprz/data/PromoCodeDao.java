package com.airprz.data;

import java.util.List;

import com.airprz.model.PromoCode;

public interface PromoCodeDao {
	
	PromoCode getPromoCode(Long codeId);
	
	PromoCode searchForPromoCode(String promoCode);
	
	List<PromoCode> getPromoCodes(boolean valid);
	
	PromoCode savePromoCode(PromoCode promoCode);
	
	void deletePromoCode(Long codeId);

}
