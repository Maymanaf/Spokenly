package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CallQualificationPO extends PageObject {

	public CallQualificationPO(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h6[normalize-space()='Advanced filters']")
	private WebElement CallQualAdvancedFilters;

	@FindBy(xpath = "//div/input[@placeholder='Agent speaker ']")
	private WebElement AgentSpeakerField;
//	@FindBy(id = "mui-25618")
//	private WebElement EvaluatorField;
	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-grid-lg-10']//label[1]//span[1]//input[1]")
	private WebElement EvaluatedRadioButton;
	
	@FindBy(xpath = "//span[@class='MuiButton-label']/h6")
	private WebElement AssessCallButton;
	
	//span[@class='MuiButton-label']
	@FindBy(xpath = "//button[normalize-space()='View details']")
	private WebElement DetailsButtonReport;
	@FindBy(id = "SvgjsText2127")
	private WebElement LowNote;
	

//	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr")
//	private List<WebElement> AllTableRows;
//	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr[1]/td")
//	private List<WebElement> AllTableColumns;

	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr[1]/td[7]")
	private WebElement FirstDetailsButton;

	public WebElement getLowNote() {
		return LowNote;
	}

	public void FilterSearch() {
		CallQualAdvancedFilters.click();
		Actions action = new Actions(driver);
		AgentSpeakerField.clear();
		AgentSpeakerField.sendKeys("DL1967");
		action.sendKeys(AgentSpeakerField, Keys.ARROW_DOWN).build().perform();
		action.sendKeys(AgentSpeakerField,Keys.ENTER).build().perform();
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.attributeToBe(AgentSpeakerField, "value" , "DL1967"));
	}

	public void ReportCallDetails() {
		FirstDetailsButton.click();
		AssessCallButton.click();
	}

}
