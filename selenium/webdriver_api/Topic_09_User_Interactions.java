package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_09_User_Interactions {
	WebDriver driver;
	Actions action;
	Select select;
	
 	@BeforeClass
	public void beforeClass() {
 		
 		// Capability (config browser)
 		// FirefoxProfile profile = new FirefoxProfile();
 		// profile.setPreference("dome.webnotifications.enabled", false);
 		// driver = new FirefoxDriver();
 		 
 		System.setProperty("webdriver.chrome.driver", "./libraries/chromedriver");
 		driver = new ChromeDriver();
 				
		
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	 
	@Test
	public void TC_01_Hover_Move_Mouse() {
		
		driver.get ("http://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Men']"))).perform();
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryLink' and text()='Boxers']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Boxers For Men']")).isDisplayed());
				
	}
	 
	@Test
	public void TC_02_Click_And_Hold() throws Exception {
		
		driver.get ("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		int numberSize = numbers.size();
		System.out.println("Size before click/hold = " + numberSize); // = 12
		
		
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(7)).release().perform();
		Thread.sleep(3000);
		
		List <WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[(contains(@class,'ui-selected'))]"));
		System.out.println("Size after click/hold = " + selectedNumbers.size());
		
		for(WebElement number: selectedNumbers) {
			System.out.println(number.getText());
		}

		Assert.assertEquals(selectedNumbers.size(), 8);
		
		
	}
	 
	@Test
	public void TC_03_Click_And_Hold_Random() {
		driver.get ("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		
		List <WebElement> numbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		int numberSize = numbers.size();
		System.out.println("Size before click/hold = " + numberSize);
		
		action.keyDown(Keys.CONTROL).perform();
		
		action.click(numbers.get(0))
				.click(numbers.get(2))
				.click(numbers.get(5))
				.click(numbers.get(10))
				.perform();
			
		List <WebElement> selectedNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li[(contains(@class,'ui-selected'))]"));
		System.out.println("Size after click/hold = " + selectedNumbers.size());
		
		for(WebElement number: selectedNumbers) {
			System.out.println(number.getText());
		}

		Assert.assertEquals(selectedNumbers.size(), 4);
	
	}
	@Test
	public void TC_04_Double_Click() throws Exception {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		
		
		String demoText = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		
		
		Assert.assertEquals(demoText, "Hello Automation Guys!");
		
	}
	
	
	@Test
	public void TC_05_Right_Click() {
	
			driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
						
			action.contextClick(findByXpath("//span[text()='right click me']")).perform();
			
			action.moveToElement(findByXpath("//span[text()='Quit']")).perform();
			
			Assert.assertTrue(isElementDisplayed("//li[contains(@class,'context-menu-visible') and contains (@class,'context-menu-hover')]/span[text()='Quit']"));
			
			action.click(findByXpath("//span[text()='Quit']")).perform();
			
			
	}
	
	
	@Test
	
	public void TC_06_Drag_AndDrop() throws Exception {
		
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		WebElement sourceCircle = findByXpath("//div[@id='droptarget']");
		WebElement targetCircle = findByXpath("////div[@id='draggable']");
		
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(isElementDisplayed("//div[@id='droptarget' and text()='You did great!']"));
		
		
	}
	
	
	public WebElement findByXpath(String xpathLocator) {
		return driver.findElement(By.xpath(xpathLocator));
	}
	
	public boolean isElementDisplayed(String xpathLocator) {
		return findByXpath(xpathLocator).isDisplayed();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}