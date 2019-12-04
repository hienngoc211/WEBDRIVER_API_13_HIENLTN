package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_08_Button_RadioButton_Checkbox_Alert {
	WebDriver driver;
	Select select; 
	Actions action;
	JavascriptExecutor javascript;
	Alert alert;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	
	javascript = (JavascriptExecutor) driver;
	
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Locator() {
		driver.get ("https://automationfc.github.io/basic-form/index.html");
		
		// Button
		elementEnabled("//button[@id='button-disabled']");
		elementSelectedStatus("//button[@id='button-disabled']");
		
		// Checkbox
		elementEnabled("//input[@id='development']");
		elementSelectedStatus("//input[@id='development']");
		
		// Radio button
		elementEnabled("//input[@id='under_18']");
		elementSelectedStatus("//input[@id='under_18']");
		
	}
	 
	@Test
	public void TC_02_ClickbyJS() throws Exception {
		driver.get ("https://demo.nopcommerce.com/");
		
		// driver.findElement(By.xpath("//a[text()='Desktops ']")).click();
		javascript.executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[text()='Desktops ']")));
		
		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Desktops']")).isDisplayed());
	}
	
	
	@Test
	public void TC_03_Checkbox() throws Exception {
		// Custom Radio/ Checkbox (Bị ẩn user không nhìn thấy được: input)
		 driver.get("https://demos.telerik.com/kendo-ui/styling/checkboxes");
		
		String checkboxInput = "//label[text()='Dual-zone air conditioning']/preceding-sibling::input";
		// String checkboxLabel = "//label[text()='Dual-zone air conditioning']";
		
		// Pass qua hàm isSelected được nhưng không click được
		// driver.findElement(By.xpath(checkboxInput)).click();
		clickByJS(checkboxInput);
		
		// Click được nhưng không pass qua hàm isSelected được
		// driver.findElement(By.xpath(checkboxLabel)).click();
		Thread.sleep(3000);
		
		elementSelectedStatus(checkboxInput);
		
		Assert.assertTrue(isElementSelected(checkboxInput));
		
		clickByJS(checkboxInput);
		
		// driver.findElement(By.xpath(checkboxLabel)).click();
		Thread.sleep(3000);
		
		elementSelectedStatus(checkboxInput);
		
		Assert.assertFalse(isElementSelected(checkboxInput));

		
	}
	
	@Test
	public void TC_04_CheckAndUncheckToCheckbox() throws Exception {
		
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		
		String newsletterCheckbox = "//input[@id='Newsletter']";
		
		// Click vao checkbox -> checked
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertFalse(isElementSelected(newsletterCheckbox));
		Thread.sleep(2000);

		// Checked
		driver.findElement(By.xpath(newsletterCheckbox)).click();
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		Thread.sleep(2000);
		
		// Checked
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));

		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
		
		checkToCheckbox(newsletterCheckbox);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));

		
		// Uncheck
		uncheckToCheckbox(newsletterCheckbox);
		Assert.assertFalse(isElementSelected(newsletterCheckbox));
		
		uncheckToCheckbox(newsletterCheckbox);
		Assert.assertFalse(isElementSelected(newsletterCheckbox));
		
		uncheckToCheckbox(newsletterCheckbox);
		Assert.assertFalse(isElementSelected(newsletterCheckbox));

	}
	
	@Test
	public void TC_05_HandleAlert() throws Exception {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String resultMessage = "//p[@id='result']"; 
		String fullName = "Automation Tester";
		
		// Accept Alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		Thread.sleep(2000);
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked an alert successfully " );

		// Cancel Alert
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: Cancel" );

		// SENDKEY TO ALERTS
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		alert = driver.switchTo().alert();
		alert.sendKeys(fullName);
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.accept();
		
		Assert.assertEquals(driver.findElement(By.xpath(resultMessage)).getText(), "You clicked: " + fullName);



		//Switch qua elert
		// driver.switchTo().alert();
		
		// Accept Aler
		// alert.accept();
		
		// Cancel Alert
		// alert.dismiss();
		
		// Get message text in Alert
		// alert.getText();
		
		// Sendkeys to Alert
		// alert.sendKeys("");
		
		
	}
	public void  clickByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		javascript.executeScript("arguments[0].click();", element);
		
	}
	
	
 	public void elementEnabled(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element is enabled");
		} else {
			System.out.println("Element is disabled");
		}
	}
	
	public void elementSelectedStatus(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element is selected");
		} else {
			System.out.println("Element is unselected");
		}
	}
	
	public boolean isElementSelected(String locator) {

		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void checkToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
		
	}
	
	public void uncheckToCheckbox(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}