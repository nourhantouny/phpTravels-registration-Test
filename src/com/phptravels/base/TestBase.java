package com.phptravels.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.phptravels.Utilties.WebListener;

public class TestBase {
  public static WebDriver driver;
  public static Properties prop;
  EventFiringWebDriver E_driver;
  WebListener webListener ;
  
  public TestBase() throws IOException {
	   prop = new Properties();
	  FileInputStream fls = new FileInputStream("D:\\Eclips_workspace\\phpTravel\\src\\com\\phptravels\\config\\config.properties");
	  prop.load(fls);
  }
	
  public void Initialization(String browser) {
	  
	    if ( browser.equalsIgnoreCase("chrome")) {
	    	System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
			 driver= new ChromeDriver();
			 
	    }
	    else if (browser.equalsIgnoreCase("firefox")) {
	    	System.setProperty("webdriver.gecko.driver", "D:\\Downloads\\geckodriver-v0.24.0-win64\\geckodriver.exe");
			 driver= new FirefoxDriver();
			 
	    }
		
		 driver.get(prop.getProperty("URL"));
		 E_driver = new EventFiringWebDriver(driver);
		 webListener = new WebListener();
		 E_driver.register(webListener);
		 driver= E_driver;
		 driver.manage().window().maximize();
	}
}
