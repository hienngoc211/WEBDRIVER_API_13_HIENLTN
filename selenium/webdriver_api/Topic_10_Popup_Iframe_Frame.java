package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_10_Popup_Iframe_Frame {
	WebDriver driver;
	Actions action;
	WebDriverWait waitExplicit;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
 		System.setProperty("webdriver.chrome.driver", "./libraries/chromedriver");
 		driver = new ChromeDriver();
 				
		
		action = new Actions(driver);
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	
	
	}
	 
	@Test
	public void TC_01_Popup() throws Exception {
		driver.get ("https://kyna.vn/");
		
		Thread.sleep(5000);
		
		// Case 01 - có popup xuất hiện 
		// Case 02 - Không có popup xuất hiện 
		
		System.out.println("Step 02 - count popup number");
		List <WebElement> fancyPopup = driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancy popup number = " + fancyPopup.size());
		
		// Step 03
		if (fancyPopup.size() > 0) {
			System.out.println("Step 03 - Check popup displayed and close it");
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();

		}
				
	//	boolean fancyPopup = driver.findElement(By.xpath("//div[@class='fancybox-inner']")).isDisplayed();
	//	System.out.println("Fancy popup displayed = " + fancyPopup);
	//	Assert.assertTrue(fancyPopup);
		
		
		// Step 04
		
		// Switch vào Iframe trước thì mới tương tác vs các element nằm trong Iframe đó được 
		System.out.println("Stepp 04 - Switch vaof Facebook iframe");
		// Web Element
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fanpage']//iframe")));
		
		
		boolean facebookIframe = driver.findElement(By.cssSelector("#facebook")).isDisplayed();
		Assert.assertTrue(facebookIframe);
		
		String facebookLikes = driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("Facebook likes number = " + facebookLikes);
		
		Assert.assertEquals(facebookLikes, "170K likes");
		
		// Switch về Main Page lại 
		driver.switchTo().defaultContent();
		
		Thread.sleep(3000);
		
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".fancybox-overlay-fixed")));
		
		driver.findElement(By.className("button-login")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(2000);
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("btn-submit-login")).click();
		
		WebElement userLogin = driver.findElement(By.xpath("//li[@class='account dropdown wrap']//span[@class='user']"));
		
		Assert.assertTrue(userLogin.isDisplayed());
		Assert.assertEquals(userLogin.getText(), "Automation FC");

	}
	 
	// @Test
	public void TC_02_() {
		driver.get ("");
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}