
package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObject.CallEvalPO;
import pageObject.CallReportPO;
import pageObject.LoginPO;

public class TestClass extends Base {

	private CallEvalPO PageObjectCallEval;
	private CallReportPO PageObjectCallReport;
	private LoginPO PageObjectLogin;

	@BeforeMethod
	public void SetUpChrome() {
		// Initialize browser
		InitilizeChrome();
		driver.manage().deleteAllCookies();
		// Locators Page
		PageObjectCallReport = new CallReportPO(driver);
		PageObjectCallEval = new CallEvalPO(driver);
		PageObjectLogin = new LoginPO(driver);
		Reporter.log("Driver got initialized", true);

	}

	@Test
	public void VerifyEval() {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
//		Reporter.log("Running Filter ....", true);
//		PageObjectCallEval.FilterSearchForEvaluatedCalls("Sortant");
		PageObjectCallEval.FirstElementDetailsBtn.click();
		Reporter.log("First Call details in opened successfully", true);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(PageObjectCallReport.ViewDetailsButton));
		PageObjectCallReport.ViewDetailsButton.click();
		Reporter.log("The eval report page is displaying .... ", true);
		PageObjectCallReport.ExpandsSubItemList();
		Reporter.log("The list of sub-items is expanded successfully", true);
		PageObjectCallReport.MyEvalResults();

	}
	
	public void VerifyEval2() {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
//		Reporter.log("Running Filter ....", true);
//		PageObjectCallEval.FilterSearchForEvaluatedCalls("Sortant");
		PageObjectCallEval.FirstElementDetailsBtn.click();
		Reporter.log("First Call details in opened successfully", true);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(PageObjectCallReport.ViewDetailsButton));
		PageObjectCallReport.ViewDetailsButton.click();
		Reporter.log("The eval report page is displaying .... ", true);
		PageObjectCallReport.ExpandsSubItemList();
		Reporter.log("The list of sub-items is expanded successfully", true);
		PageObjectCallReport.MyEvalResults();

	}


//	@AfterMethod
//
//	public void tearDown(ITestResult result) {
//
//		if (ITestResult.FAILURE == result.getStatus()) {
//			Utility.captureScreenshot(driver, result.getName() + "_" + result.getEndMillis());
//		}
//		driver.close();
//		driver.quit();
//		Reporter.log("Browser closed", true);
//	}
}
