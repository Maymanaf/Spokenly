package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.CallReportPO;
import pageObject.LoginPO;

public class ActivitiesGridVerification extends Base {

	private String currentpath = System.getProperty("user.dir");
	private String path = currentpath + "/datafiles/GridsandActivites.xlsx";
	private CallReportPO PageObjectCallReport;
	private LoginPO PageObjectLogin;

	@BeforeMethod
	public void SetUpChrome() {
		// Initialize browser
		InitilizeChrome();
		// Locators Page
		PageObjectCallReport = new CallReportPO(driver);
		PageObjectLogin = new LoginPO(driver);
		Reporter.log("Driver got initialized", true);

	}

	@Test(dataProvider = "GridData")
	public void GridVerification(String Activity, String Grid) throws InterruptedException {

		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearchWithActivity(Activity, "All");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.AllActivitiesField, Activity));
		Reporter.log("Element is present ....", true);
		Thread.sleep(1000);
		if (PageObjectCallReport.CallsNumber() == 0) {
			throw new SkipException("The selected activity: " + Activity + " has no calls");
		} else {
			try {
				PageObjectCallReport.GridSelecting(Grid, false);
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				PageObjectCallReport.GridSelecting(Grid, false);
			}
			for (WebElement DisplayedGrid : PageObjectCallReport.GirdList) {
				if (DisplayedGrid.getText() == Grid)
					Reporter.log("The grid is avaialble for this activity", true);
			}
		}
	}

	@DataProvider(name = "GridData")

	public Object[][] getGridData() {

		Object[][] arrObj = GetExcelData.getExcelData(path, "GridsandActivites");
		return arrObj;
	}

	@AfterMethod

	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			Utility.captureScreenshot(driver, result.getName() + "_" + result.getEndMillis());
		}
		driver.close();
		driver.quit();
		Reporter.log("Browser closed", true);
	}
}
