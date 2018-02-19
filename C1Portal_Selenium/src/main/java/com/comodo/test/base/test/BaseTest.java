package com.comodo.test.base.test;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.comodo.test.base.common.Commons;

public class BaseTest extends Commons {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BaseTest.class);

	/**
	 * WebDriver Object
	 */
	public WebDriver driver;

	/**
	 * This method will execute before each Test tag in testng.xml
	 * 
	 * @param browser Browser name as parameter. Should be defined in testng.xml
	 * @throws Exception
	 */
	@BeforeClass
	@Parameters("browser")
	public void initalizeTests(@Optional("firefox") String browser) throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			logger.info("Creating Firefox instance...");
			System.setProperty("webdriver.gecko.driver", ".\\resources\\geckodriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		} else if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\resources\\chromedriver.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-extensions");
			//options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			// create chrome instance
			logger.info("Creating Chrome instance...");
			driver = new ChromeDriver(capabilities);
		} else if (browser.equalsIgnoreCase("ie")) {
			// set path to IE.exe
			System.setProperty("webdriver.ie.driver", ".\\resources\\IEDriverServer.exe");
			// create IE instance
			logger.info("Creating IE instance...");
			driver = new InternetExplorerDriver();
		} else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		// Set global timeout as 3 seconds
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	/**
	 * This method will execute after each Test tag in testng.xml
	 */
	@AfterClass
	public void finalizeTests() {
		logger.trace("Finalizing tests.");
		driver.close();
		driver.quit();
	}
	
	/**
	 * Sets TestCaseEntity attribute to ITestResult
	 * 
	 * @param caseId
	 * @param suiteId
	 */
	public void setTestcaseParameters(String caseId, String suiteId){
		setTestcaseParameters(caseId, suiteId, null);
	}
	
	/**
	 * Sets TestCaseEntity and deviceName attributes to ITestResult
	 * 
	 * @param testCaseEntity
	 * @param deviceName
	 */
	public void setTestcaseParameters(String caseId, String suiteId, String deviceName){
		ITestResult tr = Reporter.getCurrentTestResult();
		tr.setAttribute("deviceName", deviceName);
		tr.setAttribute("caseId", caseId);
		logger.debug("caseId set to: "+tr.getAttribute("caseId"));
		tr.setAttribute("suiteId", suiteId);
		logger.debug("suiteId set to: "+tr.getAttribute("suiteId"));
	}
}