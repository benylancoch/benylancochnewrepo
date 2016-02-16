package com.airprz.service.impl;

import java.util.List;

import com.airprz.data.UserPromoCodeDao;
import com.airprz.data.impl.UserPromoCodeDaoImpl;
import com.airprz.model.PromoCode;
import com.airprz.model.User;
import com.airprz.model.UserPromoCode;
import com.airprz.service.UserPromoCodeService;

public class UserPromoCodeServiceImpl implements UserPromoCodeService {
	
	private final UserPromoCodeDao userPromoCodeDao; 
	
	public UserPromoCodeServiceImpl() {
		this.userPromoCodeDao = new UserPromoCodeDaoImpl();
	}
	
	@Override
	public UserPromoCode checkIfUserUsedPromoCode(Long userId, Long codeId) {
		UserPromoCode userPromoCode = null;
		
		if (userId != null && codeId != null) {
			userPromoCode = userPromoCodeDao.checkIfUserUserPromoCode(userId, codeId);
		}
		
		return userPromoCode;
	}
	
	@Override
	public List<UserPromoCode> getUsers(Long codeId) {
		return userPromoCodeDao.getUsers(codeId);
	}
	
	@Override
	public List<UserPromoCode> getPromoCodes(Long userId) {
		return userPromoCodeDao.getPromoCodes(userId);
	}
	
	@Override
	public UserPromoCode addUserPromoCode(Long userId, Long codeId) {
		UserPromoCode userPromoCode = null;
		User user = new User();
		PromoCode promoCode = new PromoCode();
		
		if (userId != null && codeId != null) {
			userPromoCode = new UserPromoCode();
			user.setId(userId);
			promoCode.setCodeId(codeId);
			userPromoCode.setUser(user);
			userPromoCode.setPromoCode(promoCode);
			userPromoCode = userPromoCodeDao.saveUserPromoCode(userPromoCode);
		}
		else {
			userPromoCode = null;
		}
		
		return userPromoCode;
	}
	
	@Override
	public UserPromoCode updateUserPromoCode(Long userPromoCodeId, Long userId,
			Long codeId) {
		UserPromoCode userPromoCode = userPromoCodeDao.getUserPromoCode(userPromoCodeId);
		User user = new User();
		PromoCode promoCode = new PromoCode();
		
		if (userPromoCode != null) {
			userPromoCode = new UserPromoCode();
			userPromoCode.setUpcId(userPromoCodeId);
			user.setId(userId);
			promoCode.setCodeId(codeId);
			userPromoCode.setUser(user);
			userPromoCode.setPromoCode(promoCode);
			userPromoCode = userPromoCodeDao.saveUserPromoCode(userPromoCode);
		}
		else {
			userPromoCode = null;
		}
		
		return userPromoCode;
	}
	
	@Override
	public void deleteUserPromoCode(Long userPromoCodeId) {
		UserPromoCode userPromoCode = userPromoCodeDao.getUserPromoCode(userPromoCodeId);
		if (userPromoCode != null) {
			userPromoCodeDao.deleteUserPromoCode(userPromoCodeId);
		}
		
	}

}
