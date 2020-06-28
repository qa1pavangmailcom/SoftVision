package com.qa.tests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.qa.keyword.engine.KeyWordEngine;
/**
 * 
 * @author Pavan
 *
 */
public class BuyingAnItem {
	
	public KeyWordEngine keyWordEngine = new KeyWordEngine();;
	
	@BeforeMethod (description ="login to the appplication")
	public void loginTest(){
		keyWordEngine.startExecution("Login");
	}
	
	@Test (description ="search for an Item and checkout the order ")
	public void SearchItemTest(){
		keyWordEngine.startExecution("SearchItem");
		keyWordEngine.startExecution("CheckOutItem");
	}
	
	@AfterMethod (description ="logout of the appplication")
	public void Logout() {
		keyWordEngine.startExecution("logout");
	}
}
