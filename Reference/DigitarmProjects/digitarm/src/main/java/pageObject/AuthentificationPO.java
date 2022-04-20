package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthentificationPO extends PageObject {

	public AuthentificationPO(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "username")
	private  WebElement UsernameField;

	@FindBy(id = "password")
	private  WebElement PasswordField;

	@FindBy(xpath = "//span[normalize-space()='Connexion']")
	private  WebElement LoginButton;

   public void Authenticate(String Username, String Password) {
		UsernameField.sendKeys(Username);
		PasswordField.sendKeys(Password);
		LoginButton.click();

	}
}
