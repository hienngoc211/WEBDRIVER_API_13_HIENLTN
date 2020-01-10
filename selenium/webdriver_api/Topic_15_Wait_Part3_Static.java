package webdriver_api;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_15_Wait_Part3_Static {
	WebDriver driver;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	// Apply để chờ cho element hiển thị -> Tương tác vào -> findElement/findElements 
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
	// Apply để chờ cho page được load xong 
	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Static() throws Exception {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		System.out.println("Start sleep -  " + getCurrentTime());
		
		Thread.sleep(5000);
		
		System.out.println("End sleep -  " + getCurrentTime());
		
		driver.findElement(By.id("search_query_top")).sendKeys("Automation");
	}
	 
	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}