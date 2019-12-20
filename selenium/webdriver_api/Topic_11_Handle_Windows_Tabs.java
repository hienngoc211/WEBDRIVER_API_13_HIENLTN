package webdriver_api;

import java.util.List;
import java.util.Set;
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

public class Topic_11_Handle_Windows_Tabs {
	WebDriver driver;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Windows() throws Exception {

		driver.get ("https://automationfc.github.io/basic-form/index.html");
		
		// Get ra ID của tab đang đứng (active)
		
		String parentID = driver.getWindowHandle();
		System.out.println("ID parent = " + parentID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Google");
		
		Assert.assertEquals(driver.getTitle(), "Google");
		Thread.sleep(2000);
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
			
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		closeAllWindowsWithoutParent(parentID);
		}
	
	@Test
	public void TC_02_Windows() {
		driver.get("http://live.guru99.com/index.php/");
		
		driver.findElement(By.xpath("//a[text()='Mobile']"));
		driver.findElement(By.xpath("//a[text()='IPhone']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product IPhone has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("Compare")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='IPhone']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		
		switchToWindowByTitle("Mobile");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		

	}
	@Test
	public void TC_03_Windows() throws Exception {
		driver.get("https://kyna.vn/");
		List<WebElement> fancyPopup = driver.findElements(By.xpath("//img[@class='fancybox-image']"));
		if (fancyPopup.size()>0) {
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		driver.findElement(By.xpath("facebook")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - Trang chủ | Facebook");
		Thread.sleep(2000);
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);

		driver.findElement(By.xpath("youtube")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn - YouTube");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//img[@alt='zalo']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Kyna.vn");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);
		
		
	}
	
	public void switchToWindowByID(String parentID) {
		// Lấy ra tất cả các ID đang có 
		Set<String> allWindows = driver.getWindowHandles();
		
		// Dùng vòng lặp duyệt qua từng ID (for - each)
		 for(String termID : allWindows) {
			 // Kiểm tra cái ID nào mà khác vs parent thì switch qua
			 if (!termID.equals(parentID)) {
				 // Switch qua tabs/windows đó 
				 driver.switchTo().window(termID);
				 break;
			 }
		 }
	}
	 
	public void switchToWindowByTitle(String expectedtitle) {

		// Lấy ra tất cả các ID đang có	
		Set<String>	allWindows = driver.getWindowHandles();
		
		// Dùng vòng lặp duyệt qua từng ID
		 for (String runWindows: allWindows) {
			 // Switch vào từng ID trước
			 driver.switchTo().window(runWindows);
			 // Get ra title đang có 
			 String currentWin = driver.getTitle();
			 // Title nào bằng với title expected thì break khỏi vòng lặp
			 if (currentWin.equals(expectedtitle)) {
				 break;
			 }
		 }
		}
	
	public void closeAllWindowsWithoutParent(String parentID) {
		// Lấy ra tất cả các ID
		Set<String> allWindows = driver.getWindowHandles();
		
		
		// Dùng vòng lặp duyệt qua từng ID
		 for (String runWindows : allWindows) {
			 
			 // Nếu ID nào mà khác vs parent thì switch qua 
			 if (!runWindows.equals(parentID)) {
				 // Switch vào window đó 
				 driver.switchTo().window(runWindows);
				 // 
				 driver.close();
				 }
		 }
		 driver.switchTo().window(parentID);
		 
		 
		
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}