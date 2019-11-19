package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@Test
public class Topic_04_Browsers_01 {
	WebDriver driver;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	public void TC_01_Browser()   {
		// Mở AUT 
		driver.get("http://live.demoguru99.com/index.php");
		
		// Đóng browser 
		//driver.quit();
		
		// Dong browser
		// driver.close();
		
		// Tra ve url cua page hien tai
		
		String homePageURl  = driver.getCurrentUrl();
		System.out.println(homePageURl);
		
		Assert.assertEquals(homePageURl, "http://live.demoguru99.com/index.php");
		
		// Tra ve title cua page hien tai 
		Assert.assertTrue(driver.getPageSource().contains("Magento Demo Store. All Rights Reserved."));
		
		// Tra ve Window ID (Handle Window) cua window/Tab hien tai 
		String homePageTabID = 	driver.getWindowHandle();
		System.out.println("Window ID = " + homePageTabID);
		
		// Tra ve widows GUID cua all tab/ windows dang co 
		 //Set<String> allWindowsID =  driver.getWindowHandles();
		 
		 //for (int i = 0; i<=allWindowsID.size(); i++) {
		//	 System.out.println(i);
		 //}
		 
		 //for(String id: allWindowsID) {
		//	 System.out.println (id);
		 //}
		
		// Nen tang tap hop (Colection) cua Java: List/Set/Queue
		
		// Khai bao bien element la email textbox
		WebElement emailTextbox = 	driver.findElement(By.xpath(""));
		
		// Khai bao 1 bien de lay ra tat ca cac link tren page hien tai 
		List <WebElement> links = driver.findElements(By.xpath("a"));
		
		// Them/lay/xoa 
		driver.manage().logs().get(LogType.PERFORMANCE);
		
		// Chờ cho element được stable để thao tác lên nó -> WebdriverWait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		
		// cho cho 1 page duoc load thanh cong trong khoang time bao nhieu 
		
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		
		// Dung cho Javascript Executor (Asynch) -> Sync
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		//F11
		driver.manage().window().fullscreen();
		
		
		System.out.println(driver.manage().window().getPosition());
		
		//Set vi tri 
		Point point = new Point(1048, 768);
		driver.manage().window().setPosition(point);
		
		
		// Get ra chieu rong/cao (size)
		System.out.println(driver.manage().window().getSize());
		
		Dimension dimension = new Dimension (1920, 1080);
		driver.manage().window().setSize(dimension);
		

		// giong user su dung
		
		driver.manage().window().maximize();	
		
		driver.navigate().back();
		driver.navigate().forward();
		
		driver.navigate().refresh();
		
		// Tracking history/gps/location
		driver.navigate().to("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		// Alert/ Windows/ Frame (Iframe)
		
		// Alert
		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().sendKeys("");;
		
		// Windows 
		driver.switchTo().window("Windows GUID");
		
		// Iframe/frame
		driver.switchTo().frame(driver.findElement(By.xpath("")));

		
}
		
	 
	public void TC_02_WebElement() {
		driver.get ("");
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}