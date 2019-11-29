package webdriver_api;

import java.util.ArrayList;
import java.util.List;
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
public class Topic_07_Dropdown_02 {
	WebDriver driver;
	Select  select;
	
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
		
		select = new Select(driver.findElement(By.name("user_job1")));
		Assert.assertFalse(select.isMultiple());
		
		select.selectByVisibleText("Mobile Testing");
		Thread.sleep(2000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Mobile Testing");
		
		select.selectByValue("manual");
		Thread.sleep(2000);

		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		select.deselectByIndex(9);
		Thread.sleep(2000);
		
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		Assert.assertEquals(select.getOptions().size(), 10);
		
		select = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(select.isMultiple());
		
		select.deselectByVisibleText("Manual");
		Thread.sleep(2000);
		select.deselectByVisibleText("Mobile");
		Thread.sleep(2000);
		select.deselectByVisibleText("Security");
		Thread.sleep(2000);
		select.deselectByVisibleText("Intergration");
		Thread.sleep(2000);


		List <WebElement> optionSelected = select.getAllSelectedOptions();
		Assert.assertEquals(optionSelected.size(), 4);
		
		List <String> arraySelected = new ArrayList<String>();
		for(WebElement select: optionSelected ) {
			arraySelected.add(select.getText());
			
		}
		
		Assert.assertTrue(arraySelected.contains("Manual"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Security"));
		Assert.assertTrue(arraySelected.contains("Intergration"));
		
		select.deselectAll();
		Thread.sleep(2000);
		
		List <WebElement> optonUnselected = select.getAllSelectedOptions();
		Assert.assertEquals(optonUnselected.size(), 0);

	}
	 
	@Test
	public void TC_02_() {
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}