package com.base;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {

	static WebDriver browser = null;  
    
//    public WebDriver getDriver(String browserType) {
//    	if(browser == null) {
//    		openBrowser(browserType);
//    	}
//    	return driver;
//    }
//    

//    
////    public static String getBrowserInfo(){
////        // we have to cast WebDriver object to RemoteWebDriver here, because the first one does not have a method
////        // that would tell you which browser it is driving. (sick!)
////        Capabilities cap = ((RemoteWebDriver) DriverManager.getDriver()).getCapabilities();
////        String b = cap.getBrowserName();
////        String os = cap.getPlatformName().toString();
////        String v = cap.getBrowserVersion();
////        return String.format("%s v:%s %s", b, v, os);
////    }
    
}
