package com.versapay.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.versapay.qa.base.TestBase;

public class Test extends TestBase{
	@BeforeMethod
	void StartUp() {
		initialisation();
	}


	@org.testng.annotations.Test
	void MethodOne()
	{
		String title =driver.getTitle();
		System.out.println(title);
		Assert.assertEquals("VersaPa | Automated Accounts Receivable Software", title);
	}
	
	@org.testng.annotations.Test
	void MethodTwo() {
		Assert.assertEquals(true, true);
	}
	
	@AfterMethod
	void tearDown() {
		driver.quit();
	}
}

