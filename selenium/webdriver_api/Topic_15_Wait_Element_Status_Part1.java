package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_15_Wait_Element_Status_Part1 {
	WebDriver driver;
	WebDriverWait waitExplicit;
	boolean status;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	waitExplicit = new WebDriverWait(driver,5);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01lementDisplayedOrVisible() {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI và có trong DOM : PASSED 
		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']"))); // chờ cho element displayed/visible 
		status = driver.findElement(By.xpath("//button[@id='SubmitLogin']")).isDisplayed(); // Kiểm tra element displayed
		System.out.println("Element hiển thị trên UI + có trong DOM = " + status);
		
		
		// ĐK 2 - Element không hiển thị trên UI và có trong DOM : PASSED
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		status = driver.findElement(By.xpath("//div[@id='create_account_error']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + có trong DOM = " + status);
		
		
		// ĐK 3 - Element không hiển thị trên UI và không có trong DOM : FAILED
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		status = driver.findElement(By.xpath("//input[@id='id_order']")).isDisplayed();
		System.out.println("Element không hiển thị trên UI + không có trong DOM = " + status);
		
	}
	 
	@Test
	public void TC_02_ElementUndisplayedOrInvisible() {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI và có trong DOM : FAILED
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		// ĐK 2 - Element không hiển thị trên UI và có trong DOM : PASSED 
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// ĐK 3 - Element không hiển thị trên UI và không có trong DOM : PASSED 
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@id='id_order']")));
		
	}
	 
	

	@Test
	public void TC_03_ElementPresence() {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// ĐK 1 - Element có hiển thị trên UI và có trong DOM : PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
		
		// ĐK 2 - Element không hiển thị trên UI và có trong DOM : PASSED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));
		
		// ĐK 3 - Element không hiển thị trên UI và không có trong DOM : FAILED
		waitExplicit.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='id_order']")));
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}