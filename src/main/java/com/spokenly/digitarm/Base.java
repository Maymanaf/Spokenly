package com.spokenly.digitarm;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public static String baseUrl = "https://hungry-lamport-2f8c97.netlify.app/";
	public static String APIUrl = "https://178.128.240.70/api/token/";
	public static WebDriver driver;

	public static void InitilizeChrome() {

		// Initialize browser
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

		options.addArguments("--ignore-certificate-errors", "--disable-extensions", "--incognito",
				"--disable-popup-blocking", "--disable-web-security", "--disable-site-isolation-trials",
				"--allow-running-insecure-content", "--allow-cross-origin-auth-prompt", "--disable-blink-features",
				"--disable-blink-features=AutomationControlled", "--disable-gpu");

		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

		/*
		 * options.addArguments("--ignore-certificate-errors","--headless",
		 * "--disable-extensions",
		 * "--incognito","--disable-popup-blocking","--disable-web-security",
		 * "--disable-site-isolation-trials", "--allow-cross-origin-auth-prompt");
		 */
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// Maximize browser
		driver.manage().window().maximize();
		// Open Website
		driver.get(APIUrl);
		WebElement Bitdefender = driver
				.findElement(By.xpath("//a[contains(text(),'Je comprends les risques, je souhaite quand même c')]"));
		Bitdefender.click();
		driver.navigate().to(baseUrl);

	}

}
