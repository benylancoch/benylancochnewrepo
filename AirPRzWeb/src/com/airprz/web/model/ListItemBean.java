package com.airprz.web.model;

import java.util.ResourceBundle;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class ListItemBean {
	@ManagedProperty("#{msg}")
	private ResourceBundle bundle;
	
	private Long id;
	private String value;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getSaveButtonValue() {
		return id == null ?
				bundle.getString("button.add")
				: bundle.getString("button.save");
	}
	
	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
