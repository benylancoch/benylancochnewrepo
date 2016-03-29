package com.airprz.service.impl;

import java.util.List;

import com.airprz.data.FlightSeatDao;
import com.airprz.data.impl.FlightSeatDaoImpl;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.Plane;
import com.airprz.service.FlightSeatService;

public class FlightSeatServiceImpl implements FlightSeatService {
	
	private final FlightSeatDao flightSeatDao;
	
	public FlightSeatServiceImpl() {
		this.flightSeatDao = new FlightSeatDaoImpl();
	}
	
	@Override
	public FlightSeat checkIfFree(Long seatNo, Long flightId) {
		FlightSeat flightSeat = null;
		
		if (seatNo != null && flightId != null && !"".equals(seatNo)) {
			flightSeat = flightSeatDao.checkIfFree(seatNo, flightId);
		}
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat getFlightSeat(Long flightSeatId) {
		return flightSeatDao.getFlightSeat(flightSeatId);
	}
	
	@Override
	public List<FlightSeat> searchForFree(Long seatClass, String location,
			Long flightId) {
		return flightSeatDao.searchForFree(seatClass, location, flightId);
	}
	
	@Override
	public List<FlightSeat> getSeats(Long flightId) {
		return flightSeatDao.getSeats(flightId);
	}
	
	@Override
	public FlightSeat addFlightSeat(Long SeatClass, Long seatNo,
			String whereLocation, String planeNo, Long flightId) {
		FlightSeat flightSeat = null;
		Plane plane = new Plane();
		Flight flight = new Flight();
		
		if (SeatClass != null && seatNo != null && whereLocation != null && planeNo != null && flightId != null
				&& !"".equals(whereLocation) && !"".equals(planeNo)) {
			flightSeat = new FlightSeat();
			flightSeat.setFsClass(SeatClass);
			flightSeat.setSeatNo(seatNo);
			flightSeat.setWhereLoc(whereLocation);
			plane.setPlaneId(planeNo);
			flight.setFlightId(flightId);
			flightSeat.setPlane(plane);
			flightSeat.setFlight(flight);
			flightSeat = flightSeatDao.saveFlightSeat(flightSeat);
		}
		else {
			flightSeat = null;
		}
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat updateFlightSeat(Long flightSeatId, Long SeatClass,
			Long seatNo, String free, String whereLocation, String planeNo, Long flightId) {
		FlightSeat flightSeat = flightSeatDao.getFlightSeat(flightSeatId);
		Plane plane = new Plane();
		Flight flight = new Flight();
		
		if (flightSeat != null) {
			flightSeat = new FlightSeat();
			flightSeat.setFsId(flightSeatId);
			flightSeat.setFsClass(SeatClass);
			flightSeat.setSeatNo(seatNo);
			flightSeat.setFree(free);
			flightSeat.setWhereLoc(whereLocation);
			plane.setPlaneId(planeNo);
			flight.setFlightId(flightId);
			flightSeat.setPlane(plane);
			flightSeat.setFlight(flight);
			flightSeat = flightSeatDao.saveFlightSeat(flightSeat);
		}
		else {
			flightSeat = null;
		}
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat bookSeat(Long seatNo, Long flightId) {
		FlightSeat flightSeat = null;
		
		if (seatNo != null && flightId != null && !"".equals(seatNo)) {
			flightSeat = flightSeatDao.bookSeat(seatNo, flightId);
		}
		
		return flightSeat;
	}
	
	@Override
	public void deleteFlightSeat(Long flightSeatId) {
		
		FlightSeat flightSeat = flightSeatDao.getFlightSeat(flightSeatId);
		if (flightSeat != null) {
			flightSeatDao.deleteFlightSeat(flightSeatId);
		}
		
	}

}
