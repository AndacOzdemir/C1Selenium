package com.comodo.test.c1.test.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.comodo.test.base.test.BaseTest;
import com.comodo.test.c1.page.company.CompanyPage;
import com.comodo.test.c1.page.login.LoginPage;

public class CompanyTests extends BaseTest {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(CompanyTests.class);

	/**
	 * LoginPage Object
	 */
	protected LoginPage lp;
	protected CompanyPage cp;

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
	 *  Opens Customer Menu
	 */
	@Test(priority=2)
	public void openCustomerMenu(){
		setTestcaseParameters("653234", "2126");
		
        logger.trace("STARTING TEST: Add New Company");
        cp = new CompanyPage(driver);
		
		logger.trace("Test Step: Click Management-->Customer menu");
		cp.enterCustomerMenu();
	
		logger.trace("Expected Result: Open Customer Menu Successfully");
		Assert.assertEquals("https://staging.one.comodo.com/app/#/management/customer", cp.getCurrentURL(), "Unable to open customer page!");
	}
	
	
	/**
	 *Test Case:
	 *     Adding Valid Company
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 653234
	 */
	@Test(priority=3, dataProvider="createValidCompanyNames")
	
        public void addCompanyValid(String companyname, String contactmail, String address, String postalcode, String description, String testid){
		setTestcaseParameters(testid, "2126");	
		
		logger.trace("STARTING TEST: Add Valid Companies");
		cp = new CompanyPage(driver);

		logger.trace("Test Step: Click New Customer Button");
		cp.newCustomer();

		logger.trace("Test Step: Enter valid company details");
		cp.enterCompanyInformation(companyname, contactmail, address, postalcode, description );
		
		logger.trace("Expected Result: Company is added successfully");
		Assert.assertEquals("New company is added successfully.", cp.getSuccessMessage(), "New company is added successfully.");
		
		logger.trace("Test Step: Close success message");
		cp.clickOKPopUp();
	}
	
	@DataProvider
	public Object[][] createValidCompanyNames(){
		Object[][] validCompanyData = new Object [3][6]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
		
		// 1st row normal company name without description
		validCompanyData[0][0] ="company136";
		validCompanyData[0][1] = "company134t363@yopmail.com";
		validCompanyData[0][2] ="street 1 address 2";
		validCompanyData[0][3] ="09999";
		validCompanyData[0][4] = " ";
		validCompanyData[0][5] ="653234";
		
		// 2nd row empty password
		validCompanyData[1][0] ="türkç3teI3rwlwe3TestŞüğ";
		validCompanyData[1][1] = "company2@yopmail.com";
		validCompanyData[1][2] ="street 1 address 2 address";
		validCompanyData[1][3] ="abdfd ";
		validCompanyData[1][4] = "description is not empty";
		validCompanyData[1][5] ="653234";
		
        // 3rd row empty password
		validCompanyData[2][0] ="Вы можете re31пиeсат3ьt кириллиц1ей";
		validCompanyData[2][1] = "company2@yopmail.com";
		validCompanyData[2][2] ="street 1 address 2 address";
		validCompanyData[2][3] ="abdfd ";
		validCompanyData[2][4] = "description is not empty";
		validCompanyData[2][5] ="653234";
		
		return validCompanyData;
	}
		
	/**
	 *Test Case:
	 *     Adding Invalid Company
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 1434883
	 */
	
	@Test(priority=4, dataProvider="createInvalidCompanyNames")
		
        public void addCompanyInvalid(String companyname, String contactmail, String address, String postalcode, String description, String testid){
		setTestcaseParameters(testid, "2126");	
		
		logger.trace("STARTING TEST: Add Valid Companies");
		cp = new CompanyPage(driver);
	
		logger.trace("Test Step: Click New Customer Button");
		cp.newCustomer();

		logger.trace("Test Step: Enter valid company details");
		cp.enterCompanyInformation(companyname, contactmail, address, postalcode, description );
		
		logger.trace("Expected Result: Company already exist");
		Assert.assertEquals("Company already exists.", cp.getAddCompanyFormFailureMessage(), "Company creation is failed");
		
		logger.trace("Test Step: Close add customer form");
        cp.clickCloseAddCustomerPopUp();
		
	}
	
	@DataProvider
	public Object[][] createInvalidCompanyNames(){
		Object[][] invalidCompanyData = new Object [1][6]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
		
		
		// 1st row normal company name without description
		invalidCompanyData[0][0] ="company136";
		invalidCompanyData[0][1] = "compat363@yopmail.com";
		invalidCompanyData[0][2] ="street 1 add3ress 2";
		invalidCompanyData[0][3] ="09999";
		invalidCompanyData[0][4] = " ";
		invalidCompanyData[0][5] ="1434883";
				
		return invalidCompanyData;
}
	
	/**
	 *Test Case:
	 *     Editing Company
	 * 
	 * Test Suite Id: 2126
	 * Test Case Id: 653235
	 */
	
	@Test(priority=5, dataProvider="updateDataForCompanies")
	
    public void updateCompanies(String companyname, String contactmail, String address, String postalcode, String description, String testid){
	setTestcaseParameters(testid, "2126");	
	
	logger.trace("STARTING TEST: Edit Company");
	cp = new CompanyPage(driver);
	
	logger.trace("Test Step: Select customer to be edited");
	cp.selectCustomer();
	
	logger.trace("Test Step: Click Edit Customer Button");
	cp.editCustomer();
	
	logger.trace("Test Step: Enter valid company details");
	cp.editCompanyInformation(companyname, contactmail, address, postalcode, description );
	
	logger.trace("Expected Result: Company is edited successfully");
	Assert.assertEquals("Company is edited successfully.", cp.getSuccessMessage(), "Company is edited successfully.");
	
	logger.trace("Test Step: Close success message");
    cp.clickOKPopUp();
}
	@DataProvider
	public Object[][] updateDataForCompanies(){
		Object[][] editCompanyData = new Object [1][6]; //Rows - Number of times your test has to be repeated. Columns - Number of parameters in test data.
		
		// 1st row normal company name without description
		editCompanyData[0][0] ="company136_1";
		editCompanyData[0][1] = "compat363@yopmail.com";
		editCompanyData[0][2] ="street 1 add3ress 2 here 3";
		editCompanyData[0][3] ="11111";
		editCompanyData[0][4] = " ";
		editCompanyData[0][5] ="653235";
				
		return editCompanyData;
}
	
	
	@Test(priority=6)
	
    public void deleteCompany(){
	setTestcaseParameters("665382", "2126");	
	
	logger.trace("STARTING TEST: Delete Companies");
	cp = new CompanyPage(driver);

	logger.trace("Test Step: Select customer to be deleted");
	cp.selectCustomer();
	
	logger.trace("Test Step: Click delete customer button");
	cp.deleteCustomer();
	
	logger.trace("Test Step: Accept deletion");
	cp.clickOKPopUp();

	logger.trace("Expected Result: Company is deleted successfully.");
	Assert.assertEquals("Company is deleted successfully.", cp.getSuccessMessage(), "Company is deleted successfully.");
	
}
	
}