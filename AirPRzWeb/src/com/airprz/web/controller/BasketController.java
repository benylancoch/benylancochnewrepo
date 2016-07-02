package com.airprz.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.airprz.model.BasketSelectedFlight;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.PromoCode;
import com.airprz.model.Tax;
import com.airprz.model.Ticket;
import com.airprz.model.User;
import com.airprz.model.UserPromoCode;
import com.airprz.service.FlightSeatService;
import com.airprz.service.PromoCodeService;
import com.airprz.service.TaxService;
import com.airprz.service.UserPromoCodeService;
import com.airprz.service.impl.FlightSeatServiceImpl;
import com.airprz.service.impl.PromoCodeServiceImpl;
import com.airprz.service.impl.TaxServiceImpl;
import com.airprz.service.impl.UserPromoCodeServiceImpl;
import com.airprz.web.model.BasketBean;
import com.airprz.web.model.SeatSearchBean;
import com.airprz.web.model.UserBean;

@ManagedBean
@SessionScoped
public class BasketController {
	
	private final TaxService taxService;
	private final PromoCodeService promoCodeService;
	private final UserPromoCodeService userPromoCodeService;
	private final FlightSeatService flightSeatService;
	
	@ManagedProperty("#{basketBean}")
	private BasketBean basketBean;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty("#{seatSearchBean}")
	private SeatSearchBean seatSearchBean;
	
	public BasketController() {
		taxService = new TaxServiceImpl();
		promoCodeService = new PromoCodeServiceImpl();
		userPromoCodeService = new UserPromoCodeServiceImpl();
		flightSeatService = new FlightSeatServiceImpl();
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
	
	public String checkout() {
		if (userBean.getUserId() == null) {
			return "/login?faces-redirect=true";
		}
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seatSearchBean", null);
		resetSeatSearchBeanFields();
		
		List<BasketSelectedFlight> tmp = basketBean.getBasketFlights();
		for (BasketSelectedFlight flight : tmp) {
			List<Flight> flightToCompare = new ArrayList<Flight>();
			flightToCompare = flight.getSelectedFlight();
			List<Ticket> tickets = new ArrayList<Ticket>();
			tickets = flight.getTicketsInBasket();
			if (tickets != null) {
				//List<Integer> occurences = new ArrayList<Integer>();
				for (int i = 0; i < flightToCompare.size(); i++) {
					List<Integer> occurences = new ArrayList<Integer>();
					for (int j = 0; j < tickets.size(); j++) {
						if (flightToCompare.get(i).getFlightNo().equals(tickets.get(j).getFlight().getFlightNo())) {
							occurences.add(j);
						}
					}
					
					if (occurences.size() < flight.getNoOfTickets()) {
						seatSearchBean.setSelectedFlight(flightToCompare.get(i));
						if (flight.getClassSelected().equalsIgnoreCase("ECONOMY")) {
							seatSearchBean.setClassSelected(new Long(1));
						}
						else if (flight.getClassSelected().equalsIgnoreCase("BUSINESS")) {
							seatSearchBean.setClassSelected(new Long(2));
						}
						else {
							seatSearchBean.setClassSelected(new Long(3));
						}
						return "chooseseat?faces-redirect=true";
					}
				}
			}
			else {
				seatSearchBean.setSelectedFlight(flightToCompare.get(0));
				if (flight.getClassSelected().equalsIgnoreCase("ECONOMY")) {
					seatSearchBean.setClassSelected(new Long(1));
				}
				else if (flight.getClassSelected().equalsIgnoreCase("BUSINESS")) {
					seatSearchBean.setClassSelected(new Long(2));
				}
				else {
					seatSearchBean.setClassSelected(new Long(3));
				}
				return "chooseseat?faces-redirect=true";
			}
			
		}
		
		return "summary?faces-redirect=true";
	}
	
	public String reserveTicket() {
		List<FlightSeat> returnedSeats = flightSeatService.searchForFree(seatSearchBean.getClassSelected(), 
				seatSearchBean.getRowPlace() + "|" + seatSearchBean.getColumnPlace(), seatSearchBean.getSelectedFlight().getFlightId());
		FlightSeat tmpSeat = flightSeatService.reserveSeat(returnedSeats.get(0).getSeatNo(), returnedSeats.get(0).getFlight().getFlightId());
		
		for (BasketSelectedFlight flight : basketBean.getBasketFlights()) {
			for (int i = 0; i < flight.getSelectedFlight().size(); i++) {
				if (seatSearchBean.getSelectedFlight().getFlightId().equals(flight.getSelectedFlight().get(i).getFlightId())) {
					Ticket tmpTicket = new Ticket();
					tmpTicket.setFlight(seatSearchBean.getSelectedFlight());
					tmpTicket.setFlightSeat(tmpSeat);
					tmpTicket.setPrice(flight.getClassPrice());
					
					User tmpUser = new User();
					tmpUser.setId(userBean.getUserId());
					tmpUser.setEmail(userBean.getEmail());
					tmpUser.setFirstname(userBean.getFirstname());
					tmpUser.setLastname(userBean.getLastname());
					tmpUser.setHonorific(userBean.getHonorific());
					tmpTicket.setUser(tmpUser);
					
					if (seatSearchBean.isDiffernetPerson()) {
						tmpTicket.setOtherUserName(seatSearchBean.getOtherPersonName());
						tmpTicket.setOtherUserPhone(seatSearchBean.getOtherPersonPhone());
						tmpTicket.setOtherUserHonorific(seatSearchBean.getOtherPersonHonorific());
					}
					flight.addTicketsInBasket(tmpTicket);
				}
			}
		}
		if (seatSearchBean.isNextToEachOther()) {
			
		}
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("seatSearchBean", null);
		List<BasketSelectedFlight> tmp = basketBean.getBasketFlights();
		for (BasketSelectedFlight flight : tmp) {
			List<Flight> flightToCompare = new ArrayList<Flight>();
			flightToCompare = flight.getSelectedFlight();
			List<Ticket> tickets = new ArrayList<Ticket>();
			tickets = flight.getTicketsInBasket();
			List<Integer> occurences = new ArrayList<Integer>();
			if (tickets != null) {
				for (int i = 0; i < flightToCompare.size(); i++) {
					//List<Integer> occurences = new ArrayList<Integer>();
					for (int j = 0; j < tickets.size(); j++) {
						if (flightToCompare.get(i).getFlightNo().equals(tickets.get(j).getFlight().getFlightNo())) {
							occurences.add(j);
						}
					}
//					System.out.println("**************************************************");
//					System.out.println(occurences.size());
//					System.out.println(flight.getNoOfTickets());
//					System.out.println("**************************************************");
					if (occurences.size() < flight.getNoOfTickets()) {
						seatSearchBean.setSelectedFlight(flightToCompare.get(i));
						if (flight.getClassSelected().equalsIgnoreCase("ECONOMY")) {
							seatSearchBean.setClassSelected(new Long(1));
						}
						else if (flight.getClassSelected().equalsIgnoreCase("BUSINESS")) {
							seatSearchBean.setClassSelected(new Long(2));
						}
						else {
							seatSearchBean.setClassSelected(new Long(3));
						}
						resetSeatSearchBeanFields();
						return "chooseseat?faces-redirect=true";
					}
				}
			}
			else {
				seatSearchBean.setSelectedFlight(flightToCompare.get(0));
				resetSeatSearchBeanFields();
				return "chooseseat?faces-redirect=true";
			}
			
		}
		
		resetSeatSearchBeanFields();
		return "summary?faces-redirect=true";
	}
	
	public boolean basketIsEmpty() {
		if (basketBean.getBasketFlights().size() > 0) {
			return false;
		}
		else {
			return true;
		}
	}

	public void setBasketBean(BasketBean basketBean) {
		this.basketBean = basketBean;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public void setSeatSearchBean(SeatSearchBean seatSearchBean) {
		this.seatSearchBean = seatSearchBean;
	}
	
	public String whatClass(Long classToCompare) {
		if (classToCompare.equals(new Long(1))) {
			return "ECONOMY";
		}
		else if (classToCompare.equals(new Long(2))) {
			return "BUSINESS";
		}
		else if (classToCompare.equals(new Long(3))) {
			return "FIRST CLASS";
		}
		else {
			return "UNKNOWN CLASS";
		}
	}
	
	private Long longClassFromString(String classString) {
		if (classString.equals(new String("ECONOMY"))) {
			return new Long(1);
		}
		else if (classString.equals(new String("BUSINESS"))) {
			return new Long(2);
		}
		else if (classString.equals(new String("FIRST CLASS"))) {
			return new Long(3);
		}
		else {
			return new Long(99999);
		}
	}
	
	private void resetSeatSearchBeanFields() {
		
		this.seatSearchBean.setRowPlace("S");
		this.seatSearchBean.setColumnPlace("M");
		this.seatSearchBean.setNextToEachOther(false);
		this.seatSearchBean.setDiffernetPerson(false);
		this.seatSearchBean.setOtherPersonHonorific(new Long(0));
		this.seatSearchBean.setOtherPersonName("");
		this.seatSearchBean.setOtherPersonPhone("");
	}

}
