package com.listmanager.service.test;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.listmanager.DataSourceSetupUtil;
import com.listmanager.model.ListItem;
import com.listmanager.model.User;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;

public class UserServiceImplTests {
	/*@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}

	@Test
	public void whenAuthenticating_givenValidUSer_itShouldReturnUserObject() {
		//Arrange
		final String validUser = "test";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.authenticateUser(validUser);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUser, user.getUsername());
	}
	
	@Test
	public void whenAuthentication_givenInvalidUser_itShouldReturnNull() {
		//Arrange
		final String validUser = "tester";
		final UserService userService = new UserServiceImpl();
				
		//Act
		User user = userService.authenticateUser(validUser);
			
		//Assert
		Assert.assertNull(user);
	}
	
	@Test
	public void whenGettingUserListItems_givenNoItemsExist_itShouldReturnAnEmptyList() {
		//Arrange
		final UserService userService = new UserServiceImpl();
		
		//Act
		List<ListItem> listItems = userService.getListItems(1L);
		
		//Assert
		Assert.assertNotNull(listItems);
		Assert.assertEquals(0, listItems.size());
	}
	
	@Test
	public void whenGettingUserListItems_givenTwoItemsExist_itShouldReturnAListWithTwoListItems() throws SQLException {
		//Arrange
		DataSourceSetupUtil.insertListItemForDefaultUser("Testing 123");
		DataSourceSetupUtil.insertListItemForDefaultUser("Testing 456");
		
		final UserService userService = new UserServiceImpl();
		
		//Act
		List<ListItem> listItems = userService.getListItems(1L);
		
		//Assert
		Assert.assertNotNull(listItems);
		Assert.assertEquals(2, listItems.size());
	}*/
}
