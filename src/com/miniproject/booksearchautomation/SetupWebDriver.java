package com.miniproject.booksearchautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class SetupWebDriver {
	
	public static WebDriver driver;

	public static WebDriver getWebDriverChrome() {

		System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--disable-infobars");
		co.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
		co.addArguments("--disable-notifications");
		driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.get("https://openlibrary.org/");
		return driver;

	}
	
	public static WebDriver getWebDriverFfox() {
		
		System.setProperty("webdriver.gecko.driver", ".\\drivers\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://openlibrary.org/");
		return driver;
		
	}

}
