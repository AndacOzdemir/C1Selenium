package com.comodo.test.c1.test.staff;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comodo.test.base.test.BaseTest;
import com.comodo.test.c1.page.staff.StaffPage;
import com.comodo.test.c1.page.login.LoginPage;

public class StaffTests extends BaseTest {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(StaffTests.class);

	/**
	 * LoginPage Object
	 */
	protected LoginPage lp;
	protected StaffPage cp;

	/**
	 * PreCondition:
	 *     Logins with valid credentials
	 * 
	 */
	@Test(priority=1)
	public void loginToC1Portal(){
			
		logger.trace("login to c1 portal");
		lp = new LoginPage(driver);

		logger.trace("login page of c1 portal");
		lp.getLoginPage(LOGIN_URL);

		logger.trace("Test Step: Enter valid credentials");
		lp.enterLoginCredentials(USERNAME, PASSWORD);

		logger.trace("Login successfully");
		Assert.assertEquals("reg_q22_msp", lp.getUserName(), "Unable to login successfully!");				
	}
	
	
	/**
	 *  Opens Staff Menu
	 */
	@Test(priority=2)
	public void openStaffMenu(){
		setTestcaseParameters("897158", "2126");
        logger.trace("STARTING TEST: Add New Staff");
        cp = new StaffPage(driver);
		
		logger.trace("Test Step: Click Management-->Staff menu");
		cp.enterStaffMenu();
	
		logger.trace("Expected Result: Open Staff Menu Successfully");
		Assert.assertEquals("https://staging.one.comodo.com/app/#/management/staff", cp.getCurrentURL(), "Unable to open staff page!");
	}
	
	
	/**
	 *Test Case:
	 *     Adding Valid Staff
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 897158
	 */
	@Test(priority=3, dataProvider="createValidStaffNames")
	
        public void addStaffValid(String staffname, String staffemail, Integer role, String testid){
		setTestcaseParameters(testid, "2126");	
		
		logger.trace("STARTING TEST: Add Valid Companies");
		cp = new StaffPage(driver);

		logger.trace("Test Step: Click New Staff Button");
		cp.newStaff();

		logger.trace("Test Step: Enter valid staff details");
		cp.enterStaffInformation(staffname, staffemail, role);
		
		logger.trace("Expected Result: Staff is added successfully");
		Assert.assertEquals("New staff is added successfully.", cp.getSuccessMessage(), "New staff is added successfully.");
		
		logger.trace("Test Step: Close success message");
		cp.clickOKPopUp();
	}
	
	@DataProvider
	public Object[][] createValidStaffNames(){
		Object[][] validStaffData = new Object [3][4]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
		
		// 1st row normal staff name without description
		validStaffData[0][0] ="staff136";
		validStaffData[0][1] = "staff134t363@yopmail.com";
		validStaffData[0][2] ="1";  // index 1 is Admin role
		validStaffData[0][3] ="653234";
		
		// 2nd row empty password
		validStaffData[1][0] ="türkç3teI3rwlwe3TestŞüğ";
		validStaffData[1][1] = "staff2@yopmail.com";
		validStaffData[1][2] ="2";  // index 2 is Technician role
		validStaffData[1][3] ="653234";
		
        // 3rd row empty password
		validStaffData[2][0] ="Вы можете re31пиeсат3ьt кириллиц1ей";
		validStaffData[2][1] = "staff2@yopmail.com";
		validStaffData[2][2] ="3";  // index 2 is Technician role
		validStaffData[2][3] ="653234";
		
		return validStaffData;
	}
		
	/**
	 *Test Case:
	 *     Adding Invalid Staff
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 1434883
	 */
	
//	@Test(priority=4, dataProvider="createInvalidStaffNames")
//		
//        public void addStaffInvalid(String staffname, String staffemail, String role, String testid){
//		setTestcaseParameters(testid, "2126");	
//		
//		logger.trace("STARTING TEST: Add Valid Companies");
//		cp = new StaffPage(driver);
//	
//		logger.trace("Test Step: Click New Staff Button");
//		cp.newStaff();
//
//		logger.trace("Test Step: Enter valid staff details");
//		cp.enterStaffInformation(staffname, staffemail, role );
//		
//		logger.trace("Expected Result: Staff already exist");
//		Assert.assertEquals("Staff already exists.", cp.getAddStaffFormFailureMessage(), "Staff creation is failed");
//		
//		logger.trace("Test Step: Close add customer form");
//        cp.clickCloseAddStaffPopUp();
//		
//	}
//	
//	@DataProvider
//	public Object[][] createInvalidStaffNames(){
//		Object[][] invalidStaffData = new Object [1][4]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
//		
//		
//		// 1st row normal staff name without description
//		invalidStaffData[0][0] ="staff136";
//		invalidStaffData[0][1] = "compat363@yopmail.com";
//		invalidStaffData[0][2] ="street 1 add3ress 2";
//		invalidStaffData[0][3] ="1434883";
//				
//		return invalidStaffData;
//}
//	
//	/**
//	 *Test Case:
//	 *     Editing Staff
//	 * 
//	 * Test Suite Id: 2126
//	 * Test Case Id: 653235
//	 */
//	
//	@Test(priority=5, dataProvider="updateDataForCompanies")
//	
//    public void updateCompanies(String staffname, String staffemail, String address, String role, String description, String testid){
//	setTestcaseParameters(testid, "2126");	
//	
//	logger.trace("STARTING TEST: Edit Staff");
//	cp = new StaffPage(driver);
//	
//	logger.trace("Test Step: Select customer to be edited");
//	cp.selectStaff();
//	
//	logger.trace("Test Step: Click Edit Staff Button");
//	cp.editStaff();
//	
//	logger.trace("Test Step: Enter valid staff details");
//	cp.editStaffInformation(staffname, staffemail, role );
//	
//	logger.trace("Expected Result: Staff is edited successfully");
//	Assert.assertEquals("Staff is edited successfully.", cp.getSuccessMessage(), "Staff is edited successfully.");
//	
//	logger.trace("Test Step: Close success message");
//    cp.clickOKPopUp();
//}
//	@DataProvider
//	public Object[][] updateDataForCompanies(){
//		Object[][] editStaffData = new Object [1][4]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
//		
//		// 1st row normal staff name without description
//		editStaffData[0][0] ="staff136_1";
//		editStaffData[0][1] = "compat363@yopmail.com";
//		editStaffData[0][2] ="street 1 add3ress 2 here 3";
//		editStaffData[0][3] ="653235";
//				
//		return editStaffData;
//}
//	
//	
//	@Test(priority=6)
//	
//    public void deleteStaff(){
//	setTestcaseParameters("665382", "2126");	
//	
//	logger.trace("STARTING TEST: Delete Companies");
//	cp = new StaffPage(driver);
//
//	logger.trace("Test Step: Select customer to be deleted");
//	cp.selectStaff();
//	
//	logger.trace("Test Step: Click delete customer button");
//	cp.deleteStaff();
//	
//	logger.trace("Test Step: Accept deletion");
//	cp.clickOKPopUp();
//
//	logger.trace("Expected Result: Staff is deleted successfully.");
//	Assert.assertEquals("Staff is deleted successfully.", cp.getSuccessMessage(), "Staff is deleted successfully.");
//	
//}
//	
}