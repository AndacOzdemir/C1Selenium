package com.comodo.test.base.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.comodo.test.base.common.Commons;


public class BasePage extends Commons {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BasePage.class);

	/**
	 * WebDriver Object
	 */
	public WebDriver driver;

	/**
	 * WebDriverWait Object
	 */
	public WebDriverWait wait;
	
	/**
	 * Constructor
	 * 
	 * @param driver WebDriver Object
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
		logger.info("Initializing Page Objects...");
		PageFactory.initElements(driver, this);
	}
}