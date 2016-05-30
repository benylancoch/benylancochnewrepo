package com.airprz.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.web.model.BasketBean;

@ManagedBean
@SessionScoped
public class BasketController {
	
	@ManagedProperty("#{basketBean}")
	private BasketBean basketBean;

	public void setBasketBean(BasketBean basketBean) {
		this.basketBean = basketBean;
	}

}
