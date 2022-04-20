package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObject.CallReportPO;
import pageObject.LoginPO;

public class CallReportTest extends Base {
	private String currentpath = System.getProperty("user.dir");
	private String path = currentpath + "/datafiles/TestData.xlsx";
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

	@Test(priority = 0)
	public void SelectCallAndGrid1() throws InterruptedException {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("sami", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearch("DL1967", "Sortant");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.FirstElementAgent, "DL1967"));
		String myAgentId = PageObjectCallReport.FirstElementAgent.getText();
		Assert.assertTrue(myAgentId.contains("DL1967"));
		Reporter.log("Agent DL1967 calls are selected ", true);
		PageObjectCallReport.GridSelecting("BU TELCO_Orange FTTH_Agence_Appel Sortant", true);
		Reporter.log("The grid is selected successfully", true);
		Reporter.log("The grid is being evaluated ....", true);
		PageObjectCallReport.EvaluateGrid();
		PageObjectCallReport.GridBreefing();
		Reporter.log("The grid is successfully evaluated", true);
		String LastBreadcrumb = PageObjectCallReport.CallReportLastBreadcrumb.getText();
		Assert.assertEquals(LastBreadcrumb, "Report call");

	}

	@Test(priority = 1)
	public void SelectCallAndGrid2() throws InterruptedException {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("sami", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearch("DL1636", "Entrant");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.FirstElementAgent, "DL1636"));
		String myAgentId = PageObjectCallReport.FirstElementAgent.getText();
		Assert.assertTrue(myAgentId.contains("DL1636"));
		Reporter.log("Agent DL1967 calls are selected ", true);
		PageObjectCallReport.GridSelecting("BU TELCO_Orange FTTH Préalable_Appel Entrant", true);
		Reporter.log("The grid is selected successfully", true);
		Reporter.log("The grid is being evaluated ....", true);
		PageObjectCallReport.EvaluateGrid();
		PageObjectCallReport.GridBreefing();
		Reporter.log("The grid is successfully evaluated", true);
		String LastBreadcrumb = PageObjectCallReport.CallReportLastBreadcrumb.getText();
		Assert.assertEquals(LastBreadcrumb, "Report call");

	}

	@Test(priority = 2, dataProvider = "GridData")
	public void SelectCallAndGrid3(String Activity, String Grid) throws InterruptedException {

		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("sami", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearchWithActivity(Activity, null);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.AllActivitiesField, Activity));
		Reporter.log("Element is present ....", true);
		PageObjectCallReport.GridSelecting(Grid, true);
		Reporter.log("The grid is selected successfully", true);
		Reporter.log("The grid is being evaluated ....", true);
		PageObjectCallReport.GridBreefing();
		Reporter.log("The grid is successfully evaluated", true);
		String LastBreadcrumb = PageObjectCallReport.CallReportLastBreadcrumb.getText();
		Assert.assertEquals(LastBreadcrumb, "Report call");
	}

	@Test(priority = 3, dataProvider = "GridData")
	public void SelectCallAndGrid4(String Activity, String Grid) throws InterruptedException {

		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("sami", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallReport.FilterSearchWithActivity(Activity, null);
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallReport.AllActivitiesField, Activity));
		Reporter.log("Element is present ....", true);

		if (Utility.ElementExist(PageObjectCallReport.FirstElementDetailsBtn) == true) {
			try {

				PageObjectCallReport.GridSelecting(Grid, false);
			} catch (org.openqa.selenium.StaleElementReferenceException ex) {
				PageObjectCallReport.GridSelecting(Grid, false);
			}
			for (WebElement DisplayedGrid : PageObjectCallReport.GirdList) {
				if (DisplayedGrid.getText() == Grid)
					;
				Reporter.log("The grid is avaialble for this activity", true);
			}
		} 
		else {
			System.out.println("No calls for this activity" + Activity);
			return;
		}
	}

	@DataProvider(name = "GridData")

	public Object[][] getData() {

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
