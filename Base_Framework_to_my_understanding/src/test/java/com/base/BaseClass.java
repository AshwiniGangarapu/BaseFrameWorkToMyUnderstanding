package com.base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.utils.ExcelUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass
{
	public static ExtentReports report;
	// to mention the envireonment and configurations.
	public static XSSFWorkbook workbook = null;
	public XSSFSheet sheet = null;    
	public ExcelUtils data = new ExcelUtils();
	
	public static String browserType = "chrome";
	public DriverManager dm = new DriverManager();
	
	
	@BeforeSuite
	public void start() throws IOException {
		
		//Extent report
		String timeStamp = new SimpleDateFormat("dd-MM-YYYY_HH-mm-ss").format(new Date());
		//it is append the time to the report name.The below line is doing it.
		String reportPath = System.getProperty("user.dir")+"\\Reports\\Report_"+timeStamp+".html";
		//configuring where to save the report.
		System.out.println("report path : "+ reportPath);
		report = new ExtentReports(new File(reportPath).getAbsolutePath(), true);
		//above we created the report object.
		
		String path = System.getProperty("user.dir") + "\\src\\test\\resources\\data\\Project.xlsx";
		workbook = data.getWorkbook(path);
	}
		
	
	public XSSFSheet readSheet(String sheetName) throws IOException {
		return data.getSheet(workbook, sheetName);
	}
	
	
	public WebDriver openBrowser(String browserType) {
		WebDriver driver = null;
	//	String basePath = System.getProperty("user.dir") + "\\src\\test\\resources\\drivers\\";
		if(browserType.toLowerCase().equals("firefox")) {
	      //   System.setProperty("webdriver.gecko.driver", basePath+"geckodriver.exe");
	         WebDriverManager.firefoxdriver().setup();
	         driver = new FirefoxDriver();
		}else if(browserType.toLowerCase().equals("chrome")) {
			WebDriverManager.chromedriver().setup();
	       //  System.setProperty("webdriver.chrome.driver", basePath+"chromedriver.exe");
	         driver = new ChromeDriver();
		}else {
	         throw new IllegalArgumentException("The Browser Type is Undefined");
		}
		
		return driver;
	}
	
	
	@AfterSuite
	public void closeApplication()
	{
		report.flush();		
	}	
	
}
 