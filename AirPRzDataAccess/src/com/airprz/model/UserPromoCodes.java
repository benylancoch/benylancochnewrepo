package com.airprz.model;

public class UserPromoCodes {
	
	private Long upcId;
	private User user;
	private PromoCode promoCode;
	
	public Long getUpcId() {
		return upcId;
	}
	public void setUpcId(Long upcId) {
		this.upcId = upcId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PromoCode getPromoCode() {
		return promoCode;
	}
	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}
	

}
