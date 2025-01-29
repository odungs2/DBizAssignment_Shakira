package com.SeleniumUtilities;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.SeleniumWebdriver.DriverClass;

public class ElementAccess extends DriverClass {
	public WebElement waitUntilclickableElement(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			return wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (WebDriverException ex) {
			System.out.println("Unable to identify the element in the page--->" + locator.toString());
			return null;
		}
	}

	public WebElement waitUntilVisibleElement(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (WebDriverException ex) {
			System.out.println("Unable to identify the element in the page--->" + locator.toString());
			return null;
		}
	}

	public List<WebElement> waitUntilGetAllElements(By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (WebDriverException ex) {
			System.out.println("Unable to identify the element in the page--->" + locator.toString());
			return null;
		}
	}

}

