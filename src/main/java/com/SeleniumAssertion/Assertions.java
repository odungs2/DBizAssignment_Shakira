/* 
* @(#)Assertions.java 
* Copyright (c) 2025 
*/
/**
 * Description(Screen shot capturing for each failed test and valid assertion for each wrapper.)
 * @author Shakira 
 * @version 00:00:01
 * @see <com.SeleniumAssertion.Assertions >
 */
package com.SeleniumAssertion;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.SeleniumAssertion.Assertions;
import com.SeleniumUtilities.BrowserActions;
import com.SeleniumUtilities.ErrorCodeForDebug;

//Contains valid assertions for each wrappers
public class Assertions extends BrowserActions {
	public static void validateAssertion(ErrorCodeForDebug code) {
		if (code == ErrorCodeForDebug.Success)
			return;
		System.out.println(code.toString() + "--->Error occured due this error code");
		captureScreenshot(driver);
		Assert.assertFalse(false);
	}

//Screen shot capturing for each failed test*/
	private static void captureScreenshot(WebDriver driver) {
		// To create reference of TakesScreenshot
		TakesScreenshot scrShot = ((TakesScreenshot) driver);
		// Call method to capture screenshot
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File(new File(".").getAbsolutePath() + "\\FailureScreenShots");
		try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			System.out.println("Unable to Capture the screenshot");
		}
	}

}
