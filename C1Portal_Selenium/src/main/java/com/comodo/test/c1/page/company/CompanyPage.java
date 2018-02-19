package com.comodo.test.c1.page.company;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comodo.test.base.page.BasePage;
import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Strings;

public class CompanyPage extends BasePage{
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(CompanyPage.class);

	/**
	 * Constructor
	 * @param driver WebDriver object
	 */
	public CompanyPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(how = How.ID, using = "c1-menu-management") 
	private WebElement MANAGEMENT_MENU;
	
	@FindBy(how = How.ID, using = "c1-menu-customer") 
	private WebElement CUSTOMER_MENU;
	
	@FindBy(how = How.ID, using = "new-customer-button") 
	private WebElement NEW_CUSTOMER;
	
	@FindBy(how = How.ID, using = "edit-customer-button") 
	private WebElement EDIT_CUSTOMER;
	
	@FindBy(how = How.ID, using = "delete-customer-button") 
	private WebElement DELETE_CUSTOMER;
	
	@FindBy(how = How.ID, using = "name") 
	private WebElement COMPANY_NAME;
	
	@FindBy(how = How.CSS, using = "form[name='editCompanyForm'] #name") 
	private WebElement EDIT_COMPANY_NAME;
	
	@FindBy(how = How.ID, using = "contact-email") 
	private WebElement CONTACT_MAIL;
	
	@FindBy(how = How.ID, using = "form[name='editCompanyForm'] #contact-email") 
	private WebElement EDIT_CONTACT_MAIL;
	
	@FindBy(how = How.ID, using = "address") 
	private WebElement COMPANY_ADDRESS;
	
	@FindBy(how = How.ID, using = "form[name='editCompanyForm'] #address") 
	private WebElement EDIT_COMPANY_ADDRESS;
	
	@FindBy(how = How.ID, using = "postal-code") 
	private WebElement POSTAL_CODE;
	
	@FindBy(how = How.ID, using = "form[name='editCompanyForm'] #postal-code") 
	private WebElement EDIT_POSTAL_CODE;
	
	@FindBy(how = How.ID, using = "description") 
	private WebElement COMPANY_DESC;
	
	@FindBy(how = How.ID, using = "form[name='editCompanyForm'] #description") 
	private WebElement EDIT_COMPANY_DESC;
	
	@FindBy(how = How.ID, using = "save-button-new-customer") 
	private WebElement COMPANY_SAVE_BUTTON;
	
	@FindBy(how = How.ID, using = "save-button-edit-customer") 
	private WebElement EDIT_COMPANY_SAVE_BUTTON;
	
	@FindBy(how = How.ID, using = "c1-popup-ok") 
	private WebElement OK_BUTTON;
	
	@FindBy(how = How.ID, using = "c1-popup-cancel") 
	private WebElement CANCEL_BUTTON;
	
	@FindBy(how = How.ID, using = "c1-popup-alert-message") 
	private WebElement SUCCESS_MESSAGE;
	
	@FindBy(how = How.ID, using = "addCompanyFormMessage") 
	private WebElement ADD_ERROR_MESSAGE;
	
	@FindBy(how = How.ID, using = "editCompanyFormMessage") 
	private WebElement EDIT_ERROR_MESSAGE;
	
	@FindBy(how = How.CSS, using = ".ui-grid-canvas > .ui-grid-row") 
	private List<WebElement> SELECT_ROWS;
	
	@FindBy(how = How.ID, using = "close-new-customer-popup") 
	private WebElement CLOSE_ADD_CUSTOMER;
	
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
	 * Enter customer menu
	 */
	public void enterCustomerMenu(){
		logger.info("Open customer sub-menu...");
		MANAGEMENT_MENU.click();
		CUSTOMER_MENU.click();
		}
		
	
	/**
	 *  Clicking new customer button in customer menu
	 */
	public void newCustomer(){
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(NEW_CUSTOMER)) ;
		logger.info("Clicking new customer button...");
		NEW_CUSTOMER.click();
		}
	
	
	/**
	 *  Selecting customer 
	 */
	public void selectCustomer(){
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(SELECT_ROWS.get(1))) ;
		logger.info("Selecting customer in the list...");
		SELECT_ROWS.get(1).click();
		}
	
	/**
	 *  Clicking edit customer button in customer menu
	 */
	public void editCustomer(){
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(EDIT_CUSTOMER)) ;
		logger.info("Clicking edit customer button...");
		EDIT_CUSTOMER.click();
		}
	
	/**
	 *  Clicking delete customer button in customer menu
	 */
	public void deleteCustomer(){
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(DELETE_CUSTOMER)) ;
		logger.info("Clicking delete customer button...");
		DELETE_CUSTOMER.click();
		}
	
	// Following 5 methods are for adding a new company inside the addNewCompany form. 
	
	/** Enter new company name
	 * @param companyname
	 */
	public void enterCompanyName(String companyname){
		if(Strings.isNullOrEmpty(companyname)){
			throw new IllegalArgumentException("Given parameter companyname can not be null or empty!");
		}
		COMPANY_NAME.clear();
		COMPANY_NAME.sendKeys(companyname);
	}
			
	/** Enter contact email for customer
	 * @param contactemail
	 */
	public void enterEmail(String contactemail){
		if(Strings.isNullOrEmpty(contactemail)){
			throw new IllegalArgumentException("Given parameter contactemail can not be null or empty!");
		}
		CONTACT_MAIL.clear();
		CONTACT_MAIL.sendKeys(contactemail);
	}
	
	/** enter address for customer
	 * @param address
	 */
	
	public void enterAddress(String address){
		if(Strings.isNullOrEmpty(address)){
			throw new IllegalArgumentException("Given parameter address can not be null or empty!");
		}
		COMPANY_ADDRESS.clear();
		COMPANY_ADDRESS.sendKeys(address);
	}
	
	/** enter postal code for customer
	 * @param postalcode
	 */
	public void enterPostalCode(String postalcode){
		if(Strings.isNullOrEmpty(postalcode)){
			throw new IllegalArgumentException("Given parameter postalcode can not be null or empty!");
		}
		POSTAL_CODE.clear();
		POSTAL_CODE.sendKeys(postalcode);
	}
	
	/** enter description for customer
	 * @param description
	 */
	
	public void enterDescription(String description){
		if(Strings.isNullOrEmpty(description)){
			throw new IllegalArgumentException("Given parameter description is invalid!");
		}
		COMPANY_DESC.clear();
		COMPANY_DESC.sendKeys(description);
	}
	
	/**
	 * Enters the company information
	 * 
	 * @param company name, contact email, address, postal code, description
	 */
	public void enterCompanyInformation(String companyname, String contactemail, String address, String postalcode, String description){
		enterCompanyName(companyname);
		enterEmail(contactemail);
		enterAddress(address);
		enterPostalCode(postalcode);
		enterDescription(description);
		clickSaveButton();
	}
	
	
	// Following methods 6 methods are for editing existing companies in the editCompanyForm
	
	/** Enter new company name
	 * @param companyname
	 */
	public void editCompanyName(String companyname){
		if(Strings.isNullOrEmpty(companyname)){
			throw new IllegalArgumentException("Given parameter companyname can not be null or empty!");
		}
		EDIT_COMPANY_NAME.clear();
		EDIT_COMPANY_NAME.sendKeys(companyname);
	}
			
	/** Enter contact email for customer
	 * @param contactemail
	 */
	public void editEmail(String contactemail){
		if(Strings.isNullOrEmpty(contactemail)){
			throw new IllegalArgumentException("Given parameter contactemail can not be null or empty!");
		}
		EDIT_CONTACT_MAIL.clear();
		EDIT_CONTACT_MAIL.sendKeys(contactemail);
	}
	
	/** enter address for customer
	 * @param address
	 */
	
	public void editAddress(String address){
		if(Strings.isNullOrEmpty(address)){
			throw new IllegalArgumentException("Given parameter address can not be null or empty!");
		}
		EDIT_COMPANY_ADDRESS.clear();
		EDIT_COMPANY_ADDRESS.sendKeys(address);
	}
	
	/** enter postal code for customer
	 * @param postalcode
	 */
	public void editPostalCode(String postalcode){
		if(Strings.isNullOrEmpty(postalcode)){
			throw new IllegalArgumentException("Given parameter postalcode can not be null or empty!");
		}
		EDIT_POSTAL_CODE.clear();
		EDIT_POSTAL_CODE.sendKeys(postalcode);
	}
	
	/** enter description for customer
	 * @param description
	 */
	
	public void editDescription(String description){
		if(Strings.isNullOrEmpty(description)){
			throw new IllegalArgumentException("Given parameter description is invalid!");
		}
		EDIT_COMPANY_DESC.clear();
		EDIT_COMPANY_DESC.sendKeys(description);
	}
	
	/**
	 * Enters the company information
	 * 
	 * @param company name, contact email, address, postal code, description
	 */
	public void editCompanyInformation(String companyname, String contactemail, String address, String postalcode, String description){
		editCompanyName(companyname);
//		editEmail(contactemail);
//		editAddress(address);
//		editPostalCode(postalcode);
//		editDescription(description);
		clickEditSaveButton();
	}
		
	/**
	 *  Clicking save button in customer creation page
	 */
	private void clickSaveButton() {
		logger.info("Saving company info...");
		COMPANY_SAVE_BUTTON.click();
	}

	/**
	 *  Clicking save button in customer update page
	 */
	private void clickEditSaveButton() {
		logger.info("Saving company info...");
		EDIT_COMPANY_SAVE_BUTTON.click();
	}
		
	/** Checks company creation result
	 * @return
	 */
	
	public String getSuccessMessage(){		
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(SUCCESS_MESSAGE)) ;
		
		return SUCCESS_MESSAGE.getText();  //get inner text of a web element
		}
	
	/** Checks add company failure messages
	 * @return
	 */
	
	public String getAddCompanyFormFailureMessage(){		
		new WebDriverWait(driver, 30);
		
		return ADD_ERROR_MESSAGE.getText();  //get inner text of a web element
		}
	
	/** Checks edit company failure messages
	 * @return
	 */
	
	public String getEditCompanyFormFailureMessage(){		
		new WebDriverWait(driver, 15).until(ExpectedConditions.elementToBeClickable(EDIT_ERROR_MESSAGE)) ;
		
		return EDIT_ERROR_MESSAGE.getText();  //get inner text of a web element
		}
	
	/**
	 * Click OK button on the company added message
	 */
	public void clickOKPopUp(){
		logger.info("Clicking ok button...");
		OK_BUTTON.click();
		}
	
	public void clickCloseAddCustomerPopUp(){
		logger.info("Clicking ok button...");
		CLOSE_ADD_CUSTOMER.click();
		}
		
	/**
	 * Click Cancel button on the company added message
	 */
	public void clickCancelPopUp(){
		logger.info("Clicking cancel button...");
		CANCEL_BUTTON.click();
		}
	}
	