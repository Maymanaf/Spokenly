package com.spokenly.digitarm;

import java.util.Arrays;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.digitarm.spokenly.Base;
import com.digitarm.spokenly.GetExcelData;
import com.digitarm.spokenly.Utility;

import pageObject.CallReportPO;

public class GridVerificationTest extends Base {

	private String currentpath = System.getProperty("user.dir");
	private String path = currentpath + "/datafiles/CursusData.xlsx";
	private static CallReportPO PageObjectCallReport;

//	@BeforeMethod
//
//	public static void setUpChrome() {
//
//		// Initialize browser
//		Base.InitilizeChrome();
//
//		// Locators Page
//		PageObjectCallReport = new CallReportPO(driver);
//		Reporter.log("Driver got initialized", true);
//
//	}
//
//	@Test(dataProvider = "GridData")
//	public void gridTest(String GridName, String GridItemTitle) {
//
//	}
//
//	@DataProvider(name = "GridData")
//
//	public Object[][] getData() {
//
//		Object[][] arrObj = GetExcelData.getExcelData(path, "Grid");
//		return arrObj;
//	}
//
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
