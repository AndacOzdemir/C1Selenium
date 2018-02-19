package com.comodo.test.c1.page.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comodo.test.base.page.BasePage;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Strings;


public class LoginPage extends BasePage{
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(LoginPage.class);

	/**
	 * Constructor
	 * @param driver WebDriver object
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "email") 
	private WebElement EMAIL_INPUT;
	
	@FindBy(how = How.ID, using = "password") 
	private WebElement PASSWORD_INPUT;
	 
	@FindBy(how = How.CLASS_NAME, using = "btn-primary") 
	private WebElement LOGIN_BUTTON;
	
	@FindBy(how = How.ID, using = "c1-menu-logout") 
	private WebElement LOGOUT_BUTTON;
	
	@FindBy(how = How.ID, using = "c1-user-text") 
	private WebElement USER_MENU_BUTTON;
	
	@FindBy(how = How.CLASS_NAME, using = "ng-binding") 
	private WebElement LOGIN_ERROR;
	
	@FindBy(how = How.ID, using = "c1-user-text") 
	private WebElement USER_NAME;
	
	
	/**
	 * Opens login page by given url
	 * 
	 * @param url URL of login page
	 * @return LoginPage object
	 */
	public LoginPage getLoginPage(String url){
		driver.get(url);
		return this;
	}
	
	
	/**
	 * get current page URL
	 * @return
	 */
	public String getCurrentURL(){
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		
		String currentURL=driver.getCurrentUrl();
		return currentURL;
	}

	
	/**
	 * gets logged in user name from the menu bar
	 * @return
	 */
	public String getUserName(){
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(USER_NAME)) ;
	
	return USER_NAME.getText();  //get inner text of a web element
	}
	
	/**
	 * Enters login credentials then clicks login button
	 * 
	 * @param email email as user name
	 * @param password password
	 */
	public void enterLoginCredentials(String email, String password){
		enterEmail(email);
		enterPassword(password);
		clickLoginButton();
	}
	
	/**
	 * Enters email address
	 * 
	 * @param email email as user name
	 */
	public void enterEmail(String email){
		if(Strings.isNullOrEmpty(email)){
			throw new IllegalArgumentException("Given parameter email can not be null or empty!");
		}
		EMAIL_INPUT.clear();
		EMAIL_INPUT.sendKeys(email);
	}
	
	/**
	 * Enters the password
	 * 
	 * @param password password
	 */
	public void enterPassword(String password){
		if(Strings.isNullOrEmpty(password)){
			throw new IllegalArgumentException("Given parameter password can not be null or empty!");
		}
		PASSWORD_INPUT.clear();
		PASSWORD_INPUT.sendKeys(password);
	}
	
	/**
	 * Clicks the login button
	 */
	public void clickLoginButton(){
		LOGIN_BUTTON.click();
	}
	
	public void clickLogoutButton(){
		try {
			Thread.sleep(15000);
		} catch (InterruptedException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		USER_MENU_BUTTON.click();
		LOGOUT_BUTTON.click();
	}
	

	
	/**
	 * Logins with given credentials
	 * 
	 * @param url
	 * @param email
	 * @param password
	 */
	public void login(String url, String email, String password){
		logger.info("Login to C1...");
		getLoginPage(url);
		enterLoginCredentials(email, password);
	}

	
	/**
	 * Controls logout
	 * @return
	 */
	public boolean isNotLoggedIn() {
		// Auto-generated method stub
		logger.info("Checking logout process...");
		
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(LOGIN_ERROR)) ;
		} catch (ElementNotFoundException e) {
			return false;

		}	
		return true;
	}
	
}