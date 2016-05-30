package com.airprz.web.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.BasketSelectedFlight;
import com.airprz.model.PromoCode;

@ManagedBean
@SessionScoped
public class BasketBean {
	
	private List<BasketSelectedFlight> basketFlights;
	private BigDecimal total;
	private PromoCode promoCode;
	
	public BasketBean() {
		basketFlights = new ArrayList<BasketSelectedFlight>();
		total = new BigDecimal(0);
	}

	public List<BasketSelectedFlight> getBasketFlights() {
		return basketFlights;
	}

	public void setBasketFlights(List<BasketSelectedFlight> basketFlights) {
		this.basketFlights = basketFlights;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public PromoCode getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}
	
	public Long getBasketItemsCount() {
		Long tmp = new Long(0);
		
		for (BasketSelectedFlight item : this.basketFlights) {
			tmp = tmp + item.getNoOfTickets();
		}
		
		return tmp;
	}

}
