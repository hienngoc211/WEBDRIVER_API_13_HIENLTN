package webdriver_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_07_Exercise_HTML_Dropdown_list_02 {
	WebDriver driver;
	Select select;
	
	By maleRadioBy = By.xpath("//input[@id='gender-male']");
	By firstnameTextbox = By.xpath("//input[@id='FirstName']");
	By lastnameTextbox = By.xpath("//input[@id='LastName']");
	
	String validEmail = "johnwick_" + randomNumber() + "@gmail.com";
	String validPassword = "123abc";
	

 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Displayed() throws Exception {
		driver.get ("https://demo.nopcommerce.com");
		driver.findElement(By.xpath("//a[text()='Register']")).click();
		driver.navigate().refresh();
		if (isElementDisplayed(maleRadioBy)) {
			clickToElement(maleRadioBy);
			Thread.sleep(2000);
		}
		if (isElementDisplayed(firstnameTextbox)) {
			sendkeyToElement(firstnameTextbox,"John");
			Thread.sleep(2000);
		if (isElementDisplayed(lastnameTextbox)) {
			sendkeyToElement(lastnameTextbox,"Wick");
			Thread.sleep(2000);
		}
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByValue("1");
			Thread.sleep(2000);
		int dateOfBirth = select.getOptions().size();
		System.out.println(" Có tất cả "  + dateOfBirth + " ngày sinh ");
		Assert.assertEquals(dateOfBirth, 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText("May");
			Thread.sleep(2000);
		int monthOfBirth = select.getOptions().size();
		System.out.println(" Có tất cả "  + monthOfBirth + " ngày sinh ");
		Assert.assertEquals(monthOfBirth, 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText("1980");
			Thread.sleep(2000);
		int yearOfBirth = select.getOptions().size();
		System.out.println(" Có tất cả " + yearOfBirth + " năm ");
		Assert.assertEquals(yearOfBirth, 112);
		
		
		driver.findElement(By.name("Email")).sendKeys("johnwick_111222@gmail.com");
			Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("validPassword");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("validPassword");

		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='My account']")).isDisplayed());

		
		}
		
	}
		
	 
	@Test
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isElementSeclected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
				return true;
			} else {
				return false;
			}
		}
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	 public void clickToElement(By by) {
		 WebElement element = driver.findElement(by);
		 element.click();
	 }
	 
	 public int randomNumber() {
		 Random rand = new Random();
		 int n = rand.nextInt(100000);
		 return n;
	 }
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}