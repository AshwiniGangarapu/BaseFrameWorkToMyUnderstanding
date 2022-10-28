package com.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.base.BaseClass;

public class SeleniumUIUtils {
	SeleniumWaitUtils wait = new SeleniumWaitUtils();
	WebDriver driver = null;
	
	public SeleniumUIUtils(WebDriver driver) {
		this.driver = driver;
	}

	
	public void click(WebElement element) {
		wait.doWaitForElement(element, driver).click();
	}
	
	public void sendKeys(WebElement element, String value) {
		wait.doWaitForElement(element, driver).sendKeys(value);
	}
	
	public WebElement getElement(WebElement element) {
		return wait.doWaitForElement(element, driver);
	}
	
	
    public String takeSnapShot(WebDriver webdriver, String testCaseName) {
    	File SrcFile, DestFile = null;
    	try {
    	String basePath = System.getProperty("user.dir")+"\\ScreenShots\\";
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        DestFile=new File(basePath+testCaseName+".png");
        FileUtils.copyFile(SrcFile, DestFile);
    	}catch(Exception ee) {
    		ee.printStackTrace();
    	}
       return DestFile.getAbsolutePath();
    }
}

