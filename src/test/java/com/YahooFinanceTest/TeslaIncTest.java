package com.YahooFinanceTest;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import com.SeleniumWebdriver.DriverClass;
import com.SeleniumTestPages.YahooFinancePage;
import com.SeleniumTestPages.TeslaIncPage;

public class TeslaIncTest extends DriverClass{
	YahooFinancePage YahooFinancePage;
	TeslaIncPage TeslaIncPage;
	WebDriverWait wait;
	
	@Test(priority =1)
	public void VerifyAutoSuggession() throws IOException, InterruptedException {
		YahooFinancePage = new YahooFinancePage(driver);
		//verify Yahoo finance header
		YahooFinancePage.financePageHeader();
		//Search for Tesla (TSLA)
		YahooFinancePage.EnterTextToSearchBox("TSLA");
		YahooFinancePage.AutoSuggestionList();
		//Verify Autosuggest: Upon typing "TSLA", the first auto suggested entry should be
		//"TESLA Inc." and navigate to Tesla Inc page click on first entry.
		YahooFinancePage.AutoSuggestionItemVerification("1");
	}
	
	
	@Test (priority =2)
	public void VerifyTestlaIncPage() throws IOException, InterruptedException {
		//verify Tesla Inc page header
		TeslaIncPage.teslaIncPageHeader();
		//Verify Stock Price is greater than $200
		TeslaIncPage.VerifyCurrentStockPrice(200);
		//LogAdditionalStockDetails
		TeslaIncPage.LogAdditionalStockDetails();
	} 
}
	

