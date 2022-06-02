package com.spokenly.digitarm;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Utility {
	static WebDriver driver;

	// method to capture screenshot
	public static void captureScreenshot(WebDriver driver, String screenshotName) {
		try {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File source = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
			System.out.println("Screenshot taken");
		} catch (Exception e) {
			System.out.println("Exception while taking screenshot" + e.getMessage());
		}
	}

	public static WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	// select element of DropDown by text
	public static void doSelectDropDownByText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	// select element of DropDown by index
	public static void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	// select element of DropDown by value
	public static void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	// Check If element is present
	public static boolean ElementExist(WebElement Myelement) {
		if (Myelement == null) {
			return false;
		}
		return true;
	}
}
