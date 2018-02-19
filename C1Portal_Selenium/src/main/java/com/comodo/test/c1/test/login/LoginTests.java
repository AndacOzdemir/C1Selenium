package com.comodo.test.c1.test.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comodo.test.base.test.BaseTest;
import com.comodo.test.c1.page.login.LoginPage;

public class LoginTests extends BaseTest {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(LoginTests.class);

	/**
	 * LoginPage Object
	 */
	protected LoginPage lp;

	/**
	 * Test Case:
	 *     Logins with valid credentials
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 6024915
	 */
	@Test(priority=1)
	public void loginTest(){
		setTestcaseParameters("1300006", "2126");
		

		logger.trace("STARTING TEST: loginTest");
		lp = new LoginPage(driver);

		logger.trace("Test Step: Navigate to login page");
		lp.getLoginPage(LOGIN_URL);

		logger.trace("Test Step: Enter valid credentials");
		lp.enterLoginCredentials(USERNAME, PASSWORD);

		logger.trace("Expected Result: Login successfully");
		Assert.assertEquals("dome110", lp.getUserName(), "Unable to login successfully!");				
	}
	
	/**
	 * Test Case:
	 *     Logout from portal
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 1300006
	 */
	
	@Test(priority=2)
	public void LogoutC1Portal(){
	setTestcaseParameters("6337687", "2126");	
		
	logger.trace("Test Step: Click logout button");
	lp.clickLogoutButton();
	
	logger.trace("Expected Result: Logout successfully");
	Assert.assertEquals("https://staging.one.comodo.com/app/login", lp.getCurrentURL(), "Unable to logout successfully!");
	}
	
	
	/**
	 * Test Case:
	 *     Logins with invalid credentials
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 623230
	 */
	@Test(priority=3, dataProvider="createCredentialsForInvalidLogin")
	
        public void InvalidloginTest(String username, String password, String testid){
		setTestcaseParameters(testid, "2126");	
		
		logger.trace("STARTING TEST: InvalidloginTest");
		lp = new LoginPage(driver);

		logger.trace("Test Step: Navigate to login page");
		lp.getLoginPage(LOGIN_URL);

		logger.trace("Test Step: Enter invalid credentials");
		lp.enterLoginCredentials(username, password);
		
		logger.trace("Expected Result: Unable to Login");
		Assert.assertTrue(lp.isNotLoggedIn(), "No error message is received during logging in");
	}
	
	@DataProvider
	public Object[][] createCredentialsForInvalidLogin(){
		Object[][] invalidData = new Object [4][3]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
		
		// 1st row empty user name
		invalidData[0][0] =" ";
		invalidData[0][1] = "12345678";
		invalidData[0][2] ="1418435";

		// 2nd row empty password
		invalidData[1][0] ="cdmtest1@yopmail.com";
		invalidData[1][1] = " ";
		invalidData[1][2] ="1418436";
		
		// 3rd row wrong password
		invalidData[2][0] ="uat_q3@yopmail.com";
		invalidData[2][1] = "pass121233";
		invalidData[2][2] ="623230";
		
		// 4th row wrong user name
		invalidData[3][0] ="anc1233@yopmail.com";
		invalidData[3][1] = "12345678";
		invalidData[3][2] ="6338444";
		
		return invalidData;
}
	
}