package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.CallEvalPO;
import pageObject.LoginPO;

public class GridSubItemAndCoefVerification extends Base {
	private String currentpath = System.getProperty("user.dir");
	private String path = currentpath + "/datafiles/MyCoefTable.xlsx";
	private CallEvalPO PageObjectCallReport;
	private LoginPO PageObjectLogin;

	@BeforeMethod
	public void SetUpChrome() {
		// Initialize browser
		InitilizeChrome();
		// Locators Page
		PageObjectCallReport = new CallEvalPO(driver);
		PageObjectLogin = new LoginPO(driver);
		Reporter.log("Driver got initialized", true);

	}

	@Test(dataProvider = "CoefandSubItemData")
	public void ItemCoefVerifcation(String Coef) throws InterruptedException {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearch("DL1636", "All");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.FirstElementAgent, "DL1636"));
		String myAgentId = PageObjectCallReport.FirstElementAgent.getText();
		Assert.assertTrue(myAgentId.contains("DL1636"));
		Reporter.log("Agent DL1967 calls are selected ", true);
		PageObjectCallReport.GridSelecting("BU TELCO_Orange FTTH Préalable_Appel Entrant", true);
		Reporter.log("The grid is selected successfully", true);
		Assert.assertEquals(PageObjectCallReport.CoefandSubItemValue().toString(), Coef.toString());
	}

	@DataProvider(name = "CoefandSubItemData")

	public Object[][] getCoefData() {

		Object[][] arrObj = GetExcelData.getExcelData(path, "MyCoefData");
		return arrObj;
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
