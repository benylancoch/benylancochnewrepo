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
	private BigDecimal discountValue;
	private BigDecimal taxTotal;
	private BigDecimal grandTotal;
	private PromoCode promoCode;
	
	public BasketBean() {
		basketFlights = new ArrayList<BasketSelectedFlight>();
		total = new BigDecimal(0);
		discountValue = new BigDecimal(0);
		taxTotal = new BigDecimal(0);
		grandTotal = new BigDecimal(0);
		promoCode = new PromoCode();
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
	
	public void addFlightToBasket(BasketSelectedFlight flightToAdd) {
		Boolean exist = false;
		int index = -1;
		
		//Finds if that flight is already added to basket. If yes, index of that ticket is returned
		//Checks seat class selected
		for(int i=0; i<this.basketFlights.size(); i++) {
			if (this.getBasketFlights().get(i).getClassSelected().equals(flightToAdd.getClassSelected())) {
				for(int j=0; j<flightToAdd.getSelectedFlight().size(); j++) {
					
					if(this.basketFlights.get(i).getSelectedFlight().get(j).getFlightId() == flightToAdd.getSelectedFlight().get(j).getFlightId()) {
						exist = true;
						continue;
					}
					else {
						exist = false;
						break;
					}
				
				}
			}
			else {
				exist = false;
			}
			index = i;
			if (exist) break;
		}
		
		//Handle adding tickets
		if (exist == false) {
			
			this.basketFlights.add(flightToAdd);
		}
		else {
			
			this.basketFlights.get(index).addTicket(flightToAdd.getNoOfTickets());
		}
	
	}

	public BigDecimal getDiscountValue() {
		return discountValue;
	}

	public void setDiscountValue(BigDecimal discountValue) {
		this.discountValue = discountValue;
	}

	public BigDecimal getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(BigDecimal taxTotal) {
		this.taxTotal = taxTotal;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}

}
