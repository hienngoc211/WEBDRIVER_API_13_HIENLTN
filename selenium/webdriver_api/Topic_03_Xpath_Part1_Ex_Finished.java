package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class Topic_03_Xpath_Part1_Ex_Finished {
	//Khai báo một biến driver đại diện cho Selenium 
	WebDriver driver;
	// String username = "mngr231005";
	// String password = "duvabyq";
	String firstName = "Selenium";
	String lastName = "Acvanced";	
	String validEmail = "automation_13@gmail.com" +randomNumber() + "@gmail.com";
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
	 
	public void TC_01_LoginwithEmailandPasswordEmpty() {
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
			Assert.assertEquals(emailErrorMsg,"This is a required field.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");
			
			
	}
	
	public void TC_02_LoginwithEmailIvalid(){
			driver.findElement(By.xpath("//input[@title='Email Address']")).sendKeys("123434234@12312.123123");
			driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
			driver.findElement(By.xpath("//button[@id='send2']")).click();
			
			String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
			Assert.assertEquals(emailErrorMsg,"Please enter a valid email address. For example johndoe@domain.com.");
			
			String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(passwordErrorMsg,"This is a required field.");
			
	}
	 
	public void TC_03_LoginwithPasswordlessthan6characters() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg,"This is a required field.");
		
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg,"Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	public void TC_04_LoginwithPasswordIncorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
	
		
		String PasswordErrorMsg = driver.findElement(By.xpath("//div[@class='main-container col1-layout']//span")).getText();
		Assert.assertEquals(PasswordErrorMsg,"Invalid login or password.");
		
	} 
	public void TC_05_CreateNewAccount() {
		
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.'])")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOAR");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello," + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(text(),'"+ firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
		// If not login in TC 5, when click to my Account will redirect to detail account page in TC 6
		 driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		 driver.findElement(By.xpath("//a[@title='Log Out'] ")).click();
		
	}
	
	public void TC_06_LoginwwithEmailandPasswordValidorCorrect() {
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Cách 2: Dùng hàm assertEquals (điều kiện 1, điều kiện 2) -> getText() - actual result, expected result (text cố định)
		
				Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "MY DASHBOAR");
		// Cách 1: Dùng hàm assertTrue (điều kiện) -> locator được hiển thị (isDisplayed) 
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello," + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(text(),'"+ firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class ='box-content']/p[contains(.,'" + validEmail + "')]")).isDisplayed());

		
		
		
			
		
		
	}	
	

 //Post - condition
	@AfterClass
	public void afterClass() {
		
	// Tắt trình duyệt 
	driver.quit();
	}
	 public int randomNumber() {
		 Random rand = new Random();
		 int n = rand.nextInt(50);
		 return n;
	 }
	}