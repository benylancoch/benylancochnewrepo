package com.airprz.service;

import java.util.List;

import com.airprz.model.UserPromoCode;

public interface UserPromoCodeService {
	
	public UserPromoCode checkIfUserUsedPromoCode(Long userId, Long codeId);
	
	public List<UserPromoCode> getUsers(Long codeId);
	
	public List<UserPromoCode> getPromoCodes(Long userId);
	
	public UserPromoCode addUserPromoCode(Long userId, Long codeId);
	
	public UserPromoCode updateUserPromoCode(Long userPromoCodeId, Long userId, Long codeId);
	
	public void deleteUserPromoCode(Long userPromoCodeId);

}
