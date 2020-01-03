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
public class Topic_14_UploadFile {
	String projectPath = System.getProperty("user.dir");
	
	// /Users/hienle/Documents/02_WebDriverAPI
	
	WebDriver driver;
	String AppiumPath = projectPath + "/uploadFile/Appium.jpg";
	String seleniumPath = projectPath + "/uploadFile/selenium.jpg";
	String specflowPath = projectPath + "/uploadFile/specflow.jpg";
	String testcompletePath = projectPath + "/uploadFile/testcomplete.jpg";
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_UploadbySendKey() throws Exception {
		driver.get ("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile = find("//input[@type='file']");
		uploadFile.sendKeys(AppiumPath + "\n" + seleniumPath + "\n" + specflowPath + "\n" + testcompletePath);
		
		//uploadFile = find("//input[@type='file']");
		//uploadFile.sendKeys(seleniumPath);
		
		//uploadFile = find("//input[@type='file']");
		//uploadFile.sendKeys(specflowPath);
		
		//uploadFile = find("//input[@type='file']");
		//uploadFile.sendKeys(testcompletePath);

		
		Thread.sleep(4000);
		
		find("//table//button[@class='btn btn-primary start']").click();
		
		Assert.assertTrue(find("//p/a[@title='Appium.jpg']").isDisplayed());
		
		
	}	
	 
	@Test
	public void TC_02_() {
		driver.get ("");
		
	}
	
	public WebElement find(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}