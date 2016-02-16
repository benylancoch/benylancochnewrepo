package com.airprz.data;

import java.util.List;

import com.airprz.model.UserPromoCode;

public interface UserPromoCodeDao {
	
	public UserPromoCode checkIfUserUserPromoCode(Long userId, Long codeId);
	
	public List<UserPromoCode> getUsers(Long codeId);
	
	public List<UserPromoCode> getPromoCodes(Long userId);
	
	public UserPromoCode saveUserPromoCode(UserPromoCode userPromoCode);
	
	public UserPromoCode getUserPromoCode(Long userPromoCodeId);
	
	public void deleteUserPromoCode(Long userPromoCodeId);

}
