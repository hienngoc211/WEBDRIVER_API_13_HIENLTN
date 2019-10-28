package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironments {
	//Khai báo một biến driver đại diện cho Selenium 
	WebDriver driver;
	
 //Pre-condition
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo trình duyệt 
	driver = new FirefoxDriver();
	
		//Chờ element được hiển thị trước khi tương tác trong vòng 30
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		//Phóng to trình duyệt 
	driver.manage().window().maximize();
	
		//Mở trang web (AUT: Application Under Test)
	driver.get("http://demo.guru99.com/v4/");
	}
	 
	@Test
	public void TC_01_ValidateCurrentUrl() {
		
	//Verifty Login Page Url matching
	String loginPageUrl = driver.getCurrentUrl();
	
	Assert.assertEquals(loginPageUrl, "http://demo.guru99.com/v4/");
	}
	 
	@Test
	public void TC_02_ValidatePageTitle() {
		
	//Verify Login Page title
	String loginPageTitle = driver.getTitle();
	Assert.assertEquals(loginPageTitle, "Guru99 Bank Home Page");
	}
	 
	@Test
	public void TC_03_LoginFormDisplayed() {
		
	//Verify Login form displayed
	Assert.assertTrue(driver.findElement(By.xpath("//form[@name='frmLogin']")).isDisplayed());
	}
	 
 //Post - condition
	@AfterClass
	public void afterClass() {
		
	// Tắt trình duyệt 
	driver.quit();
	}
	 
	}