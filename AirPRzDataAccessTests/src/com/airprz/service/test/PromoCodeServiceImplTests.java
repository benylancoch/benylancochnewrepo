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
import com.airprz.model.PromoCode;
import com.airprz.service.PromoCodeService;
import com.airprz.service.impl.PromoCodeServiceImpl;

public class PromoCodeServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenGettingPromoCode_givenValidPromoCodeId_itShouldReturnPromoCodeObject()  {
		final Long validCodeId = new Long(1);
		final String validCode = "ABC";
		final String validDescription = "ABC DESCRIPTION";
		final BigDecimal validDiscount = new BigDecimal("0.25");
		final String validMultiple = "N";
		final String validUsed = "N";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-01-01 00:00:00.0");
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.getPromoCode(validCodeId);
		
		//Assert
		Assert.assertNotNull(promoCode);
		Assert.assertEquals(validCodeId, promoCode.getCodeId());
		Assert.assertEquals(validCode, promoCode.getCode());
		Assert.assertEquals(validDescription, promoCode.getDescription());
		Assert.assertEquals(validDiscount, promoCode.getDiscount());
		Assert.assertEquals(validMultiple, promoCode.getMultiple());
		Assert.assertEquals(validUsed, promoCode.getUsed());
		Assert.assertEquals(validValidFrom, promoCode.getValidFrom());
		Assert.assertEquals(null, promoCode.getValidTo());
	}
	
	@Test
	public void whenGettingPromoCode_givenInvalidPromoCodeId_itShouldReturnnull()  {
		final Long invalidPromoCodeId = new Long(100);
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.getPromoCode(invalidPromoCodeId);
		
		//Assert
		Assert.assertNull(promoCode);
	}
	
	@Test
	public void whenGettingAllPromoCodes__itShouldReturnPromoCodesObject()  {
		//It should return all promoCodees in database
		List<PromoCode> validPromoCodes = new ArrayList<PromoCode>();
		Long promoCodeId1 = new Long(1);
		BigDecimal promoCodeDiscount1 = new BigDecimal("0.25");
		PromoCode validPromoCode1 = new PromoCode();
		validPromoCode1.setCodeId(promoCodeId1);
		validPromoCode1.setCode("ABC");
		validPromoCode1.setDescription("ABC DESCRIPTION");
		validPromoCode1.setDiscount(promoCodeDiscount1);
		validPromoCode1.setMultiple("N");
		validPromoCode1.setUsed("N");
		validPromoCode1.setValidFrom(Timestamp.valueOf("2016-01-01 00:00:00.0"));
		
		PromoCode validPromoCode2 = new PromoCode();
		Long promoCodeId2 = new Long(2);
		BigDecimal promoCodeDiscount2 = new BigDecimal("0.25");
		validPromoCode2.setCodeId(promoCodeId2);
		validPromoCode2.setCode("DFGH");
		validPromoCode2.setDescription("DFGH DESCRIPTION");
		validPromoCode2.setDiscount(promoCodeDiscount2);
		validPromoCode2.setMultiple("N");
		validPromoCode2.setUsed("N");
		validPromoCode2.setValidFrom(Timestamp.valueOf("2016-01-02 00:00:00.0"));
		
		PromoCode validPromoCode3 = new PromoCode();
		Long promoCodeId3 = new Long(3);
		BigDecimal promoCodeDiscount3 = new BigDecimal("0.25");
		validPromoCode3.setCodeId(promoCodeId3);
		validPromoCode3.setCode("123");
		validPromoCode3.setDescription("123 DESCRIPTION");
		validPromoCode3.setDiscount(promoCodeDiscount3);
		validPromoCode3.setMultiple("N");
		validPromoCode3.setUsed("U");
		validPromoCode3.setValidFrom(Timestamp.valueOf("2016-01-02 00:00:00.0"));
		
		PromoCode validPromoCode4 = new PromoCode();
		Long promoCodeId4 = new Long(4);
		BigDecimal promoCodeDiscount4 = new BigDecimal("0.25");
		validPromoCode4.setCodeId(promoCodeId4);
		validPromoCode4.setCode("456");
		validPromoCode4.setDescription("456 DESCRIPTION");
		validPromoCode4.setDiscount(promoCodeDiscount4);
		validPromoCode4.setMultiple("N");
		validPromoCode4.setUsed("N");
		validPromoCode4.setValidFrom(Timestamp.valueOf("2016-01-02 00:00:00.0"));
		validPromoCode4.setValidTo(Timestamp.valueOf("2016-01-03 00:00:00.0"));
		
		validPromoCodes.add(validPromoCode1);
		validPromoCodes.add(validPromoCode2);
		validPromoCodes.add(validPromoCode3);
		validPromoCodes.add(validPromoCode4);

		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		List<PromoCode> promoCodes = promoCodeService.getPromoCodes(true);
		
		PromoCode promoCode1 = promoCodes.get(0);
		PromoCode promoCode2 = promoCodes.get(1);
		PromoCode promoCode3 = promoCodes.get(2);
		PromoCode promoCode4 = promoCodes.get(3);
		//Assert
		Assert.assertNotNull(promoCodes);
		Assert.assertEquals(validPromoCodes.size(), promoCodes.size());
		Assert.assertNotNull(promoCode1);
		
		Assert.assertEquals(validPromoCode1.getCodeId(), promoCode1.getCodeId());
		Assert.assertEquals(validPromoCode1.getCode(), promoCode1.getCode());
		Assert.assertEquals(validPromoCode1.getDescription(), promoCode1.getDescription());
		Assert.assertEquals(validPromoCode1.getDiscount(), promoCode1.getDiscount());
		Assert.assertEquals(validPromoCode1.getMultiple(), promoCode1.getMultiple());
		Assert.assertEquals(validPromoCode1.getUsed(), promoCode1.getUsed());
		Assert.assertEquals(validPromoCode1.getValidFrom(), promoCode1.getValidFrom());
		Assert.assertEquals(validPromoCode1.getValidTo(), promoCode1.getValidTo());
		
		Assert.assertNotNull(promoCode2);
		
		Assert.assertEquals(validPromoCode2.getCodeId(), promoCode2.getCodeId());
		Assert.assertEquals(validPromoCode2.getCode(), promoCode2.getCode());
		Assert.assertEquals(validPromoCode2.getDescription(), promoCode2.getDescription());
		Assert.assertEquals(validPromoCode2.getDiscount(), promoCode2.getDiscount());
		Assert.assertEquals(validPromoCode2.getMultiple(), promoCode2.getMultiple());
		Assert.assertEquals(validPromoCode2.getUsed(), promoCode2.getUsed());
		Assert.assertEquals(validPromoCode2.getValidFrom(), promoCode2.getValidFrom());
		Assert.assertEquals(validPromoCode2.getValidTo(), promoCode2.getValidTo());
		
		Assert.assertNotNull(promoCode3);
		
		Assert.assertEquals(validPromoCode3.getCodeId(), promoCode3.getCodeId());
		Assert.assertEquals(validPromoCode3.getCode(), promoCode3.getCode());
		Assert.assertEquals(validPromoCode3.getDescription(), promoCode3.getDescription());
		Assert.assertEquals(validPromoCode3.getDiscount(), promoCode3.getDiscount());
		Assert.assertEquals(validPromoCode3.getMultiple(), promoCode3.getMultiple());
		Assert.assertEquals(validPromoCode3.getUsed(), promoCode3.getUsed());
		Assert.assertEquals(validPromoCode3.getValidFrom(), promoCode3.getValidFrom());
		Assert.assertEquals(validPromoCode3.getValidTo(), promoCode3.getValidTo());
		
		Assert.assertNotNull(promoCode4);
		
		Assert.assertEquals(validPromoCode4.getCodeId(), promoCode4.getCodeId());
		Assert.assertEquals(validPromoCode4.getCode(), promoCode4.getCode());
		Assert.assertEquals(validPromoCode4.getDescription(), promoCode4.getDescription());
		Assert.assertEquals(validPromoCode4.getDiscount(), promoCode4.getDiscount());
		Assert.assertEquals(validPromoCode4.getMultiple(), promoCode4.getMultiple());
		Assert.assertEquals(validPromoCode4.getUsed(), promoCode4.getUsed());
		Assert.assertEquals(validPromoCode4.getValidFrom(), promoCode4.getValidFrom());
		Assert.assertEquals(validPromoCode4.getValidTo(), promoCode4.getValidTo());
	}
	
	@Test
	public void whenGettingAllValidPromoCodes__itShouldReturnPromoCodesObject()  {
		//It should return only not used and valid (date) promoCodees in database
		List<PromoCode> validPromoCodes = new ArrayList<PromoCode>();
		Long promoCodeId1 = new Long(1);
		BigDecimal promoCodeDiscount1 = new BigDecimal("0.25");
		PromoCode validPromoCode1 = new PromoCode();
		validPromoCode1.setCodeId(promoCodeId1);
		validPromoCode1.setCode("ABC");
		validPromoCode1.setDescription("ABC DESCRIPTION");
		validPromoCode1.setDiscount(promoCodeDiscount1);
		validPromoCode1.setMultiple("N");
		validPromoCode1.setUsed("N");
		validPromoCode1.setValidFrom(Timestamp.valueOf("2016-01-01 00:00:00.0"));
		
		PromoCode validPromoCode2 = new PromoCode();
		Long promoCodeId2 = new Long(2);
		BigDecimal promoCodeDiscount2 = new BigDecimal("0.25");
		validPromoCode2.setCodeId(promoCodeId2);
		validPromoCode2.setCode("DFGH");
		validPromoCode2.setDescription("DFGH DESCRIPTION");
		validPromoCode2.setDiscount(promoCodeDiscount2);
		validPromoCode2.setMultiple("N");
		validPromoCode2.setUsed("N");
		validPromoCode2.setValidFrom(Timestamp.valueOf("2016-01-02 00:00:00.0"));
		
		validPromoCodes.add(validPromoCode1);
		validPromoCodes.add(validPromoCode2);

		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		List<PromoCode> promoCodes = promoCodeService.getPromoCodes(false);
		
		PromoCode promoCode1 = promoCodes.get(0);
		PromoCode promoCode2 = promoCodes.get(1);
		//Assert
		Assert.assertNotNull(promoCodes);
		Assert.assertEquals(validPromoCodes.size(), promoCodes.size());
		Assert.assertNotNull(promoCode1);
		
		Assert.assertEquals(validPromoCode1.getCodeId(), promoCode1.getCodeId());
		Assert.assertEquals(validPromoCode1.getCode(), promoCode1.getCode());
		Assert.assertEquals(validPromoCode1.getDescription(), promoCode1.getDescription());
		Assert.assertEquals(validPromoCode1.getDiscount(), promoCode1.getDiscount());
		Assert.assertEquals(validPromoCode1.getMultiple(), promoCode1.getMultiple());
		Assert.assertEquals(validPromoCode1.getUsed(), promoCode1.getUsed());
		Assert.assertEquals(validPromoCode1.getValidFrom(), promoCode1.getValidFrom());
		Assert.assertEquals(validPromoCode1.getValidTo(), promoCode1.getValidTo());
		
		Assert.assertNotNull(promoCode2);
		
		Assert.assertEquals(validPromoCode2.getCodeId(), promoCode2.getCodeId());
		Assert.assertEquals(validPromoCode2.getCode(), promoCode2.getCode());
		Assert.assertEquals(validPromoCode2.getDescription(), promoCode2.getDescription());
		Assert.assertEquals(validPromoCode2.getDiscount(), promoCode2.getDiscount());
		Assert.assertEquals(validPromoCode2.getMultiple(), promoCode2.getMultiple());
		Assert.assertEquals(validPromoCode2.getUsed(), promoCode2.getUsed());
		Assert.assertEquals(validPromoCode2.getValidFrom(), promoCode2.getValidFrom());
		Assert.assertEquals(validPromoCode2.getValidTo(), promoCode2.getValidTo());
	}
	
	@Test
	public void whenAddingPromoCode_givenValidPromoCode_itShouldReturnPromoCodeObject()  {
		final Long validCodeId = new Long(5);
		final String validCode = "XCV";
		final String validDescription = "XCV DESCRIPTION";
		final BigDecimal validDiscount = new BigDecimal("0.36");
		final String validMultiple = "N";
		final String validUsed = "N";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-02-01 00:00:00.0");
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.addPromoCode(validCode, validDescription, validDiscount, validMultiple, validValidFrom, null);
		PromoCode promoCodeCheck = promoCodeService.getPromoCode(promoCode.getCodeId());
		
		//Assert
		Assert.assertNotNull(promoCode);
		Assert.assertEquals(validCodeId, promoCode.getCodeId());
		Assert.assertEquals(validCode, promoCode.getCode());
		Assert.assertEquals(validDescription, promoCode.getDescription());
		Assert.assertEquals(validDiscount, promoCode.getDiscount());
		Assert.assertEquals(validMultiple, promoCode.getMultiple());
		Assert.assertEquals(validUsed, promoCode.getUsed());
		Assert.assertEquals(validValidFrom, promoCode.getValidFrom());
		Assert.assertEquals(null, promoCode.getValidTo());
		
		Assert.assertNotNull(promoCodeCheck);
	}
	
	@Test
	public void whenAddingPromoCode_givenValidPromoCodeWithValidTo_itShouldReturnPromoCodeObject()  {
		final Long validCodeId = new Long(5);
		final String validCode = "XCV";
		final String validDescription = "XCV DESCRIPTION";
		final BigDecimal validDiscount = new BigDecimal("0.36");
		final String validMultiple = "N";
		final String validUsed = "N";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-02-01 00:00:00.0");
		final Timestamp validValidTo = Timestamp.valueOf("2016-06-01 00:00:00.0");
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.addPromoCode(validCode, validDescription, validDiscount, validMultiple, validValidFrom, validValidTo);
		PromoCode promoCodeCheck = promoCodeService.getPromoCode(promoCode.getCodeId());
		
		//Assert
		Assert.assertNotNull(promoCode);
		Assert.assertEquals(validCodeId, promoCode.getCodeId());
		Assert.assertEquals(validCode, promoCode.getCode());
		Assert.assertEquals(validDescription, promoCode.getDescription());
		Assert.assertEquals(validDiscount, promoCode.getDiscount());
		Assert.assertEquals(validMultiple, promoCode.getMultiple());
		Assert.assertEquals(validUsed, promoCode.getUsed());
		Assert.assertEquals(validValidFrom, promoCode.getValidFrom());
		Assert.assertEquals(validValidTo, promoCode.getValidTo());
		
		Assert.assertNotNull(promoCodeCheck);
	}
	
	@Test
	public void whenAddingPromoCode_givenInvalidPromoCode_itShouldReturnNull()  {
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.addPromoCode(null, null, null, null, null, null);
		
		//Assert
		Assert.assertNull(promoCode);
	}
	
	@Test
	public void whenUpdatingPromoCode_givenValidPromoCodeId_itShouldReturnPromoCodeObject()  {
		final Long validCodeId = new Long(1);
		final String validCode = "XCV";
		final String validDescription = "XCV DESCRIPTION";
		final BigDecimal validDiscount = new BigDecimal("0.36");
		final String validMultiple = "N";
		final String validUsed = "N";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-02-01 00:00:00.0");
		final Timestamp validValidTo = Timestamp.valueOf("2016-06-01 00:00:00.0");
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.updatePromoCode(validCodeId, validCode, validDescription, validDiscount, validMultiple, validUsed, validValidFrom, validValidTo);
		
		//Assert
		Assert.assertNotNull(promoCode);
		Assert.assertEquals(validCodeId, promoCode.getCodeId());
		Assert.assertEquals(validCode, promoCode.getCode());
		Assert.assertEquals(validDescription, promoCode.getDescription());
		Assert.assertEquals(validDiscount, promoCode.getDiscount());
		Assert.assertEquals(validMultiple, promoCode.getMultiple());
		Assert.assertEquals(validUsed, promoCode.getUsed());
		Assert.assertEquals(validValidFrom, promoCode.getValidFrom());
		Assert.assertEquals(validValidTo, promoCode.getValidTo());
	}
	
	@Test
	public void whenUpdatingPromoCode_givenInValidPromoCodeId_itShouldReturnNull()  {
		final Long validCodeId = new Long(100);
		final String validCode = "XCV";
		final String validDescription = "XCV DESCRIPTION";
		final BigDecimal validDiscount = new BigDecimal("0.36");
		final String validMultiple = "N";
		final String validUsed = "N";
		final Timestamp validValidFrom = Timestamp.valueOf("2016-02-01 00:00:00.0");
		final Timestamp validValidTo = Timestamp.valueOf("2016-06-01 00:00:00.0");
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		PromoCode promoCode = promoCodeService.updatePromoCode(validCodeId, validCode, validDescription, validDiscount, validMultiple, validUsed, validValidFrom, validValidTo);
		
		//Assert
		Assert.assertNull(promoCode);
	}
	
	@Test
	public void whenDeletingPromoCode_itShouldReturnNull()  {
		final Long validCodeId = new Long(1);
		final PromoCodeService promoCodeService = new PromoCodeServiceImpl();
		
		//Act
		promoCodeService.deletePromoCode(validCodeId);
		
		PromoCode promoCode = promoCodeService.getPromoCode(validCodeId);
		
		//Assert
		Assert.assertNull(promoCode);
	}

}
