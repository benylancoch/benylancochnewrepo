package com.airprz.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.Plane;
import com.airprz.service.PlaneService;
import com.airprz.service.impl.PlaneServiceImpl;

public class PlaneServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenGettingPlane_givenValidPlaneId_itShouldReturnPlaneObject()  {
		final String validPlaneNo = "AIR001";
		final String validManufacturer = "AIRBUS";
		final String validModel = "A380";
		final Long validClasses = new Long(3);
		final Long validSeats = new Long(100);
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.getPlane(validPlaneNo);
		
		//Assert
		Assert.assertNotNull(plane);
		Assert.assertEquals(validPlaneNo, plane.getPlaneId());
		Assert.assertEquals(validManufacturer, plane.getManufacturer());
		Assert.assertEquals(validModel, plane.getModel());
		Assert.assertEquals(validClasses, plane.getClasses());
		Assert.assertEquals(validSeats, plane.getSeats());
	}
	
	@Test
	public void whenGettingPlane_givenInvalidPlaneId_itShouldReturnnull()  {
		final String invalidPlaneNo = "BER001";
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.getPlane(invalidPlaneNo);
		
		//Assert
		Assert.assertNull(plane);
	}
	
	@Test
	public void whenGettingAllPlanes__itShouldReturnPlanesObject()  {
		List<Plane> validPlanes = new ArrayList<Plane>();
		Plane validPlane1 = new Plane();
		validPlane1.setPlaneId("AIR001");
		validPlane1.setManufacturer("AIRBUS");
		validPlane1.setModel("A380");
		validPlane1.setClasses(new Long(3));
		validPlane1.setSeats(new Long(100));
		
		Plane validPlane2 = new Plane();
		validPlane2.setPlaneId("AIR002");
		validPlane2.setManufacturer("AIRBUS");
		validPlane2.setModel("A380");
		validPlane2.setClasses(new Long(3));
		validPlane2.setSeats(new Long(200));
		
		validPlanes.add(validPlane1);
		validPlanes.add(validPlane2);

		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		List<Plane> planes = planeService.getPlanes();
		
		Plane plane1 = planes.get(0);
		Plane plane2 = planes.get(1);
		//Assert
		Assert.assertNotNull(planes);
		Assert.assertEquals(validPlanes.size(), planes.size());
		Assert.assertNotNull(plane1);
		Assert.assertEquals(validPlane1.getPlaneId(), plane1.getPlaneId());
		Assert.assertEquals(validPlane1.getManufacturer(), plane1.getManufacturer());
		Assert.assertEquals(validPlane1.getModel(), plane1.getModel());
		Assert.assertEquals(validPlane1.getClasses(), plane1.getClasses());
		Assert.assertEquals(validPlane1.getSeats(), plane1.getSeats());
		
		Assert.assertNotNull(plane2);
		Assert.assertEquals(validPlane2.getPlaneId(), plane2.getPlaneId());
		Assert.assertEquals(validPlane2.getManufacturer(), plane2.getManufacturer());
		Assert.assertEquals(validPlane2.getModel(), plane2.getModel());
		Assert.assertEquals(validPlane2.getClasses(), plane2.getClasses());
		Assert.assertEquals(validPlane2.getSeats(), plane2.getSeats());
	}
	
	@Test
	public void whenAddingPlane_givenValidPlane_itShouldReturnPlaneObject()  {
		final String validPlaneNo = "AIR003";
		final String validManufacturer = "AIRBUS";
		final String validModel = "A380";
		final Long validClasses = new Long(3);
		final Long validSeats = new Long(500);
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.addPlane(validPlaneNo, validManufacturer, validModel, validClasses, validSeats);
		
		//Assert
		Assert.assertNotNull(plane);
		Assert.assertEquals(validPlaneNo, plane.getPlaneId());
		Assert.assertEquals(validManufacturer, plane.getManufacturer());
		Assert.assertEquals(validModel, plane.getModel());
		Assert.assertEquals(validClasses, plane.getClasses());
		Assert.assertEquals(validSeats, plane.getSeats());
	}
	
	@Test
	public void whenAddingPlane_givenInvalidPlane_itShouldReturnNull()  {
		final String invalidPlaneNo = "";
		final String invalidManufacturer = "";
		final String invalidModel = "";
		final Long invalidClasses = new Long(3);
		final Long invalidSeats = new Long(500);
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.addPlane(invalidPlaneNo, invalidManufacturer, invalidModel, invalidClasses, invalidSeats);
		
		//Assert
		Assert.assertNull(plane);
	}
	
	@Test
	public void whenUpdatingPlane_givenValidPlaneId_itShouldReturnPlaneObject()  {
		final String validPlaneNo = "AIR001";
		final String validManufacturer = "AIRBUS";
		final String validModel = "A380";
		final Long validClasses = new Long(3);
		final Long validSeats = new Long(500);
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.updatePlane(validPlaneNo, validManufacturer, validModel, validClasses, validSeats);
		
		//Assert
		Assert.assertNotNull(plane);
		Assert.assertEquals(validPlaneNo, plane.getPlaneId());
		Assert.assertEquals(validManufacturer, plane.getManufacturer());
		Assert.assertEquals(validModel, plane.getModel());
		Assert.assertEquals(validClasses, plane.getClasses());
		Assert.assertEquals(validSeats, plane.getSeats());
	}
	
	@Test
	public void whenUpdatingPlane_givenInValidPlaneId_itShouldReturnNull()  {
		final String invalidPlaneNo = "AIR033";
		final String validManufacturer = "AIRBUS";
		final String validModel = "A380";
		final Long validClasses = new Long(3);
		final Long validSeats = new Long(500);
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		Plane plane = planeService.updatePlane(invalidPlaneNo, validManufacturer, validModel, validClasses, validSeats);
		
		//Assert
		Assert.assertNull(plane);
	}
	
	@Test
	public void whenDeletingPlane_itShouldReturnNull()  {
		final String validPlaneNo = "AIR001";
		final PlaneService planeService = new PlaneServiceImpl();
		
		//Act
		planeService.deletePlane(validPlaneNo);
		
		Plane plane = planeService.getPlane(validPlaneNo);
		
		//Assert
		Assert.assertNull(plane);
	}

}
