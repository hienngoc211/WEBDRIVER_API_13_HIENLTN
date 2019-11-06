package webdriver_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath_Part1_Ex_Finished {
	//Khai báo một biến driver đại diện cho Selenium 
	WebDriver driver;
	// String username = "mngr231005";
	// String password = "duvabyq";
	String firstName = "automation";
	String lastName = "Testing";	
	String validEmail = "automation_13@gmail.com";
	String validPassword  = "123123";
	
 //Pre-condition
	@BeforeClass
	public void beforeClass() {
		//Khởi tạo trình duyệt 
	driver = new FirefoxDriver();
	
		//Chờ element được hiển thị trước khi tương tác trong vòng 30
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	
		//Phóng to trình duyệt 
	driver.manage().window().maximize();
	
	}
	 @BeforeMethod
	 public void beforeMethod() {
			driver.get("http://live.demoguru99.com/");
			driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		}
	 
	@Test
	public void TC_01_LoginwithEmailandPasswordEmpty() {
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
			Assert.assertEquals(emailErrorMsg,"This is a required field.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");
			
			
	}
	
	@Test
	public void TC_02_LoginwithEmailIvalid(){
			driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("123434234@12312.123123");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
			Assert.assertEquals(emailErrorMsg,"Please enter a valid email address. For example johndoe@domain.com.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");
			
	}
	 
	@Test
	public void TC_03_LoginwithPasswordlessthan6characters() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg,"This is a required field.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg,"Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_LoginwithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
	
		
		String PasswordErrorMsg = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//span")).getText();
		Assert.assertEquals(PasswordErrorMsg,"Invalid login or password.");
		
	} 
	
	@Test
	public void TC_05_LoginwwithEmailandPasswordValidorCorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("validEmail");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("validPassword");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Cách 1: Dùng hàm assertTrue (điều kiện) -> locator được hiển thị (isDisplayed) 
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, Automation Testing!']")).isDisplayed());
		
		
		// Cách 2: Dùng hàm assertEquals (điều kiện 1, điều kiện 2) -> getText() - actual result, expected result (text cố định)
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "My Dashboard");
		
		String myaccMsg = driver.findElement(By.xpath("//div[@ class='col-main']//h1")).getText();
		Assert.assertEquals(myaccMsg,"MY DASHBOARD");
		
		String accdashboardMsg = driver.findElement(By.xpath("//p[@class='hello']//strong")).getText();
		Assert.assertEquals(accdashboardMsg,"Hello, Automation Testing!");
		
		driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation Testing')]")).getText();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation Testing')]")).isDisplayed());



		
		// If not login in TC 5, when click to my Account will redirect to detail account page in TC 6
			driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
			driver.findElement(By.xpath("//a[@title='Log Out'] ")).click();
	}	
	
	@Test
	public void TC_06_CreatAnAccount() {
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Le");
		driver.findElement(By.xpath("//input[@id='middlename']")).sendKeys("Ngoc");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Hien");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("ngochien123@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("12345678");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("12345678");
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		String SuccessMsg = driver.findElement(By.xpath("//div[@class='my-account']//span")).getText();
		Assert.assertEquals(SuccessMsg,"Thank you for registering with Main Website Store.");
	}
 //Post - condition
	@AfterClass
	public void afterClass() {
		
	// Tắt trình duyệt 
	driver.quit();
	}
	 
	}