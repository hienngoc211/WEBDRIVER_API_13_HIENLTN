package webdriver_api;

import java.util.List;
import java.util.Random;
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
public class Topic_07_Textbox_TextArea {
	WebDriver driver;
	String username = "mngr233468";
	String password = "tYqAhaq";
	String customerName = "Micheal Jackson";
	String gender = "male";
	String dateOfBirth = "1980-06-06";
	String address = "255 PO Hamlet";
	String city = "New York";
	String state = "California";
	String pin = "777999";
	String phone = "0905777999";
	String email = "jackson0606" + randomNumber() + "gmail.com";
	
	By nameTextbox = By.name("name");
	By genderRadio = By.xpath("//input[@value='m']");
	By dobTextbox = By.name("dob");
	

	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	driver.get("http://demo.guru99.com/v4/index.php");
	
	driver.findElement(By.name("uid")).sendKeys("username");
	driver.findElement(By.name("password")).sendKeys("password");
	driver.findElement(By.name("btnLogin")).click();
	
	String homePageWelcomeMsg = driver.findElement(By.tagName("marquee ")).getText();
	Assert.assertEquals(homePageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
	
	Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "'] ")).isDisplayed());
	
	}
	 
	@Test
	public void TC_01_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Account']")).click();
		
	}
	@Test
	public void TC_02_EditCustomer() {
		driver.get ("");
		
	}
	 
	public int randomNumber() {
		Random rand = new Random();
		return rand.nextInt(100000);
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}