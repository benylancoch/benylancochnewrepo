package com.airprz.web.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.BasketSelectedFlight;
import com.airprz.model.Flight;
import com.airprz.model.PromoCode;
import com.airprz.model.Tax;
import com.airprz.model.UserPromoCode;
import com.airprz.service.PromoCodeService;
import com.airprz.service.TaxService;
import com.airprz.service.UserPromoCodeService;
import com.airprz.service.impl.PromoCodeServiceImpl;
import com.airprz.service.impl.TaxServiceImpl;
import com.airprz.service.impl.UserPromoCodeServiceImpl;
import com.airprz.web.model.BasketBean;
import com.airprz.web.model.UserBean;

@ManagedBean
@SessionScoped
public class BasketController {
	
	private final TaxService taxService;
	private final PromoCodeService promoCodeService;
	private final UserPromoCodeService userPromoCodeService;
	
	@ManagedProperty("#{basketBean}")
	private BasketBean basketBean;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public BasketController() {
		taxService = new TaxServiceImpl();
		promoCodeService = new PromoCodeServiceImpl();
		userPromoCodeService = new UserPromoCodeServiceImpl();
	}
	
	public void addToBasket(List<Flight> selectedFlights, String classSelected) {
		BasketSelectedFlight temp = new BasketSelectedFlight();
		
		BigDecimal tempTotal = new BigDecimal("0.0");
		
		for(Flight item: selectedFlights) {
			tempTotal = tempTotal.add(item.getBasePrice());
		}

		if(classSelected.equals("ECONOMY")) {
			tempTotal = tempTotal.multiply(new BigDecimal("1"));
		}
		else if(classSelected.equals("BUSINESS")) {
			tempTotal = tempTotal.multiply(new BigDecimal("1.5"));
		}
		else if(classSelected.equals("FIRSTCLASS")) {
			tempTotal = tempTotal.multiply(new BigDecimal("2.5"));
		}
		
		
		temp.setSelectedFlight(selectedFlights);
		temp.setClassSelected(classSelected);
		temp.setClassPrice(tempTotal.setScale(2, BigDecimal.ROUND_HALF_UP));
		temp.setNoOfTickets(new Long(1));
		
		basketBean.addFlightToBasket(temp);
	}
	
	public String removeTicket(BasketSelectedFlight ticket) {
		
		basketBean.getBasketFlights().remove(ticket);
		
		return "index?faces-redirect=true";
	}
	
	public BigDecimal calculateTotal() {
		BigDecimal temp = new BigDecimal("0");
		
		for (BasketSelectedFlight item : basketBean.getBasketFlights()) {
			for (Flight flight : item.getSelectedFlight()) {
				BigDecimal subTotal = new BigDecimal("0");
				subTotal = subTotal.add(item.getClassPrice());
				subTotal = subTotal.multiply(new BigDecimal(item.getNoOfTickets()));
				
				temp = temp.add(subTotal);
			}
		}
		
		basketBean.setTotal(temp.setScale(2, BigDecimal.ROUND_HALF_UP));
		return temp.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal calculateTax() {
		BigDecimal tmp = basketBean.getTotal();
		Tax tax = taxService.getCurrentTax();
		
		tmp = tmp.subtract(basketBean.getDiscountValue());
		tmp = tmp.multiply(tax.getValue());
		
		basketBean.setTaxTotal(tmp.setScale(2, BigDecimal.ROUND_HALF_UP));
		return tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public BigDecimal calculateGrandTotal() {
		
		BigDecimal tmp = basketBean.getTotal();
		
		tmp = tmp.subtract(basketBean.getDiscountValue());
		tmp = tmp.add(basketBean.getTaxTotal());
		
		basketBean.setGrandTotal(tmp.setScale(2, BigDecimal.ROUND_HALF_UP));
		return tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
public BigDecimal calculateDiscountedTotal() {
		
		BigDecimal tmp = basketBean.getTotal();
		
		tmp = tmp.multiply(basketBean.getPromoCode().getDiscount());
		
		basketBean.setDiscountValue(tmp.setScale(2, BigDecimal.ROUND_HALF_UP));
		
		tmp = basketBean.getTotal();
		tmp = tmp.subtract(basketBean.getDiscountValue());
		return tmp.setScale(2, BigDecimal.ROUND_HALF_UP);
	}
	
	public void checkDiscount() {
		PromoCode tmpPromoCode = promoCodeService.searchForPromoCode(basketBean.getPromoCode().getCode());
		if (tmpPromoCode != null) {
			UserPromoCode tmp = userPromoCodeService.checkIfUserUsedPromoCode(userBean.getUserId(), tmpPromoCode.getCodeId());
			if (tmp == null) {
				basketBean.setPromoCode(tmpPromoCode);
			}
			else {
				basketBean.setPromoCode(null);
			}
		}
		else {
			basketBean.setPromoCode(null);
		}
	}

	public void setBasketBean(BasketBean basketBean) {
		this.basketBean = basketBean;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
