package pageObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
	Logger log ;
    WebDriver driver;

	
	public PageObject (WebDriver driver ) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);

		log = Logger.getLogger(PageObject.class.getName());

		log.setLevel(Level.INFO);
	}




}
