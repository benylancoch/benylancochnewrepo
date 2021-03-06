package com.airprz.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.airprz.data.PromoCodeDao;
import com.airprz.data.impl.PromoCodeDaoImpl;
import com.airprz.model.PromoCode;
import com.airprz.service.PromoCodeService;

public class PromoCodeServiceImpl implements PromoCodeService {
	
	private final PromoCodeDao promoCodeDao;
	
	public PromoCodeServiceImpl() {
		this.promoCodeDao = new PromoCodeDaoImpl();
	}
	
	@Override
	public PromoCode getPromoCode(Long codeId) {
		PromoCode promoCode = null;
		
		if (codeId != null) {
			promoCode = promoCodeDao.getPromoCode(codeId);
		}
		
		return promoCode;
	}
	
	@Override
	public PromoCode searchForPromoCode(String promoCode) {
		
		return promoCodeDao.searchForPromoCode(promoCode);
	}
	
	@Override
	public List<PromoCode> getPromoCodes(boolean valid) {
		return promoCodeDao.getPromoCodes(valid);
	}
	
	@Override
	public PromoCode addPromoCode(String code, String description,
			BigDecimal discount, String multiple,
			Date validFrom, Date validTo) {
		PromoCode promoCode = null;
		
		if (code != null && description != null && discount != null && multiple != null && validFrom != null
				&& !"".equals(code) && !"".equals(multiple))  {
			promoCode = new PromoCode();
			promoCode.setCode(code);
			promoCode.setDescription(description);
			promoCode.setDiscount(discount);
			promoCode.setMultiple(multiple);
			promoCode.setUsed("N");
			promoCode.setValidFrom(new Timestamp(validFrom.getTime()));
			if(validTo != null) {
				promoCode.setValidTo(new Timestamp(validTo.getTime()));
			}
			else {
				promoCode.setValidTo(null);
			}
			promoCode = promoCodeDao.savePromoCode(promoCode);
		}
		else {
			promoCode = null;
		}
		
		return promoCode;
	}
	
	@Override
	public PromoCode updatePromoCode(Long codeId, String code,
			String description, BigDecimal discount, String multiple,
			String used, Date validFrom, Date validTo) {
		PromoCode promoCode = promoCodeDao.getPromoCode(codeId);
		
		if (promoCode != null) {
			promoCode = new PromoCode();
			promoCode.setCodeId(codeId);
			promoCode.setCode(code);
			promoCode.setDescription(description);
			promoCode.setDiscount(discount);
			promoCode.setMultiple(multiple);
			promoCode.setUsed(used);
			promoCode.setValidFrom(new Timestamp(validFrom.getTime()));
			if(validTo != null) {
				promoCode.setValidTo(new Timestamp(validTo.getTime()));
			}
			else {
				promoCode.setValidTo(null);
			}
			promoCode = promoCodeDao.savePromoCode(promoCode);
		}
		else {
			promoCode = null;
		}
		
		return promoCode;
	}
	
	@Override
	public void deletePromoCode(Long codeId) {
		PromoCode promoCode = promoCodeDao.getPromoCode(codeId);
		if (promoCode != null) {
			promoCodeDao.deletePromoCode(codeId);
		}
		
	}

}
