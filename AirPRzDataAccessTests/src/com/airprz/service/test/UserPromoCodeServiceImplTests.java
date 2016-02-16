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
import com.airprz.data.UserPromoCodeDao;
import com.airprz.data.impl.UserPromoCodeDaoImpl;
import com.airprz.model.PromoCode;
import com.airprz.model.User;
import com.airprz.model.UserPromoCode;
import com.airprz.service.UserPromoCodeService;
import com.airprz.service.impl.UserPromoCodeServiceImpl;

public class UserPromoCodeServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenCheckingIfUserUsedPromoCode_givenValidUserIdAndValidPromoCode_itShouldReturnUserPromoCodeObject()  {
		final Long validUserId = new Long(1);
		final Long validCodeId = new Long(1);
		final Long validUserPromoCodeId = new Long(1);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		UserPromoCode userPromoCode = userPromoCodeService.checkIfUserUsedPromoCode(validUserId, validCodeId);
		//Assert
		Assert.assertNotNull(userPromoCode);
		Assert.assertEquals(validUserPromoCodeId, userPromoCode.getUpcId());
		Assert.assertEquals(validUserId, userPromoCode.getUser().getId());
		Assert.assertEquals(validCodeId, userPromoCode.getPromoCode().getCodeId());

	}
	
	@Test
	public void whenCheckingIfUserUsedPromoCode_givenValidUserIdAndNotUsedPromoCode_itShouldReturnNull()  {
		final Long validUserId = new Long(1);
		final Long validCodeId = new Long(3);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		UserPromoCode userPromoCode = userPromoCodeService.checkIfUserUsedPromoCode(validUserId, validCodeId);
		//Assert
		Assert.assertNull(userPromoCode);
	}
	
	@Test
	public void whenGettingAllUserWhichUsedPromoCode__itShouldReturnUserPromoCodesObject()  {
		List<UserPromoCode> validUserPromoCodes = new ArrayList<UserPromoCode>();
		Long userPromoCodeId1 = new Long(1);
		Long userId1 = new Long(1);
		Long promoCodeId1 = new Long(1);
		User user1 = new User();
		user1.setId(userId1);
		PromoCode promoCode1 = new PromoCode();
		promoCode1.setCodeId(promoCodeId1);
		UserPromoCode validUserPromoCode1 = new UserPromoCode();
		validUserPromoCode1.setUpcId(userPromoCodeId1);
		validUserPromoCode1.setUser(user1);
		validUserPromoCode1.setPromoCode(promoCode1);
		
		Long userPromoCodeId2 = new Long(3);
		Long userId2 = new Long(2);
		Long promoCodeId2 = new Long(1);
		User user2 = new User();
		user2.setId(userId2);
		PromoCode promoCode2 = new PromoCode();
		promoCode2.setCodeId(promoCodeId2);
		UserPromoCode validUserPromoCode2 = new UserPromoCode();
		validUserPromoCode2.setUpcId(userPromoCodeId2);
		validUserPromoCode2.setUser(user2);
		validUserPromoCode2.setPromoCode(promoCode2);
		
		
		validUserPromoCodes.add(validUserPromoCode1);
		validUserPromoCodes.add(validUserPromoCode2);

		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		List<UserPromoCode> userPromoCodes = userPromoCodeService.getUsers(promoCodeId1);
		
		UserPromoCode userPromoCode1 = userPromoCodes.get(0);
		UserPromoCode userPromoCode2 = userPromoCodes.get(1);
		//Assert
		Assert.assertNotNull(userPromoCodes);
		Assert.assertEquals(validUserPromoCodes.size(), userPromoCodes.size());
		Assert.assertNotNull(userPromoCode1);
		
		Assert.assertEquals(validUserPromoCode1.getUpcId(), userPromoCode1.getUpcId());
		Assert.assertEquals(validUserPromoCode1.getUser().getId(), userPromoCode1.getUser().getId());
		Assert.assertEquals(validUserPromoCode1.getPromoCode().getCodeId(), userPromoCode1.getPromoCode().getCodeId());
		
		Assert.assertNotNull(userPromoCode2);
		
		Assert.assertEquals(validUserPromoCode2.getUpcId(), userPromoCode2.getUpcId());
		Assert.assertEquals(validUserPromoCode2.getUser().getId(), userPromoCode2.getUser().getId());
		Assert.assertEquals(validUserPromoCode2.getPromoCode().getCodeId(), userPromoCode2.getPromoCode().getCodeId());

	}
	
	@Test
	public void whenGettingAllCodesForUser__itShouldReturnUserPromoCodesObject()  {
		List<UserPromoCode> validUserPromoCodes = new ArrayList<UserPromoCode>();
		Long userPromoCodeId1 = new Long(1);
		Long userId1 = new Long(1);
		Long promoCodeId1 = new Long(1);
		User user1 = new User();
		user1.setId(userId1);
		PromoCode promoCode1 = new PromoCode();
		promoCode1.setCodeId(promoCodeId1);
		UserPromoCode validUserPromoCode1 = new UserPromoCode();
		validUserPromoCode1.setUpcId(userPromoCodeId1);
		validUserPromoCode1.setUser(user1);
		validUserPromoCode1.setPromoCode(promoCode1);
		
		Long userPromoCodeId2 = new Long(2);
		Long userId2 = new Long(1);
		Long promoCodeId2 = new Long(2);
		User user2 = new User();
		user2.setId(userId2);
		PromoCode promoCode2 = new PromoCode();
		promoCode2.setCodeId(promoCodeId2);
		UserPromoCode validUserPromoCode2 = new UserPromoCode();
		validUserPromoCode2.setUpcId(userPromoCodeId2);
		validUserPromoCode2.setUser(user2);
		validUserPromoCode2.setPromoCode(promoCode2);
		
		
		validUserPromoCodes.add(validUserPromoCode1);
		validUserPromoCodes.add(validUserPromoCode2);

		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		List<UserPromoCode> userPromoCodes = userPromoCodeService.getPromoCodes(userId1);
		
		UserPromoCode userPromoCode1 = userPromoCodes.get(0);
		UserPromoCode userPromoCode2 = userPromoCodes.get(1);
		//Assert
		Assert.assertNotNull(userPromoCodes);
		Assert.assertEquals(validUserPromoCodes.size(), userPromoCodes.size());
		Assert.assertNotNull(userPromoCode1);
		
		Assert.assertEquals(validUserPromoCode1.getUpcId(), userPromoCode1.getUpcId());
		Assert.assertEquals(validUserPromoCode1.getUser().getId(), userPromoCode1.getUser().getId());
		Assert.assertEquals(validUserPromoCode1.getPromoCode().getCodeId(), userPromoCode1.getPromoCode().getCodeId());
		
		Assert.assertNotNull(userPromoCode2);
		
		Assert.assertEquals(validUserPromoCode2.getUpcId(), userPromoCode2.getUpcId());
		Assert.assertEquals(validUserPromoCode2.getUser().getId(), userPromoCode2.getUser().getId());
		Assert.assertEquals(validUserPromoCode2.getPromoCode().getCodeId(), userPromoCode2.getPromoCode().getCodeId());

	}
	
	@Test
	public void whenGettingAllUsersForInvalidCode__itShouldReturnNull()  {
		final Long inalidCodeId = new Long(231);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		List<UserPromoCode> userPromoCodes = userPromoCodeService.getUsers(inalidCodeId);
		//Assert
		Assert.assertEquals(0, userPromoCodes.size());

	}
	
	@Test
	public void whenGettingAllCodesForInvalidUser__itShouldReturnNull()  {
		final Long inalidUserId = new Long(231);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		List<UserPromoCode> userPromoCodes = userPromoCodeService.getPromoCodes(inalidUserId);
		//Assert
		Assert.assertEquals(0, userPromoCodes.size());

	}
	
	@Test
	public void whenAddingValidUserPromoCode__itShouldReturnUserPromoCodeObject()  {
		final Long validUserId = new Long(2);
		final Long validCodeId = new Long(4);
		final Long validUserPromoCodeId = new Long(5);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		UserPromoCode userPromoCode = userPromoCodeService.addUserPromoCode(validUserId, validCodeId);
		//Assert
		Assert.assertEquals(validUserPromoCodeId, userPromoCode.getUpcId());
		Assert.assertEquals(validUserId, userPromoCode.getUser().getId());
		Assert.assertEquals(validCodeId, userPromoCode.getPromoCode().getCodeId());

	}
	
	@Test
	public void whenAddingInValidUserPromoCode__itShouldReturnnull()  {
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		UserPromoCode userPromoCode = userPromoCodeService.addUserPromoCode(null, null);
		//Assert
		Assert.assertNull(userPromoCode);
	}
	
	@Test
	public void whenUpdatingValidUserPromoCode__itShouldReturnUserPromoCodeObject()  {
		final Long validUserId = new Long(2);
		final Long validCodeId = new Long(4);
		final Long validUserPromoCodeId = new Long(1);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		
		//Act
		UserPromoCode userPromoCode = userPromoCodeService.updateUserPromoCode(validUserPromoCodeId, validUserId, validCodeId);
		//Assert
		Assert.assertEquals(validUserPromoCodeId, userPromoCode.getUpcId());
		Assert.assertEquals(validUserId, userPromoCode.getUser().getId());
		Assert.assertEquals(validCodeId, userPromoCode.getPromoCode().getCodeId());

	}
	
	@Test
	public void whenDeletingUserPromoCode_itShouldReturnNull()  {
		final Long validUserCodeId = new Long(1);
		final UserPromoCodeService userPromoCodeService = new UserPromoCodeServiceImpl();
		final UserPromoCodeDao userPromoCodeDao = new UserPromoCodeDaoImpl();
		
		//Act
		userPromoCodeService.deleteUserPromoCode(validUserCodeId);
		
		UserPromoCode userPromoCode = userPromoCodeDao.getUserPromoCode(validUserCodeId);
		
		//Assert
		Assert.assertNull(userPromoCode);
	}

}
