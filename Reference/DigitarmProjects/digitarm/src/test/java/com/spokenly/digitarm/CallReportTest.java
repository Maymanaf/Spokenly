package com.spokenly.digitarm;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import com.digitarm.spokenly.Base;
import com.digitarm.spokenly.Utility;

import pageObject.AuthentificationPO;
import pageObject.CallQualificationPO;

public class CallReportTest extends Base {

	private static CallQualificationPO PageObjectCallQualification;
	private static AuthentificationPO PageObjectAuthentification;

	@BeforeMethod

	public static void setUpChrome() {

		// Initialize browser
		Base.InitilizeChrome();

		// Locators Page
		PageObjectCallQualification = new CallQualificationPO(driver);
		PageObjectAuthentification = new AuthentificationPO(driver);
		// Authentification
		PageObjectAuthentification.Authenticate("sami", "spokenly");

		Reporter.log("Driver got initialized", true);

	}

	@Test
	public void CallReportingTest() {

		PageObjectCallQualification.FilterSearch();
		Reporter.log("....The search is launched....", true);
		PageObjectCallQualification.ReportCallDetails();
		Reporter.log("....The first Call is being evaluated....", true);
		// String LowNoteValue = PageObjectCallQualification.getLowNote().getText();
		// Assert.assertEquals(LowNoteValue, "0%");
	}

//	@AfterMethod
//
//	public static void tearDown(ITestResult result) {
//
//		if (ITestResult.FAILURE == result.getStatus()) {
//			Utility.captureScreenshot(driver, result.getName() + "_" + result.getEndMillis());
//		}
//		driver.close();
//		driver.quit();
//		Reporter.log("Browser closed", true);
//	}
}
