/* 
* @TeslaIncPage.java
* Copyright (c) 2025
*/

/**
 * Description(Testla Inc test page)
 * @author Shakira 
 * @version 00:00:01
 * @see <com.SeleniumTestPages.TeslaIncPage>
 */

package com.SeleniumTestPages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.SeleniumAssertion.Assertions;
import com.SeleniumUtilities.BrowserActions;

public class TeslaIncPage extends BrowserActions {
	WebDriver driver;

	By TeslaIncHeader = By.xpath("//h1[@class='yf-xxbei9']");
	By CurrentStockPrice = By.xpath("//span[@data-testid='qsp-price']");	
	By PreviousStockField = By.xpath("//div[@data-testid='quote-statistics']");	
	By PreviousStockLabel = By.xpath("//li[@class=' yf-gn3zu3']/span[contains(text(),'Previous')]");
	By PreviousStockPrice = By.xpath("//fin-streamer[@data-field='regularMarketPreviousClose']");	
	By PreviousVolumeLabel = By.xpath("//li[@class=' yf-gn3zu3']/span[@title='Volume']");
	By PreviousVolume = By.xpath("//fin-streamer[@data-field='regularMarketVolume']");
	
			
	// Class constructor
	public TeslaIncPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method for verify Tesla Inc finance page header
	public void teslaIncPageHeader() throws InterruptedException, IOException {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(180));
		wait.until(ExpectedConditions.presenceOfElementLocated(TeslaIncHeader));
		Assertions.validateAssertion(checkElementVisibility(TeslaIncHeader));
		String PageHeader = driver.findElement(TeslaIncHeader).getText();
		System.out.println("Yahoo Finace header is :"+PageHeader);
		Assert. assertEquals(PageHeader, "Tesla, Inc. (TSLA)", "Page header mismatch");
	}
	
	// Verify Stock price is greater than specified value
	public void VerifyCurrentStockPrice(int ExpectedCurrentStockValue) throws InterruptedException, IOException {
		Assertions.validateAssertion(checkElementVisibility(CurrentStockPrice));
		Double CurrentStockValue = Double.valueOf(driver.findElement(CurrentStockPrice).getText());
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(CurrentStockValue >= ExpectedCurrentStockValue);
		}

	// Print additional data from the stock details page like previous close stock price and volume.
	public void LogAdditionalStockDetails() throws InterruptedException, IOException {
		Assertions.validateAssertion(checkElementVisibility(PreviousStockField));
		String PreviousCloseLabel = driver.findElement(PreviousStockLabel).getText();
		String PreviousCloseValue = driver.findElement(PreviousStockPrice).getText();
		String VolumeLabel = driver.findElement(PreviousVolumeLabel).getText();
		String CloseValue = driver.findElement(PreviousVolume).getText();
		System.out.println("Printing Additional Info of the TESLA Inc Stock Price");
		System.out.println(PreviousCloseLabel + " : " + PreviousCloseValue);
		System.out.println(VolumeLabel + " : " + CloseValue);
		}
}
