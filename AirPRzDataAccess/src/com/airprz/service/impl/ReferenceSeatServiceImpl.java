package com.airprz.service.impl;

import java.util.List;

import com.airprz.data.ReferenceSeatDao;
import com.airprz.data.impl.ReferenceSeatDaoImpl;
import com.airprz.model.ReferenceSeat;
import com.airprz.model.Plane;
import com.airprz.service.ReferenceSeatService;

public class ReferenceSeatServiceImpl implements ReferenceSeatService {
	
private final ReferenceSeatDao referenceSeatDao;
	
	public ReferenceSeatServiceImpl() {
		this.referenceSeatDao = new ReferenceSeatDaoImpl();
	}
	
	@Override
	public ReferenceSeat getReferenceSeat(Long referenceSeatId) {
		return referenceSeatDao.getReferenceSeat(referenceSeatId);
	}
	
	@Override
	public List<ReferenceSeat> getSeats(String planeNo) {
		return referenceSeatDao.getSeats(planeNo);
	}
	
	@Override
	public ReferenceSeat addReferenceSeat(Long SeatClass, Long seatNo,
			String whereLocation, String planeNo) {
		ReferenceSeat referenceSeat = null;
		Plane plane = new Plane();
		
		if (SeatClass != null && seatNo != null && whereLocation != null && planeNo != null
				&& !"".equals(whereLocation) && !"".equals(planeNo)) {
			referenceSeat = new ReferenceSeat();
			referenceSeat.setRsClass(SeatClass);
			referenceSeat.setSeatNo(seatNo);
			referenceSeat.setWhereLoc(whereLocation);
			plane.setPlaneId(planeNo);
			referenceSeat.setPlane(plane);
			referenceSeat = referenceSeatDao.saveReferenceSeat(referenceSeat);
		}
		else {
			referenceSeat = null;
		}
		
		return referenceSeat;
	}
	
	public Boolean addMultipleReferenceSeats(Long seatClass, Long seatNoStart, Long seatNoEnd, String planeNo) {
		
		if (seatClass != null && seatNoStart != null && seatNoEnd != null) {
			return referenceSeatDao.addMultipleReferenceSeats(seatClass, seatNoStart, seatNoEnd, planeNo);
		}
		else {
			return false;
		}
	}
	
	@Override
	public ReferenceSeat updateReferenceSeat(Long referenceSeatId, Long SeatClass,
			Long seatNo, String free, String whereLocation, String planeNo) {
		ReferenceSeat referenceSeat = referenceSeatDao.getReferenceSeat(referenceSeatId);
		Plane plane = new Plane();
		
		if (referenceSeat != null) {
			referenceSeat = new ReferenceSeat();
			referenceSeat.setRsId(referenceSeatId);
			referenceSeat.setRsClass(SeatClass);
			referenceSeat.setSeatNo(seatNo);
			referenceSeat.setFree(free);
			referenceSeat.setWhereLoc(whereLocation);
			plane.setPlaneId(planeNo);
			referenceSeat.setPlane(plane);
			referenceSeat = referenceSeatDao.saveReferenceSeat(referenceSeat);
		}
		else {
			referenceSeat = null;
		}
		
		return referenceSeat;
	}
	
	
	@Override
	public void deleteReferenceSeat(Long referenceSeatId) {
		
		ReferenceSeat referenceSeat = referenceSeatDao.getReferenceSeat(referenceSeatId);
		if (referenceSeat != null) {
			referenceSeatDao.deleteReferenceSeat(referenceSeatId);
		}
		
	}

}
