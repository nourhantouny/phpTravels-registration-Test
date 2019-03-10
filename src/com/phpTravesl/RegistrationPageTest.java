package com.phpTravesl;


import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.phptravels.Utilties.TestUtils;
import com.phptravels.base.TestBase;

public class RegistrationPageTest extends TestBase {
 
  
	public RegistrationPageTest() throws IOException {
		super(); // to initial an object from TestBase has the properties file
		
	}
    @Parameters({"browser"})
	@BeforeMethod
	public void setup(String browser) {
		Initialization(browser);
	}
	
	@Test(priority =1 , dataProvider =  "testDat")
	public void Registartion(String Fname , String Lname, String Email , String PassW , String ConfirmPasss) {
		   WebElement FirstNameText= driver.findElement(By.xpath("//input[@name='firstname']"));
	       WebElement LastNameText= driver.findElement(By.xpath("//input[@name='lastname']"));
	       WebElement EmailBox = driver.findElement(By.xpath("//input[@name='email']"));
	       WebElement Password = driver.findElement(By.xpath("//input[@name='password']"));
	       WebElement ConfirmPassword = driver.findElement(By.xpath("//input[@name='confirmpassword']"));
	       WebElement SubmitButton   = driver.findElement(By.cssSelector("button[class='signupbtn btn_full btn btn-action btn-block btn-lg']"));
	       FirstNameText.sendKeys(Fname);
	       LastNameText.sendKeys(Lname);
	       EmailBox.sendKeys(Email);
	       Password.sendKeys(PassW);
	       ConfirmPassword.sendKeys(ConfirmPasss);
	       SubmitButton.click();
	       String ExpectURL = "https://www.phptravels.net/register";
	       String ActualURL = driver.getCurrentUrl();
	       SoftAssert softassert = new SoftAssert();
	       System.out.println(ActualURL);
	       
	       boolean Fname_Flag= Character.isUpperCase( Fname.charAt(0)); 
	       boolean Lname_Flag= Character.isUpperCase( Fname.charAt(0)); 
	       //boolean ValidEmail = Email.contentEquals("+ @ + .com" );
	       
	       softassert.assertTrue(Fname_Flag, " The First letter must be Capital in the First Name");
	       softassert.assertTrue(Lname_Flag, " The First letter must be Capital in the Last Name");
	       softassert.assertNotEquals(Fname, Lname);
	       softassert.assertEquals(PassW, ConfirmPasss);
	       softassert.assertNotEquals(ActualURL,ExpectURL, " invalid registration");
	       
	       softassert.assertAll();
	       
	       
	}
	
	
	@AfterMethod
	public void screenshot(ITestResult result) throws IOException {
		
	    if(ITestResult.FAILURE==result.getStatus()){
		       TestUtils.TakeSnapShot();
	     }
	    
	       driver.quit();
     }
	
	@DataProvider
	public  Object[][] testDat() throws Throwable{
		Object data[][]= TestUtils.GetDataFromExcelSheet("Test_Data");
		
		return data;
	}
}