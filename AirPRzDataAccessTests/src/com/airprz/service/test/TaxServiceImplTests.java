package com.airprz.service.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.Tax;
import com.airprz.service.TaxService;
import com.airprz.service.impl.TaxServiceImpl;

public class TaxServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenGettingTax_givenValidTaxId_itShouldReturnTaxObject()  {
		final Long validTaxId = new Long(1);
		final BigDecimal validValue = new BigDecimal("0.23");
		final String validDescription = "POLISH VAT 23%";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-01-01 00:00:00.0");
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.getTax(validTaxId);
		
		//Assert
		Assert.assertNotNull(tax);
		Assert.assertEquals(validTaxId, tax.getTaxId());
		Assert.assertEquals(validValue, tax.getValue());
		Assert.assertEquals(validDescription, tax.getDescription());
		Assert.assertEquals(validValidFrom, tax.getValidFrom());
		Assert.assertEquals(null, tax.getValidTo());
	}
	
	@Test
	public void whenGettingTax_givenInvalidTaxId_itShouldReturnnull()  {
		final Long invalidTaxId = new Long(100);
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.getTax(invalidTaxId);
		
		//Assert
		Assert.assertNull(tax);
	}
	
	@Test
	public void whenGettingAllTaxes__itShouldReturnTaxesObject()  {
		//It should return all taxes in database
		List<Tax> validTaxes = new ArrayList<Tax>();
		Long taxId1 = new Long(1);
		BigDecimal taxValue1 = new BigDecimal("0.23");
		Tax validTax1 = new Tax();
		validTax1.setTaxId(taxId1);
		validTax1.setValue(taxValue1);
		validTax1.setDescription("POLISH VAT 23%");
		validTax1.setValidFrom(Timestamp.valueOf("2016-01-01 00:00:00.0"));
		
		Tax validTax2 = new Tax();
		final Long tax2Id = new Long(2);
		BigDecimal taxValue2 = new BigDecimal("0.09");
		validTax2.setTaxId(tax2Id);
		validTax2.setValue(taxValue2);
		validTax2.setDescription("POLISH VAT 9%");
		validTax2.setValidFrom(Timestamp.valueOf("2016-02-02 00:00:00.0"));
		
		Tax validTax3 = new Tax();
		final Long tax3Id = new Long(3);
		BigDecimal taxValue3 = new BigDecimal("0.09");
		validTax3.setTaxId(tax3Id);
		validTax3.setValue(taxValue3);
		validTax3.setDescription("POLISH VAT 9%");
		validTax3.setValidFrom(Timestamp.valueOf("2016-02-02 00:00:00.0"));
		validTax3.setValidTo(Timestamp.valueOf("2016-02-03 00:00:00.0"));
		
		validTaxes.add(validTax1);
		validTaxes.add(validTax2);
		validTaxes.add(validTax3);

		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		List<Tax> taxes = taxService.getTaxes(true);
		
		Tax tax1 = taxes.get(0);
		Tax tax2 = taxes.get(1);
		Tax tax3 = taxes.get(2);
		//Assert
		Assert.assertNotNull(taxes);
		Assert.assertEquals(validTaxes.size(), taxes.size());
		Assert.assertNotNull(tax1);
		
		Assert.assertEquals(validTax1.getTaxId(), tax1.getTaxId());
		Assert.assertEquals(validTax1.getValue(), tax1.getValue());
		Assert.assertEquals(validTax1.getDescription(), tax1.getDescription());
		Assert.assertEquals(validTax1.getValidFrom(), tax1.getValidFrom());
		Assert.assertEquals(validTax1.getValidTo(), tax1.getValidTo());
		
		Assert.assertNotNull(tax2);
		
		Assert.assertEquals(validTax2.getTaxId(), tax2.getTaxId());
		Assert.assertEquals(validTax2.getValue(), tax2.getValue());
		Assert.assertEquals(validTax2.getDescription(), tax2.getDescription());
		Assert.assertEquals(validTax2.getValidFrom(), tax2.getValidFrom());
		Assert.assertEquals(validTax2.getValidTo(), tax2.getValidTo());
		
		Assert.assertNotNull(tax3);
		
		Assert.assertEquals(validTax3.getTaxId(), tax3.getTaxId());
		Assert.assertEquals(validTax3.getValue(), tax3.getValue());
		Assert.assertEquals(validTax3.getDescription(), tax3.getDescription());
		Assert.assertEquals(validTax3.getValidFrom(), tax3.getValidFrom());
		Assert.assertEquals(validTax3.getValidTo(), tax3.getValidTo());
	}
	
	@Test
	public void whenGettingAllValidTaxes__itShouldReturnTaxsObject()  {
		//It should return only active taxes in database
		List<Tax> validTaxes = new ArrayList<Tax>();
		Long taxId1 = new Long(1);
		BigDecimal taxValue1 = new BigDecimal("0.23");
		Tax validTax1 = new Tax();
		validTax1.setTaxId(taxId1);
		validTax1.setValue(taxValue1);
		validTax1.setDescription("POLISH VAT 23%");
		validTax1.setValidFrom(Timestamp.valueOf("2016-01-01 00:00:00.0"));
		
		Tax validTax2 = new Tax();
		final Long tax2Id = new Long(2);
		BigDecimal taxValue2 = new BigDecimal("0.09");
		validTax2.setTaxId(tax2Id);
		validTax2.setValue(taxValue2);
		validTax2.setDescription("POLISH VAT 9%");
		validTax2.setValidFrom(Timestamp.valueOf("2016-02-02 00:00:00.0"));
		
		validTaxes.add(validTax1);
		validTaxes.add(validTax2);

		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		List<Tax> taxes = taxService.getTaxes(false);
		
		Tax tax1 = taxes.get(0);
		Tax tax2 = taxes.get(1);
		//Assert
		Assert.assertNotNull(taxes);
		Assert.assertEquals(validTaxes.size(), taxes.size());
		Assert.assertNotNull(tax1);
		
		Assert.assertEquals(validTax1.getTaxId(), tax1.getTaxId());
		Assert.assertEquals(validTax1.getValue(), tax1.getValue());
		Assert.assertEquals(validTax1.getDescription(), tax1.getDescription());
		Assert.assertEquals(validTax1.getValidFrom(), tax1.getValidFrom());
		Assert.assertEquals(validTax1.getValidTo(), tax1.getValidTo());
		
		Assert.assertNotNull(tax2);
		
		Assert.assertEquals(validTax2.getTaxId(), tax2.getTaxId());
		Assert.assertEquals(validTax2.getValue(), tax2.getValue());
		Assert.assertEquals(validTax2.getDescription(), tax2.getDescription());
		Assert.assertEquals(validTax2.getValidFrom(), tax2.getValidFrom());
		Assert.assertEquals(validTax2.getValidTo(), tax2.getValidTo());
	}
	
	@Test
	public void whenAddingTax_givenValidTax_itShouldReturnTaxObject()  {
		final Long taxId = new Long(4);
		final BigDecimal taxValue = new BigDecimal("0.09");
		final String description = "POLISH VAT 9%";
		final Timestamp validFrom = Timestamp.valueOf("2016-02-02 00:00:00.0");
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.addTax(taxValue, description, validFrom, null);
		Tax taxCheck = taxService.getTax(tax.getTaxId());
		
		//Assert
		Assert.assertNotNull(tax);
		Assert.assertEquals(taxId, tax.getTaxId());
		Assert.assertEquals(taxValue, tax.getValue());
		Assert.assertEquals(description, tax.getDescription());
		Assert.assertEquals(validFrom, tax.getValidFrom());
		Assert.assertEquals(null, tax.getValidTo());
		
		Assert.assertNotNull(taxCheck);
	}
	
	@Test
	public void whenAddingTax_givenValidTaxWithValidTo_itShouldReturnTaxObject()  {
		final Long taxId = new Long(4);
		final BigDecimal taxValue = new BigDecimal("0.09");
		final String description = "POLISH VAT 9%";
		final Timestamp validFrom = Timestamp.valueOf("2016-02-02 00:00:00.0");
		final Timestamp validTo = Timestamp.valueOf("2016-09-02 00:00:00.0");
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.addTax(taxValue, description, validFrom, validTo);
		Tax taxCheck = taxService.getTax(tax.getTaxId());
		
		//Assert
		Assert.assertNotNull(tax);
		Assert.assertEquals(taxId, tax.getTaxId());
		Assert.assertEquals(taxValue, tax.getValue());
		Assert.assertEquals(description, tax.getDescription());
		Assert.assertEquals(validFrom, tax.getValidFrom());
		Assert.assertEquals(validTo, tax.getValidTo());
		
		Assert.assertNotNull(taxCheck);
	}
	
	@Test
	public void whenAddingTax_givenInvalidTax_itShouldReturnNull()  {
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.addTax(null, null, null, null);
		
		//Assert
		Assert.assertNull(tax);
	}
	
	@Test
	public void whenUpdatingTax_givenValidTaxId_itShouldReturnTaxObject()  {
		final Long taxId = new Long(1);
		final BigDecimal taxValue = new BigDecimal("0.09");
		final String description = "POLISH VAT 9%";
		final Timestamp validFrom = Timestamp.valueOf("2016-02-02 00:00:00.0");
		final Timestamp validTo = Timestamp.valueOf("2016-09-02 00:00:00.0");
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.updateTax(taxId, taxValue, description, validFrom, validTo);
		
		//Assert
		Assert.assertNotNull(tax);
		Assert.assertEquals(taxId, tax.getTaxId());
		Assert.assertEquals(taxValue, tax.getValue());
		Assert.assertEquals(description, tax.getDescription());
		Assert.assertEquals(validFrom, tax.getValidFrom());
		Assert.assertEquals(validTo, tax.getValidTo());
	}
	
	@Test
	public void whenUpdatingTax_givenInValidTaxId_itShouldReturnNull()  {
		final Long taxId = new Long(100);
		final BigDecimal taxValue = new BigDecimal("0.09");
		final String description = "POLISH VAT 9%";
		final Timestamp validFrom = Timestamp.valueOf("2016-02-02 00:00:00.0");
		final Timestamp validTo = Timestamp.valueOf("2016-09-02 00:00:00.0");
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		Tax tax = taxService.updateTax(taxId, taxValue, description, validFrom, validTo);
		
		//Assert
		Assert.assertNull(tax);
	}
	
	@Test
	public void whenDeletingTax_itShouldReturnNull()  {
		final Long validTaxId = new Long(1);
		final TaxService taxService = new TaxServiceImpl();
		
		//Act
		taxService.deleteTax(validTaxId);
		
		Tax tax = taxService.getTax(validTaxId);
		
		//Assert
		Assert.assertNull(tax);
	}

}
