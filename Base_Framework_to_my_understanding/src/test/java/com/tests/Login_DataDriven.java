package com.tests;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.pom.LoginPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.utils.SeleniumUIUtils;

public class Login_DataDriven extends BaseClass {
	LoginPage login = new LoginPage();
	ExtentTest logger;
	XSSFSheet sheet = null;
	SeleniumUIUtils UI = null;
	WebDriver driver = null;
	
	@BeforeClass
	public void preSetup() throws IOException {
		sheet = readSheet("Login");
	}
	
	@Parameters("browser")
	@BeforeClass
	public void init(String browser)
	{ 
		browserType = browser;
		driver = openBrowser(browser);
		UI = new SeleniumUIUtils(driver);
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.manage().window().maximize();
	}
	
	
	@BeforeMethod
	public void Setup() {
		System.out.println("Before test");
		logger = report.startTest("Login Demo");
	}
	
	@Test(description="This TC will perform valid login")
	public void loginToApplication() throws Throwable
	{
		UI.sendKeys(login.userName(driver), data.getDataAsString(sheet, "UserName", 1));
	    // it is data.getDataAsString(sheet name of excel workbook,column name,row number); this was defined in ExcelUtils class.
		logger.log(LogStatus.INFO, "entered username as : "+  data.getDataAsString(sheet, "UserName", 1));
		UI.sendKeys(login.password(driver), "password");
		logger.log(LogStatus.INFO, "entered password as : "+  data.getDataAsString(sheet, 0, 1));
		UI.click(login.loginButton(driver));
		logger.log(LogStatus.INFO, "clicked on button");

	}       
	
	
	
	@AfterMethod
	public void captureResult(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			String path = UI.takeSnapShot(driver, result.getName());
			System.out.println("img path "+ path);
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}else if(result.getStatus() == ITestResult.SKIP) {
			logger.log(LogStatus.SKIP, "This test skipped");
		}
		report.endTest(logger);
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
