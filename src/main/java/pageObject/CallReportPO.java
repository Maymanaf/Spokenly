package pageObject;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CallReportPO extends PageObject {

	public CallReportPO(WebDriver driver) {
		super(driver);
	}

	private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	/************************************************
	 * public locators
	 *********************************************************************/

	@FindBy(xpath = "//ol[@class=\"MuiBreadcrumbs-ol\"]/li[last()]")
	public WebElement CallReportLastBreadcrumb;

	@FindBy(xpath = "//div[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input']")
	public WebElement AllActivitiesField;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-item']/li/div/span/div")
	public List<WebElement> GirdList;

	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr[1]/td[7]/div/button/p")
	public WebElement FirstElementDetailsBtn;

	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr/th")
	public WebElement FirstElementAgent;

	@FindBy(xpath = "//tbody[@class='MuiTableBody-root']/tr/td[3]")
	public List<WebElement> ActivityColumns;

	@FindBy(xpath = "//h6[normalize-space()='Assess Call']")
	public WebElement AssessCallButton;

	@FindBy(xpath = "//input[@value='BU IT_M30 sortant']")
	public WebElement BUITM30sortantGrid;

	@FindBy(xpath = "//p[@class='MuiTypography-root MuiTypography-body1' and contains(text(),'Coéf')]")
	public List<WebElement> ListofCoefElements;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container']/form/div/p[@class='MuiTypography-root MuiTypography-body1']")
	public List<WebElement> ListofSubItem;

	/************************************************
	 * private locators
	 *********************************************************************/

	@FindBy(xpath = "//h6[normalize-space()='Advanced filters']")
	private WebElement AdvancedFilterBtn;

	@FindBy(xpath = "//input[@placeholder='Agent speaker ']")
	private WebElement AgentSpeakSdField;

	@FindBy(xpath = "//h6[normalize-space()='Non Evalué']")
	private WebElement NonEvalueRadio;

	@FindBy(xpath = "//h6[normalize-space()='Sortant']")
	private WebElement SortantRadio;

	@FindBy(xpath = "//h6[normalize-space()='Entrant']")
	private WebElement EntrantRadio;

	@FindBy(xpath = "//span[normalize-space()='Next']")
	private WebElement NextButton;

	@FindBy(xpath = "//span[@class='MuiButton-label' and contains(text(),'Suivant')]")
	private WebElement SuivantButton;

	@FindBy(xpath = "//span[@class='MuiStepLabel-iconContainer']")
	private List<WebElement> ItemRadioButton;

	@FindBy(xpath = "//textarea[@placeholder=\"Axes d'améliorations \"]")
	private WebElement AxesAmeliorationsField;

	@FindBy(xpath = "//textarea[@placeholder=\"Points forts\"]")
	private WebElement PointsFortsField;

	@FindBy(xpath = "//textarea[@placeholder=\"Priorités\"]")
	private WebElement PrioritesField;

	@FindBy(xpath = "//textarea[@placeholder=\"Moyens\"]")
	private WebElement MoyensField;

	@FindBy(xpath = "//span[@class='MuiButton-label' and contains(text(),'Envoie')]")
	private WebElement SendButton;

	@FindBy(xpath = "//input[@name='value' and @value='0']")
	private List<WebElement> SubItemsRadioBtn;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-container MuiGrid-item MuiGrid-grid-lg-5']/h6")
	private WebElement NumberOfActivityCalls;

	/************************************************
	 * page methods
	 *********************************************************************/

// Search Non Evaluated Calls by Agents
	public void FilterSearch(String AgentID, String CallType) throws InterruptedException {
		AdvancedFilterBtn.click();
		if (CallType.equalsIgnoreCase("Entrant")) {
			EntrantRadio.click();
		} else if (CallType.equalsIgnoreCase("Sortant")) {
			SortantRadio.click();
		}
		NonEvalueRadio.click();
		AgentSpeakSdField.sendKeys(AgentID);
		wait.until(ExpectedConditions.attributeToBe(AgentSpeakSdField, "value", AgentID));
		Thread.sleep(3000);
		AgentSpeakSdField.sendKeys(Keys.ARROW_DOWN);
		wait.until(ExpectedConditions.attributeToBeNotEmpty(AgentSpeakSdField, "aria-activedescendant"));
		AgentSpeakSdField.sendKeys(Keys.ENTER);

	}

//Search Non Evaluated Calls by Activities
	public void FilterSearchWithActivity(String ActivityName, String CallType) throws InterruptedException {
		AdvancedFilterBtn.click();
		if (CallType.equalsIgnoreCase("Entrant")) {
			EntrantRadio.click();
		} else if (CallType.equalsIgnoreCase("Sortant")) {
			SortantRadio.click();
		}
		NonEvalueRadio.click();
		AllActivitiesField.click();
		Thread.sleep(1000);
		WebElement MyActivites = driver.findElement(By.xpath("//li[normalize-space()='" + ActivityName + "']"));
		wait.until(ExpectedConditions.elementToBeClickable(MyActivites));
		MyActivites.click();
		wait.until(ExpectedConditions.textToBePresentInElement(MyActivites, ActivityName));

	}

//Selecting First Call then Select the corresponding grid is needed
	public void GridSelecting(String GridName, boolean WillISelectGrid) {

		FirstElementDetailsBtn.click();
		AssessCallButton.click();
		if (WillISelectGrid == true) {
			WebElement MyGrid = driver.findElement(By.xpath("//input[@value='" + GridName + "']"));
			// wait.until(ExpectedConditions.elementToBeClickable(MyGrid));
			MyGrid.click();
			NextButton.click();
		}
	}

//Evaluating the Grid with random values
	public void EvaluateGrid() {
		String[] EvalsNote = { "0", "1", "NE" };
		Random RanDom = new Random();
		for (int Itemcount = 1; Itemcount < ItemRadioButton.size(); Itemcount++) {
			for (int ElmntIndex = 0; ElmntIndex < SubItemsRadioBtn.size(); ElmntIndex++) {
				// select a random value from the string array
				int RandEval = RanDom.nextInt(EvalsNote.length);
				List<WebElement> SubItemsEval = driver
						.findElements(By.xpath("//input[@name='value' and @value='" + EvalsNote[RandEval] + "']"));
				// click on every radio button of a sub-item
				boolean selectState = SubItemsEval.get(ElmntIndex).isSelected();
				if (selectState == false) {
					SubItemsEval.get(ElmntIndex).click();
				}
			}
			SuivantButton.click();
		}
	}

//Filling the briefing fields with texts
	public void GridBreefing() {
		AxesAmeliorationsField.sendKeys(
				"les activités ou les domaines dans lesquels cet employé n'est pas au maximum de ses capacités et qu'il pourrait vouloir améliorer");
		PointsFortsField
				.sendKeys("Ce que je fais volontiers et bien. Ce qui me permet d'avoir des résultats et du succès");
		PrioritesField.sendKeys("Qualité de ce qui vient, passe en premier, dans le temps.");
		MoyensField.sendKeys("Qui se trouve entre deux extrêmes.");
		SendButton.click();
	}

//Verifying if there is calls available for the selected activity
	public int CallsNumber() {
		String StringNumberOfCalls = StringUtils.substringBefore(NumberOfActivityCalls.getText(), " results found");
		int NumberOfCalls = Integer.parseInt(StringNumberOfCalls);
		return NumberOfCalls;
	}

	// Return the coef and sub-items values in a array list
	public ArrayList<String> CoefandSubItemValue() {
		int NumberofSubItems = ListofSubItem.size();
		ArrayList<String> MyListofCoef = new ArrayList<>();
		ArrayList<String> MyListofSubItem = new ArrayList<>();
//		ArrayList<String>> MySubItemAndCoef = new ArrayList<>(NumberofSubItems);
		for (int i = 0; i < NumberofSubItems; i++) {
			MyListofCoef.add(StringUtils.substringAfter(ListofCoefElements.get(i).getText(), "Coéf "));
		}

		for (int j = 0; j < NumberofSubItems; j++) {
			MyListofSubItem.add(ListofSubItem.get(j).getText());
		}

		MyListofSubItem.addAll(MyListofCoef);
		return MyListofSubItem;

	}

}
