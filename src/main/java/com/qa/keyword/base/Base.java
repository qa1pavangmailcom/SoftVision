package com.qa.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;


/**
 * 
 * @author Pavan
 *
 */
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
public class Base {
	
	public AppiumDriver driver;
	public Properties prop;
	
	public Properties init_properties(){
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("C:\\Users\\AC37118\\Downloads\\Keyword-Driven-SoftvisionProject\\Keyword-Driven-SoftVision\\src\\main\\java\\com\\qa\\hs\\keyword\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static DesiredCapabilities androidcapabilities() {
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
	
	public AppiumDriver init_driver(String platform){
		if(platform.equalsIgnoreCase("android")) {
			try {
				driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),Base.androidcapabilities());
		}catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		}else if(platform.equalsIgnoreCase("IOS")) {
			try {
				driver= new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"),Base.androidcapabilities());
		}catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		}
		}
		return driver;
	}

}
