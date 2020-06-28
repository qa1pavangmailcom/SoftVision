package com.qa.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
/**
 * @author Pavan
 */
public class Base {
	public AppiumDriver<WebElement> driver;
	public Properties prop;
	static Logger log = Logger.getLogger(Base.class);
	public Properties init_properties(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\AC37118\\Downloads\\Keyword-Driven-SoftvisionProject\\Keyword-Driven-SoftVision\\src\\main\\java\\com\\qa\\keyword\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static DesiredCapabilities androidcapabilities() {
		log.info("Adding the device desiredcapabilities");
		DesiredCapabilities caps= new DesiredCapabilities();
		caps.setCapability("deviceName", System.getProperty("deviceName"));
		caps.setCapability("udid", System.getProperty("udid"));
		caps.setCapability("platformName",System.getProperty("platformName"));
		caps.setCapability("platformVersion", System.getProperty("platformVersion"));
		caps.setCapability("appPackage", System.getProperty("appPackage"));
		caps.setCapability("appActivity", System.getProperty("appActivity"));
		caps.setCapability("autoGrantPermissions", System.getProperty("autoGrantPermissions")); 
//		caps.setCapability("fullReset", "true"); 
		return caps;
	}
	
	public AppiumDriver<WebElement> init_driver(String platform){
		if(platform.equalsIgnoreCase("android")) {
			log.info("launching android device");
			try {
				driver= new AndroidDriver<WebElement >(new URL("http://127.0.0.1:4723/wd/hub"),Base.androidcapabilities());
		}catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		}else if(platform.equalsIgnoreCase("IOS")) {
			try {
				driver= new IOSDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"),Base.androidcapabilities());
		}catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		}
		return driver;
	}

}
