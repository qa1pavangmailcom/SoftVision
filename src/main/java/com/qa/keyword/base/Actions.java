package com.qa.keyword.base;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

/**
 * 
 * @author Pavan
 *
 */

public class Actions extends Base {
	public static WebElement element;
	By locator;
	
	/*
	 * @parameters - locatortype and locatorvalues that are passing from the KeywordEngine
	 *  Result- Clicks on a element 
	 */
	public void clickonElement(String locatorType, String locatorvalue) {
		try {
			locator = locatorValue(locatorType, locatorvalue);
			element = driver.findElement(locator);
			Assert.assertTrue(element.isDisplayed(), "Unable to find the element");
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}
	
	/*
	 * @parameters - locatortype and locatorvalue, input data  that are passing from the KeywordEngine
	 *  Result- input field will be filled with the input data 
	 */
	
	public void sendInputToAField(String locatorType, String locatorvalue,String inputdata) {
		try {
			locator = locatorValue(locatorType, locatorvalue);
			element = driver.findElement(locator);
			Assert.assertTrue(element.isDisplayed(), "Unable to find the element");
			element.clear();
			element.sendKeys(inputdata);
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}
	
	/*
	 * @parameters - locatortype and locatorvalue, input data  that are passing from the KeywordEngine
	 *  Result- fails the test if assertion fails 
	 */
	
	public void assertion(String locatorType, String locatorvalue,String expectedtext) {
		try {
			locator = locatorValue(locatorType, locatorvalue);
			String actualmessage = driver.findElement(locator).getText();
			Assert.assertEquals(actualmessage, expectedtext ,"Error:  expected" +expectedtext+"but found"+actualmessage); 
		} catch (NoSuchElementException e) {
			System.err.format("No Element Found to enter text" + e);
		}
	}
	
	/*
	 * @parameters - locatortype and locatorvalue, inputdata  that are passing from the KeywordEngine
	 *  Result- fails the test if assertion fails 
	 */
	
	public WebElement pickOneElementFromTheList(String locatorType, String locatorvalue,String expectedtext) {
		try {
			locator = locatorValue(locatorType, locatorvalue);
			List<WebElement> listofelements = driver.findElements(locator);
			element=listofelements.get(Integer.parseInt(expectedtext));		
			} catch (NoSuchElementException e) {
				System.err.format("No Element Found to enter text" + e);
				}
		return element;
		
	}

	
	/*
	 * @parameters - locatortype and locatorvalue,   that are passing from the KeywordEngine
	 *  Result- it gives a locator
	 */
	
	public By locatorValue(String locatorType, String value) {
		By by;
		switch (locatorType) {
				case "id":
					by = By.id(value);
					break;
				case "name":
					by = By.name(value);
					break;
				case "xpath":
					by = By.xpath(value);
					break;
				case "cssselctor":
					by = By.cssSelector(value);
					break;
				case "linkText":
					by = By.linkText(value);
					break;
				case "partialLinkText":
					by = By.partialLinkText(value);
					break;
				default:
			by = null;
			break;
		}
		return by;
	}

}
