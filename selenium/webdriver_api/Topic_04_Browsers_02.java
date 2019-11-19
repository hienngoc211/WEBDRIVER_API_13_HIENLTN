package webdriver_api;

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
public class Topic_04_Browsers_02 {
	WebDriver driver;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Url() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Verify 'Login Page' Url");
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/" );
		
		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' buton");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify 'Register Page' Url");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		
	}
	 
	@Test
	public void TC_02_Title() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Verify 'Login Page' title");
		String loginPageTitle = driver.getTitle();
		Assert.assertEquals(loginPageTitle, "Customer Login");
		
		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' buton");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify 'Register Page' Title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
		
		
	}
	 
	@Test
	public void TC_03_Navigate() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Click 'CREATE AN ACCOUNT' buton");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 04 - Verify 'Register Page' url ");
		String registerPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		System.out.println("Step 05 - Back to 'Login page' ");
		driver.navigate().back();

		System.out.println("Step 06 - Verify 'Login page' url");
		String loginPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 07 - Forward to 'Register page'");
		driver.navigate().forward();
		
		System.out.println("Step 08 - Verify 'Register page' title");
		String registerPageTitle = driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");

		}
	
	@Test
	public void TC_04_GetPageSourceCode() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/index.php/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		
		System.out.println("Step 03 - Verify Register Page contain text 'Create an Account'");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account")); 
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}