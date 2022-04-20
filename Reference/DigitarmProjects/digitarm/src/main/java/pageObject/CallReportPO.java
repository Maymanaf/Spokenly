package pageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallReportPO extends PageObject {

	public CallReportPO(WebDriver driver) {
		super(driver);
	}
	@FindBy(xpath="//span[normalize-space()='BU']")
	private WebElement BuCMB;	
	@FindBy(xpath="//span[normalize-space()='All activities']")
	private WebElement AllActivitiesCMB;	
	@FindBy(xpath="//tbody/tr[1]/td[7]/div[1]/button[1]/p[1]")
	private WebElement CallDetailsButton;	
	@FindBy(xpath="//p[normalize-space()='Assess Call']")
	private WebElement AssessCallButton;	
	@FindBy(xpath="//input[@value='BU Energie_ Appel Sortant_GAZ']")
	private WebElement BUEnergieAppelSortantGAZ;	
}
