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
public class Topic_06_Element_02_Exercises {
	WebDriver driver;
	By emailTextboxBy = By.xpath("//input[@id='mail']");
	By passwordTextboxBy = By.xpath("//input[@id='password']");
	By age18RadioBy = By.xpath("//input[@id='under_18']");
	By developmentCheckbox = By.xpath("//input[@id='development']");
	By educationTextAreaBy = By.xpath("//textarea[@id='edu']");

	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Displayed() {
		driver.get ("https://automationfc.github.io/basic-form/index.html");
		if (isElementDisplayed(emailTextboxBy)) {
			sendkeyToElement(emailTextboxBy,"Automation Testing");
		}
		if (isElementDisplayed(age18RadioBy)) {
			clickToElement(age18RadioBy) ;
		}
		if (isElementDisplayed(educationTextAreaBy)) {
			sendkeyToElement(educationTextAreaBy,"Automation Testing");
		}
		
	}
	 
	@Test
	public void TC_02_Enabled_Disabled() {
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(age18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));

		
	}
		
		@Test
		public void TC_03_Selected() {
			// Chưa click chọn nên 
			driver.navigate().refresh();
			Assert.assertFalse(isElementSeclected(age18RadioBy));
			Assert.assertFalse(isElementSeclected(developmentCheckbox));
	
			//Click chọn 
			clickToElement(age18RadioBy);
			clickToElement(developmentCheckbox);
			
			// Selected
			Assert.assertTrue(isElementSeclected(age18RadioBy));
			Assert.assertTrue(isElementSeclected(developmentCheckbox));
			
			//Click chọn lần nữa 
			clickToElement(developmentCheckbox);
			
			// Radio - > Selected
			Assert.assertTrue(isElementSeclected(age18RadioBy));

			// Checkbox - unselected/Deselected 
			Assert.assertTrue(isElementSeclected(age18RadioBy));
			Assert.assertFalse(isElementSeclected(developmentCheckbox));
	
}
	public boolean isElementDisplayed(By by)	{
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
			return false;
		}
	}
	public boolean isElementSeclected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is unselected");
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
		
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}