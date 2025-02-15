package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CallReportPO extends PageObject {

	public CallReportPO(WebDriver driver) {
		super(driver);
	}

	/************************************************
	 * private locators
	 ************************************************/
	@FindBy(xpath = "//table[@class='MuiTable-root']//p[contains(text(),'View details')]")
	public List<WebElement> ViewDetailsLinks;

	@FindBy(xpath = "//tr[@class=\"MuiTableRow-root\"]/td[1]/p")
	public List<WebElement> CoefValues;

	@FindBy(xpath = "//tr[@class=\"MuiTableRow-root\"]/td[2]/p")
	public List<WebElement> EvalsValues;

	@FindBy(xpath = "//tr[@class=\"MuiTableRow-root\"]/th/p")
	public List<WebElement> SubItemValues;

	/************************************************
	 * public locators
	 ************************************************/

	@FindBy(id = "call-report-details")
	public WebElement ViewDetailsButton;

	/************************************************
	 * page methods
	 ************************************************/

	public ArrayList<String> MyEvalResults() {
		ArrayList<String> EvaluatedValueResult = new ArrayList<>();

		for (WebElement EvalsValue : EvalsValues) {
			if (EvalsValue.getText().length() > 0) {
				EvaluatedValueResult.add(EvalsValue.getText());
			}
		}
		System.out.println(EvaluatedValueResult);
		return EvaluatedValueResult;

	}

	public void ExpandsSubItemList() {
		for (WebElement ViewDetails : ViewDetailsLinks) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(ViewDetails));
			ViewDetails.click();
		}
	}
}
