/* 
* @YahooFinancePage.java
* Copyright (c) 2025
*/

/**
 * Description(Yahoo finance test page)
 * @author Shakira 
 * @version 00:00:01
 * @see <com.SeleniumTestPages.YahooFinancePage>
 */

package com.SeleniumTestPages;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.SeleniumAssertion.Assertions;
import com.SeleniumUtilities.BrowserActions;

public class YahooFinancePage extends BrowserActions {
	WebDriver driver;

	By YahooFinanceHeader = By.xpath("//a[@id='ybar-logo']");
	By SearchBox = By.xpath("//input[@id='ybar-sbq']");
	By SearchIcon = By.xpath("//button[@id='ybar-search']");
	By AutoSuggestList = By.xpath("//*[@id='ybar-sf']/div[4]/div[1]/div[@data-id = 'search-assist-input-sugglst']/ul[@role='listbox']");
	By AutoSuggestOption = By.xpath("//li[@role='option']");
	
	// Class constructor
	public YahooFinancePage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Method for verify Yahoo finance page header
	public void financePageHeader() throws InterruptedException, IOException {
		Assertions.validateAssertion(checkElementVisibility(YahooFinanceHeader));
		String PageHeader = driver.findElement(YahooFinanceHeader).getText();
		System.out.println("Yahoo Finace header is :"+PageHeader);
		Assert. assertEquals(PageHeader,"Yahoo Finance", "Page header mismatch");
	}
	
	// Enter text to Yahoo Search box
	public void EnterTextToSearchBox(String SearchText) throws InterruptedException, IOException {
		Assertions.validateAssertion(checkElementVisibility(SearchBox));
		Assertions.validateAssertion(performSendkeys(SearchBox, SearchText));
		}

	// Verify Auto Suggestion list displayed for entered text in search box
	public void AutoSuggestionList() throws InterruptedException, IOException {
		//Explicit wait for auto suggestion list to be present
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(AutoSuggestList));
		System.out.println("Auto Suggestion List Found");
		}
	
	// Verify Auto Suggestion for entered text in search box with index of displaying
	public void AutoSuggestionItemVerification(String Index) throws InterruptedException, IOException {
		
		By AutoSuggestItem = By.xpath("//ul[@role='listbox']/li[@title='Tesla, Inc.'][1]");
		String ActualIndex = driver.findElement(AutoSuggestItem).getAttribute("data-pindex");
		Assert.assertEquals(ActualIndex, Index, "Tesla, Inc. Not in " + Index + "Index Position");
		//click on the Auto suggestion element
		Assertions.validateAssertion(clickElement(AutoSuggestItem));
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
	}

}
