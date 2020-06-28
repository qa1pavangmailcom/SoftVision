package com.qa.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.keyword.base.Actions;
import com.qa.keyword.base.Base;

import io.appium.java_client.AppiumDriver;

/**
 * @author Pavan
 */

 /* Keyword Engine reads the data from the excel sheet 
 * Traversing through each row except first row(Header), till the last row 
 * Handling each step with a step definition in Action class
 * Getting the input data from the excel file for each step
 */
public class KeyWordEngine {
	public AppiumDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public Base base;
	public WebElement element;
	public Actions actionclass = new Actions();
	
	//providing the excel sheet path 

	public final String SCENARIO_SHEET_PATH = "C:\\Users\\AC37118\\Downloads\\Keyword-Driven-SoftvisionProject\\Keyword-Driven-SoftVision\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\Application_keywords_scenarios.xlsx";

	public void startExecution(String sheetName) {

		FileInputStream file = null;
		try {
			file = new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		sheet = book.getSheet(sheetName);
		int k = 0;
		
		// Traversing through the each row of a excel sheet
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			try {

				String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
				String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
				String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
				String inputdata = sheet.getRow(i + 1).getCell(k + 4).toString().trim();
				
		//Handling reusable step definition for each test step
				
				switch (action) {
				case "OpenApplication":
					base = new Base();
					prop = base.init_properties();
					break;
				case "Click":
					actionclass.clickonElement(locatorType, locatorValue );
					break;
				case "SendKeys":
					actionclass.sendInputToAField(locatorType, locatorValue, inputdata );
					break;	
				case "Assert":
					actionclass.assertion(locatorType, locatorValue, inputdata);
					break;
				case "PickoneItemfromtheList":
					 element=actionclass.pickOneElementFromTheList(locatorType, locatorValue, inputdata);
					element.click();
					break;	
				case "quit":
					driver.quit();
					break;
				default:
					break;
				}
			} catch (Exception e) {

			}

		}

	}
}
