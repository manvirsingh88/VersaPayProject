package com.versapay.qa.retryfailedtestlogic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTest implements IRetryAnalyzer {
	int counter = 0;
	int maxRetry = 2;


	@Override
	public boolean retry(ITestResult result) {
		
		if (counter < maxRetry) {

			counter++;

			return true;
		}
		
		return false;
	}

}
