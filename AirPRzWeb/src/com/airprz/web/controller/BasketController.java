package com.airprz.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.BasketSelectedFlight;
import com.airprz.model.Flight;
import com.airprz.web.model.BasketBean;

@ManagedBean
@SessionScoped
public class BasketController {
	
	@ManagedProperty("#{basketBean}")
	private BasketBean basketBean;
	
	public void addToBasket(List<Flight> selectedFlights, String classSelected) {
		BasketSelectedFlight temp = new BasketSelectedFlight();
		
		BigDecimal classMultiplier = new BigDecimal("1.0");
		BigDecimal tempTotal = new BigDecimal("0.0");

		if(classSelected.equals("BUSINESS")) {
			classMultiplier = new BigDecimal("1.5");
		}
		else if(classSelected.equals("FIRSTCLASS")) {
			classMultiplier = new BigDecimal("2.5");
		}
		
		for(Flight item: selectedFlights) {
			tempTotal.add(item.getBasePrice());
		}
		
		temp.setSelectedFlight(selectedFlights);
		temp.setClassSelected(classSelected);
		temp.setClassPrice(tempTotal.multiply(classMultiplier));
		temp.setNoOfTickets(new Long(1));
		
		basketBean.addFlightToBasket(temp);
	}

	public void setBasketBean(BasketBean basketBean) {
		this.basketBean = basketBean;
	}

}
