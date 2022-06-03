package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import pageObject.CallEvalPO;
import pageObject.LoginPO;

public class RandomEval extends Base {

	private CallEvalPO PageObjectCallEval;
	private LoginPO PageObjectLogin;

	@BeforeMethod
	public void SetUpChrome() {
		// Initialize browser
		InitilizeChrome();
		// Locators Page
		PageObjectCallEval = new CallEvalPO(driver);
		PageObjectLogin = new LoginPO(driver);
		Reporter.log("Driver got initialized", true);

	}

	@Test(priority = 0)
	public void SCRandomEvalByAgent() throws InterruptedException {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallEval.FilterSearch("DL1967", "Sortant");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallEval.FirstElementAgent, "DL1967"));
		String myAgentId = PageObjectCallEval.FirstElementAgent.getText();
		Assert.assertTrue(myAgentId.contains("DL1967"));
		Reporter.log("Agent DL1967 calls are selected ", true);
		PageObjectCallEval.GridSelecting("BU TELCO_Orange FTTH_Agence_Appel Sortant", true);
		Reporter.log("The grid is selected successfully", true);
		Reporter.log("The grid is being evaluated ....", true);
		PageObjectCallEval.EvaluateGridWithoutSI();
		PageObjectCallEval.GridBreefing();
		Reporter.log("The grid is successfully evaluated", true);
		String LastBreadcrumb = PageObjectCallEval.CallReportLastBreadcrumb.getText();
		Assert.assertEquals(LastBreadcrumb, "Report call");

	}

	@Test(priority = 1)
	public void ECRandomEvalByAgent() throws InterruptedException {
		Reporter.log("Test is starting ....", true);
		PageObjectLogin.Authenticate("AymenNaf", "spokenly");
		Reporter.log("Logged in successfully", true);
		Reporter.log("Running Filter ....", true);
		PageObjectCallEval.FilterSearch("DL1636", "Entrant");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.textToBePresentInElement(PageObjectCallEval.FirstElementAgent, "DL1636"));
		String myAgentId = PageObjectCallEval.FirstElementAgent.getText();
		Assert.assertTrue(myAgentId.contains("DL1636"));
		Reporter.log("Agent DL1967 calls are selected ", true);
		PageObjectCallEval.GridSelecting("BU TELCO_Orange FTTH Préalable_Appel Entrant", true);
		Reporter.log("The grid is selected successfully", true);
		Reporter.log("The grid is being evaluated ....", true);
		PageObjectCallEval.EvaluateGridWithSI();
		PageObjectCallEval.GridBreefing();
		Reporter.log("The grid is successfully evaluated", true);
		String LastBreadcrumb = PageObjectCallEval.CallReportLastBreadcrumb.getText();
		Assert.assertEquals(LastBreadcrumb, "Report call");

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
