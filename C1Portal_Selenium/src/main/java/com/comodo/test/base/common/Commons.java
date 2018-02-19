package com.comodo.test.base.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Commons {
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(Commons.class);
	
	// Application specific properties
	public static String LOGIN_URL;
	public static String C1Portal_URL;
	public static String USERNAME;
	public static String PASSWORD;
	
	// TestRail specific properties
	public static String TESTRAIL_URL;
	public static String TESTRAIL_USERNAME;
	public static String TESTRAIL_PASSWORD;
	public static String TESTRAIL_PLAN_ID;
	public static String TESTRAIL_RUN_ID;

	
	/**
	 * Constructor 
	 */
	public Commons() {
		String propFilePath = "C:/c1portal.properties";
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(propFilePath));

			LOGIN_URL = properties.getProperty("loginurl");
			C1Portal_URL = properties.getProperty("ClPortalURL");
			USERNAME = properties.getProperty("username");
			PASSWORD = properties.getProperty("password");
			TESTRAIL_URL = properties.getProperty("testrailurl");
			TESTRAIL_USERNAME = properties.getProperty("testrailusername");
			TESTRAIL_PASSWORD = properties.getProperty("testrailpassword");
			TESTRAIL_PLAN_ID = properties.getProperty("testrailplanid");
			TESTRAIL_RUN_ID = properties.getProperty("testrailrunid");


		} catch (IOException e) {
			logger.error("Unable to read from ".concat(propFilePath));
			e.printStackTrace();
		}
	}
}