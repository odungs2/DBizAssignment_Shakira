/* 
* @(#)BrowserActions.java  22/12/22 
* Copyright (c) 2022-2023 
*/
/**
 * Description(Implementing Wrappers for each selenium browser actions.)
 * @author Ananduraj 
 * @version 00:00:01
 * @see <com.SeleniumUtilities.BrowserActions >
 */

package com.SeleniumUtilities;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BrowserActions extends ElementAccess {

	public static Properties Value = new Properties();

	// Browser navigate to previous window
	public void goBack() {
		driver.navigate().back();
	}

	// Browser navigate to following window
	public void goForward() {
		driver.navigate().forward();
	}

	// Browser refresh
	public void refresh() {
		driver.navigate().refresh();
	}

	// Wrapper for switch to an iframe
	public ErrorCodeForDebug switchToFrame(By locator) {
		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				driver.switchTo().frame(waitUntilVisibleElement(locator));
				System.out.println("Switched to Iframe --->" + locator.toString());
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Error;
	}

	// Wrapper for switch back from an iframe to parent window
	public ErrorCodeForDebug switchToParentWindow() {
		try {
			driver.switchTo().defaultContent();
			System.out.println("Switched to Parent Window --->");
			return ErrorCodeForDebug.Success;
		} catch (StaleElementReferenceException ex) {
			System.out.println("Stale Element execption occured.!");
			return ErrorCodeForDebug.Error;
		}
	}

	// Wrapper for click an element
	public ErrorCodeForDebug clickElement(By locator) {
		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				element.click();
				System.out.println("Click Action Performed on --->" + locator.toString());
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.ElementNotClickable;
	}

	// Wrapper for checking element visibility
	public ErrorCodeForDebug checkElementVisibility(By locator) {
		WebElement element = waitUntilVisibleElement(locator);
		if (element != null) {
			System.out.println("Visible in the page --->" + locator.toString());
			return ErrorCodeForDebug.Success;
		}
		return ErrorCodeForDebug.ElementNotVisible;
	}

	// Wrapper for drag and drop a adjusting slider
	public ErrorCodeForDebug adjustSlider(By locator, int attValue) {
		Actions action = new Actions(driver);
		WebElement element = waitUntilclickableElement(locator);
		if (element == null)
			return ErrorCodeForDebug.ElementNotClickable;
		try {
			action.moveToElement(element).dragAndDropBy(element, 1, attValue).build().perform();
		} catch (WebDriverException ex) {
			System.out.print("Error occured while Performing drag action on the element--->" + locator.toString());
			return ErrorCodeForDebug.Error;
		}
		return ErrorCodeForDebug.Success;
	}

	// Wrapper for mouse hover and click an element
	public ErrorCodeForDebug hoverAndClickElement(By locator) {
		Actions action = new Actions(driver);
		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				action.moveToElement(element).click();
				System.out.println("Click Action Performed on --->" + locator.toString());
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.ElementNotClickable;
	}

	// Wrapper for send data to a particular field
	public ErrorCodeForDebug performSendkeys(By locator, String datas) throws IOException {

		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				element.click();
				element.sendKeys(datas);
				System.out.println("Sendkeys Action Performed on --->" + locator.toString());
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Success;
	}

	// Wrapper for check whether any alert present or not
	public ErrorCodeForDebug isAlertPresentHandle(By locator, String AlertHandle) {
		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				element.click();
				System.out.println("Click Action Performed on --->" + locator.toString());
				try {
					driver.switchTo().alert();
					System.out.println("Alert message : " + driver.switchTo().alert().getText());
					if (AlertHandle.equals("Accept")) {
						driver.switchTo().alert().accept();
						System.out.println("Alert Accepted --->" + locator.toString());
					} else if (AlertHandle.equals("Dismiss")) {
						driver.switchTo().alert().dismiss();
						System.out.println("Alert Dismissed --->" + locator.toString());
					}
				} catch (NoAlertPresentException e) {
					System.out.println("No Alert Present");
				}
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.ElementNotClickable;
	}

	// Wrapper for single select drop down
	public ErrorCodeForDebug dropDownSingleSelect(By locator, String Data, String SelectMethod) throws IOException {

		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				Select select = new Select(element);

				if (SelectMethod.equals("Visible Text")) {
					select.selectByVisibleText(Data);
				} else if (SelectMethod.equals("Value")) {
					select.selectByValue(Data);
				} else if (SelectMethod.equals("Index")) {
					select.selectByIndex(Integer.parseInt(Data));
				} else {
					System.out.println("No Options selected");
				}

				System.out.println("Selected the option" + " " + select.getFirstSelectedOption().getText() + " "
						+ "using Select by" + " " + SelectMethod + " " + "method from dropdown --->"
						+ locator.toString());

				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Success;
	}

	// Wrapper for multi-select drop down
	public ErrorCodeForDebug dropDownMultiSelect(By locator, String Data[], String SelectMethod) throws IOException {

		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				Select select = new Select(element);

				if (select.isMultiple()) {
					if (SelectMethod.equals("Visible Text")) {
						for (String options : Data) {
							select.selectByVisibleText(options);
						}
					} else if (SelectMethod.equals("Value")) {
						for (String options : Data) {
							select.selectByValue(options);
						}
					} else if (SelectMethod.equals("Index")) {
						for (String options : Data) {
							select.selectByIndex(Integer.parseInt(options));
						}
					} else {
						System.out.println("No Options selected");
					}

					List<WebElement> Options = select.getAllSelectedOptions();
					int optionsize = Options.size();
					for (int i = 0; i < optionsize; i++) {
						System.out.println("The selected option is : " + Options.get(i).getText());
					}

				}
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Success;
	}

	// Wrapper for deselecting a multi-select drop down
	public ErrorCodeForDebug dropDownMultiSelectDeSelect(By locator, String Data[], String DeSelectMethod)
			throws IOException {

		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				Select select = new Select(element);

				if (select.isMultiple()) {
					if (DeSelectMethod.equals("Visible Text")) {
						for (String options : Data) {
							select.deselectByVisibleText(options);
						}
					} else if (DeSelectMethod.equals("Value")) {
						for (String options : Data) {
							select.deselectByValue(options);
						}
					} else if (DeSelectMethod.equals("Index")) {
						for (String options : Data) {
							select.deselectByIndex(Integer.parseInt(options));
						}
					} else {
						System.out.println("No Options Deselected");
					}

				}
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Success;
	}

	// Wrapper for deselect all drop down values
	public ErrorCodeForDebug dropDownMultiSelectDeSelectAll(By locator) {

		WebElement element = waitUntilclickableElement(locator);
		if (element != null) {
			try {
				Select select = new Select(element);
				if (select.isMultiple()) {
					select.deselectAll();
					System.out.println("All options are deselected");
				}
				return ErrorCodeForDebug.Success;
			} catch (StaleElementReferenceException ex) {
				System.out.println("Stale Element execption occured.!");
				return ErrorCodeForDebug.Error;
			}
		}
		return ErrorCodeForDebug.Success;
	}
}
