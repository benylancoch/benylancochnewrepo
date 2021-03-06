package com.airprz.model;

import java.sql.Timestamp;

public class Transaction {
	
	private Long transactionId;
	private Timestamp date;
	private String paidUsing;
	private Timestamp paidDate;
	private Tax tax;
	private User user;
	private PromoCode promoCode;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getPaidUsing() {
		return paidUsing;
	}
	public void setPaidUsing(String paidUsing) {
		this.paidUsing = paidUsing;
	}
	public Timestamp getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Timestamp paidDate) {
		this.paidDate = paidDate;
	}
	public Tax getTax() {
		return tax;
	}
	public void setTax(Tax tax) {
		this.tax = tax;
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
