package com.miniproject.booksearchautomation;

import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


public class Main {
	
	public static WebDriver driver;

	public WebDriver createDriver(String browser) {

		if(browser.equalsIgnoreCase("Chrome"))
			return SetupWebDriver.getWebDriverChrome();
		else if(browser.equalsIgnoreCase("Firefox"))
			return SetupWebDriver.getWebDriverFfox();
		else
			return SetupWebDriver.getWebDriverChrome();

	}
	
	public void showCatelog() {
		String bkNum;
		String bkName;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		System.out.println("Books available in library");
		int i=0;
		while(true) {
			try {
				bkName = (String) js.executeScript("return document.getElementsByClassName('category-title')["+i+"].innerHTML");
				bkNum = (String) js.executeScript("return document.getElementsByClassName('category-count')["+i+"].innerHTML");
				bkNum = bkNum.replace(",", "");
				System.out.println(bkName+": "+bkNum);
				i++;
			}catch(Exception e) {
				break;
			}
		}
	}

	public String getElement() throws InterruptedException {

		driver.findElement(By.xpath("//summary[contains(text(),'Browse')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Subjects')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'See more...')]")).click();
		Thread.sleep(3000);
		return driver.findElement(By.xpath("//a[contains(text(),'Tamil')]/following::span/b")).getText();

	}
	
	public int countBook(String st) {
		// TODO Auto-generated method stub
		String str = "";
		int num = 0;
		str = st.split(" ")[0];
		str = String.join("", str.split(","));
		num = Integer.parseInt(str);
		return num;
	}

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Enter Chrome or Firefox");
			String browser = sc.nextLine();
			while(!(browser.equalsIgnoreCase("Firefox") || browser.equalsIgnoreCase("Chrome"))) {
				System.out.println("Enter Chrome or Firefox");
				browser = sc.nextLine();
			}
			Main mn = new Main();
			driver = mn.createDriver(browser);
			mn.showCatelog();
			try {
				int num = mn.countBook(mn.getElement());
				if(num>1000) {
					System.out.println("The number of 'Tamil' Books present "+num);
					System.out.println("Condition(>1000) satisfied...");
				}else {
					System.out.println("The number of 'Tamil' Books present "+num);
					System.out.println("Condition(>1000) not satisfied...");
				}
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
			driver.quit();
		}
		

	}

}
