package com.uma.framework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.Assert;

//import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class FirstEample extends TestBase {
	static Properties prop;
	static FileInputStream fis;

	static Properties orprop;
	static FileInputStream fisor;
	static{
	
		try {

			fis = new FileInputStream(new File("./Resouce/Testdata/testdata.properties"));
			prop = new Properties();
			prop.load(fis);
			
			fisor = new FileInputStream(new File("./Resouce/OR/or.properties"));
			orprop = new Properties();
			orprop.load(fisor);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/*
	 * @Test(priority = 0) public void testMethod() {
	 * System.out.println("test method.."); }
	 * 
	 * @Test public void secondTestMethod() {
	 * System.out.println("second test method.."); }
	 * 
	 * @Test public void demo() { System.out.println("Demo Method...");
	 * 
	 * }
	 * 
	 * @Test(priority = 1) public void run() { System.out.println("Run Method...");
	 * 
	 * }
	 * 
	 * @Test public void zoo() { System.out.println("Zoo Method...");
	 * 
	 * }
	 * 
	 * @Test(priority = 2) public void john() { System.out.println("john method..");
	 * assertTrue(false);
	 * 
	 * }
	 */

	@Test(priority = 0)
	public void enterFirstName() {
		driver.findElement(By.xpath(orprop.getProperty("FirstNameField"))).sendKeys(prop.getProperty("FirstName"));
	}

	@Test(priority = 1)
	public void enterLastName() {
		driver.findElement(By.xpath(orprop.getProperty("LastNameField"))).sendKeys(prop.getProperty("LastName"));
	}

	@Test(priority = 2)
	public void enterMiddleName() {
		driver.findElement(By.xpath(orprop.getProperty("MiddleNameField"))).sendKeys(prop.getProperty("MiddleName"));
	}

	@Test(priority = 3)
	public void verifyHeaderFormText() {
		String createText = driver.findElement(By.xpath(orprop.getProperty("CreateProfileTest"))).getText();
		Assert.assertEquals(createText, "Create a profile");

	}
}
