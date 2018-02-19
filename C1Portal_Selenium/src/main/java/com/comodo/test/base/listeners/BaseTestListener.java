package com.comodo.test.base.listeners;

import java.io.PrintWriter;
import java.io.StringWriter;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.comodo.test.base.common.Commons;
import com.comodo.test.testrail.TestrailReporter;


public class BaseTestListener extends TestListenerAdapter implements IInvokedMethodListener{
	/**
	 * Logger Object
	 */
	private static final Logger logger = LogManager.getLogger(BaseTestListener.class);

	@Override
	public void onTestFailure(ITestResult tr) {
		logger.error(tr.getName() + " test failed.");
		new TestrailReporter(Commons.TESTRAIL_URL, Commons.TESTRAIL_USERNAME, Commons.TESTRAIL_PASSWORD, Commons.TESTRAIL_PLAN_ID, Commons.TESTRAIL_RUN_ID).sendResult(tr, "Failed");
		//new TestResultEntity().sendResultToTestrail(tr, "Failed");
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		logger.warn(tr.getName() + " test skipped.");
		if(tr.getAttribute("caseId")!=null){
			new TestrailReporter(Commons.TESTRAIL_URL, Commons.TESTRAIL_USERNAME, Commons.TESTRAIL_PASSWORD, Commons.TESTRAIL_PLAN_ID, Commons.TESTRAIL_RUN_ID).sendResult(tr, "Retest");
			//new TestResultEntity().sendResultToTestrail(tr, "Retest");
		}
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		logger.info(tr.getName() + " test success.");
		new TestrailReporter(Commons.TESTRAIL_URL, Commons.TESTRAIL_USERNAME, Commons.TESTRAIL_PASSWORD, Commons.TESTRAIL_PLAN_ID, Commons.TESTRAIL_RUN_ID).sendResult(tr, "Passed");
		//new TestResultEntity().sendResultToTestrail(tr, "Passed");	
	}

	@Override
	public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
		logger.info("Starting method: "+method.getTestMethod().getMethodName());
	}

	@Override
	public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

		if (testResult.getMethod().isTest()) {
			//Change Failed to Skipped based on exception text
			if (testResult.getStatus() == ITestResult.FAILURE) {
				if (testResult.getThrowable() != null) {
					if (testResult.getThrowable().getStackTrace() != null) {
						StringWriter sw = new StringWriter();
						testResult.getThrowable().printStackTrace(new PrintWriter(sw));
						if (sw.toString().contains("ThreadTimeoutException")) {
							ITestContext tc = Reporter.getCurrentTestResult().getTestContext();
							tc.getFailedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							tc.getFailedTests().getAllMethods().remove(Reporter.getCurrentTestResult().getMethod());
							Reporter.getCurrentTestResult().setStatus(ITestResult.SKIP);
							tc.getSkippedTests().addResult(testResult, Reporter.getCurrentTestResult().getMethod());
							logger.warn("Method timed out!");
						}
					}
				}
			}
		}
	}
}