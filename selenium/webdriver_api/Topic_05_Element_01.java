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
public class Topic_05_Element_01 {
	WebDriver driver;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Locator() throws Exception {
		driver.get ("https://automationfc.github.io/basic-form/index.html");
		
	// --------------------- THAO TÁC VỚI 1 ELEMENT -------------------
	// Nếu chỉ tương tác lên element 1 lần thì ko cần khai báo biến 
		driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
	// Nếu elemindent được tương tác/ sử dụng nhiều lần thì nên khai bao biến 
		WebElement emailTextbox = driver.findElement(By.xpath("//input[@id='mail']"));
		emailTextbox.clear();
		emailTextbox.sendKeys("automationfc.vn@gmail.com");
		Assert.assertTrue(emailTextbox.isDisplayed());
	
	// ----------------------- THAO TÁC VỚI >= 2 ELEMENT ------------------
	// Tương tác lên all links ở page hiện tại 
		List <WebElement> links = driver.findElements(By.xpath("//a"));
		
	// Có bn link ở page hiện tại 
		System.out.println("Link size = " + links.size());
		
	// Get all text link
		for (WebElement link: links)
		{
			System.out.println("Text = " + link.getText());
		}		
	
		
	// Textbox/ textarea/ link/ button/ checkbox/ radio/......
		
		WebElement element = driver.findElement(By.cssSelector("..."));
		
	// ----------------- WEB ELEMENTS METHOD --------------------
	// Xoá dữ liệu trong textbox/ Textarea/ dropdown list (editable) trước khi sendkeys (đảm bảo tính toàn vẹn của dữ liệu)
	//	 element.clear();
		
	// Input dữ liệu trong textbox/ Textarea/ dropdown list (editable) 	
	//	 element.sendKeys("0999333666");
		
	// Click vao link/button/checkbox/radio button/image/ dropdown list (custom)
	//	 element.click();
		 
		WebElement passwordTextbox = driver.findElement(By.id("password"));
		String passwordTextboxHint = passwordTextbox.getAttribute("placeholder");
		System.out.println(passwordTextboxHint);
		
		// GUI: Graphic/ size/ color/ location/ position...
		WebElement buttonDisabled = driver.findElement(By.id("button-disabled"));
		String buttonBackground = buttonDisabled.getCssValue("background-color");
		String buttonFontsize = buttonDisabled.getCssValue("font-size");
		System.out.println(buttonBackground);
		System.out.println(buttonFontsize);
		
		// Lấy ra vị trí của nó so với độ phân giải của màn hình 
		buttonDisabled.getLocation();
	
		buttonDisabled.getSize();
		
		String buttonTagname = buttonDisabled.getTagName();
		System.out.println(buttonTagname);
		
		System.out.println(driver.findElement(By.tagName("h1")).getText());
		
		// Kiem tra cho bat ki mot element nao duoc hien thi tren page hay khong?
		// isdisplayed/ visible (User: co the nhin thay va thao tac dc)
		
		WebElement userAvatar5 = driver.findElement(By.xpath("//img[@alt='User Avatar 05']/following-sibling::div/h5"));
		Assert.assertFalse(userAvatar5.isDisplayed());
		
		// Kiem tra cho 1 element co duoc phep thao tac hay ko (no co bi disable)
		Assert.assertFalse(buttonDisabled.isEnabled());
		
		// Kiem tra cho 1 element da duoc chon hay chua (radio/checkbox)
		WebElement under18Radio = driver.findElement(By.id("18"));
		Assert.assertFalse(under18Radio.isSelected());
		under18Radio.click();
		Assert.assertTrue(under18Radio.isSelected());
		
		// Work cho element la form 
		userAvatar5.submit();
	}
	 
	@Test
	public void TC_02_() {
		driver.get ("");
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}