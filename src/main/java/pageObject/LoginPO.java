package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPO extends PageObject {

	public LoginPO(WebDriver driver) {
		super(driver);

	}

	@FindBy(id = "username")
	private WebElement UsernameField;
	@FindBy(id = "password")
	private WebElement PasswordField;
	@FindBy(xpath = "//span[normalize-space()='Connexion']")
	private WebElement LoginBtn;

	public void Authenticate(String username, String password) {
		UsernameField.sendKeys(username);
		PasswordField.sendKeys(password);
		LoginBtn.click();
	}

}
