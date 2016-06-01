package com.airprz.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ReferenceSeatsDefineBean {
	
	private Long seatClass;
	private Long seatNoStart;
	private Long seatNoEnd;
	
	public Long getSeatClass() {
		return seatClass;
	}
	public void setSeatClass(Long seatClass) {
		this.seatClass = seatClass;
	}
	public Long getSeatNoStart() {
		return seatNoStart;
	}
	public void setSeatNoStart(Long seatNoStart) {
		this.seatNoStart = seatNoStart;
	}
	public Long getSeatNoEnd() {
		return seatNoEnd;
	}
	public void setSeatNoEnd(Long seatNoEnd) {
		this.seatNoEnd = seatNoEnd;
	}

}
