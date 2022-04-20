package com.digitarm.spokenly;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
	public  static String baseUrl="https://hungry-lamport-2f8c97.netlify.app/";
	public  static WebDriver driver;

	public static void InitilizeChrome()  {

		// Initialize browser
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments(  "--incognito", "--ignore-certificate-errors",
			"--disable-extensions","--disable-popup-blocking", "--no-sandbox", "--disable-dev-shm-usage");
		driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
		// Maximize browser
		driver.manage().window().maximize(); 
		//Open Website

		driver.get(baseUrl);


	}


}
