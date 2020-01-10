package webdriver_api;

import java.util.Date;
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


public class Topic_15_Wait_Part4_Implicit_Explicit {
	WebDriver driver;
	WebDriverWait waitExplicit;
	By startButtonBy = By.xpath("//div[@id='start']/button");
	By loadingImageBy = By.xpath("//div[@id='loading']/img");
	By helloworldTextBy = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
	
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	
	// Rõ ràng: Chờ cho element theo từng trạng thái cụ thể 
	waitExplicit = new WebDriverWait(driver, 10);
	
	// Wait ngầm định: không chờ cho một element nào có trạng thái cụ thể -> đi tìm element 
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // 1
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Implicit() {
		driver.get ("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// 1 - Mở app
		// 2 - Chờ cho Start button được hiển thị để thao tác 
		// 3 - Click Start button 
		// 4 - Hiển thị Inprogress Loading bar : Invisible (Undisplayed)
		// Để Loading bar này biến mất mất (5s) dù nhanh hay chậm 
		// 5 - Hello world text được hiển thị : Visible (Displayed)
		
		
		// Check cho element được hiển thị (visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());
		
		// Click vaò start button 
		System.out.println("Start click -  " + getCurrentTime());
		startButton.click();
		System.out.println("End click -  " + getCurrentTime());
		
		// Loading icon hiển thị và mất tới 5s mới biến mất 
		
		// Set lại 10s cho implicit wait (3s không còn ý nghĩa) - bị ghi đè
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS); // 2

		// Check cho helloworld được hiển thị 
		System.out.println("Start helloworld -  " + getCurrentTime());
		WebElement helloworldText = driver.findElement(helloworldTextBy);
		System.out.println("End helloworld -  " + getCurrentTime());
		
		System.out.println("Start displayed -  " + getCurrentTime());
		Assert.assertTrue(helloworldText.isDisplayed());
		System.out.println("End displayed -  " + getCurrentTime());

	}
	@Test
	public void TC_02_Implicit_Overide() {
		// Apply timeout của 1 hay 2 
		driver.get ("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		// Check cho element được hiển thị (visible)
		
				WebElement startButton = driver.findElement(startButtonBy);
				Assert.assertTrue(startButton.isDisplayed());
				
				// Click vaò start button 
				System.out.println("Start click -  " + getCurrentTime());
				startButton.click();
				System.out.println("End click -  " + getCurrentTime());
				
				// Loading icon hiển thị và mất tới 5s mới biến mất 
				

				// Check cho helloworld được hiển thị 
				System.out.println("Start helloworld -  " + getCurrentTime());
				WebElement helloworldText = driver.findElement(helloworldTextBy);
				System.out.println("End helloworld -  " + getCurrentTime());
				
				System.out.println("Start displayed -  " + getCurrentTime());
				Assert.assertTrue(helloworldText.isDisplayed());
				System.out.println("End displayed -  " + getCurrentTime());
	}
	 @ Test 
	 public void TC_03_Explicit_Visible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// Click start button
			waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));	
			driver.findElement(startButtonBy).click();
			
			// Loading icon hiển thị và biến mất sau 5s 
			
			// Wait cho helloworld được visible trước khi check display
			System.out.println("Start wait visible -  " + getCurrentTime());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
			System.out.println("End wait visible -  " + getCurrentTime());		
			
			System.out.println("Start displayed -  " + getCurrentTime());
			Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
			System.out.println("End ddisplayed -  " + getCurrentTime());
	 }
	 @ Test 
	 public void TC_04_Explicit_Invisible() {
			driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
			
			// Click start button
			waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));	
			driver.findElement(startButtonBy).click();
			
			// Loading icon hiển thị và biến mất sau 5s 
			System.out.println("Start wait invisible -  " + getCurrentTime());
			waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImageBy));
			System.out.println("End wait invisible -  " + getCurrentTime());
		
			// Wait cho helloworld được visible trước khi check display
			System.out.println("Start wait visible -  " + getCurrentTime());
			waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(helloworldTextBy));
			System.out.println("End wait visible -  " + getCurrentTime());		
			
			System.out.println("Start displayed -  " + getCurrentTime());
			Assert.assertTrue(driver.findElement(helloworldTextBy).isDisplayed());
			System.out.println("End ddisplayed -  " + getCurrentTime());
	 }
	 
	 @ Test 
	 public void TC_05_Date_Implicit() { 
		 driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		 
		 // In ra ngày được chọn: No Selected Dates to display
		 WebElement dateSelectedText = driver.findElement(By.id("ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel"));
		 System.out.println("Date seclected = " + dateSelectedText );
		 driver.findElement(By.xpath("//a[text()='7']")).click();

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