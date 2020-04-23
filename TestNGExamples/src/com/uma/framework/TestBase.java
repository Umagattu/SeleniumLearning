package com.uma.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class TestBase {

	public WebDriver driver;
	Properties prop;
	FileInputStream fis;

	@BeforeSuite
	public void beforeSuiteMethod() {
		System.out.println("Before suite method..");
	}

	@AfterSuite
	public void afterSuiteMethod() {
		System.out.println("After suite method..");
	}

	@BeforeTest
	@Parameters("browser")
	public void beforeTestMethod(String browserName) {
		
		try {

		    fis= new FileInputStream(new File("./Resouce/Config/config.properties"));
			prop = new Properties();
			prop.load(fis);
				
	
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch (IOException e)
		{
			e.printStackTrace();
		}
		
		

		if (browserName.equalsIgnoreCase(prop.getProperty("ChromeBrowser"))) {
			
			System.setProperty(prop.getProperty("ChromeDriverKey"),prop.getProperty("ChromeDriverPath"));
			driver = new ChromeDriver();
			
		} else if (browserName.equalsIgnoreCase(prop.getProperty("FirefoxBrowser"))) {
			
			System.setProperty(prop.getProperty("FirefoxDriverKey"),prop.getProperty("FirefoxDriverPath"));
			
			File pathBinary = new File("C:\\Users\\uma.gattu\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);   
			DesiredCapabilities desired = DesiredCapabilities.firefox();
			FirefoxOptions options = new FirefoxOptions();
			desired.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options.setBinary(firefoxBinary));
		    driver = new FirefoxDriver(options);
			
			
			
		}else {
			System.out.println("Please pass the parameter in testng.xml either in chrome or firefox... ");
			
		}
		driver.manage().window().maximize(); // maximizing the browser
		driver.get(prop.getProperty("ApplicationURL")); // launching url or application
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTestMethod() {
		//driver.close();

	}

	@BeforeClass
	public void beforeClassMethod() {
		System.out.println("Before class method..");
	}

	@AfterClass
	public void afterClassMethod() {
		System.out.println("After class method..");
	}

	@BeforeMethod
	public void beforeMethodExample() {
		System.out.println("Before method example..");
	}

	@AfterMethod
	public void afterMethodExample() {
		System.out.println("After method example..");
	}
}
