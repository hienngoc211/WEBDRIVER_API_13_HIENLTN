package webdriver_api;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Link;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_12_JS_Executor {
	WebDriver driver;
	
	WebElement element;

	JavascriptExecutor jsExecutor ;
	String username = "mngr238966";
	String password = "emYmEqe";
	

	//Input in New Customer (user)/Output (server) data
		String customerName = "Micheal Jackson";
		String gender = "male";
		String dateOfBirth = "1980-06-06";
		String address = "255 PO Hamlet";
		String city = "New York";
		String state = "California";
		String pin = "777999";
		String phone = "0905777999";
		String email = "jackson0606" + randomNumber() + "@gmail.com";
		
		// Input in Edit Customer
		
		String editAddress = "255 PO Boxing";
		String editCity = "New Jersey";
		String editState = "Stock";
		String editPin = "888666";
		String editPhone = "0913888999";
		String editEmail = "jackson789" + randomNumber() + "@hotmail.com";
		
		
		// Locator for new/edit customer form
		
		By nameTextbox = By.name("name");
		By genderRadio = By.xpath("//input[@value='m']");
		By genderTextbox =  By.name("gender");
		By dobTextbox = By.name("dob");
		By addressTextArea = By.name("addr");
		By cityTextbox = By.name("city");
		By stateTextbox = By.name("state");
		By pinTextbox = By.name("pinno");
		By phoneTextbox = By.name("telephoneno");
		By emailTextbox = By.name("emailid");
		By passwordTextbox = By.name("password");
		By submitButton = By.name("sub");
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
 		System.setProperty("webdriver.chrome.driver", "./libraries/chromedriver");
 		driver = new ChromeDriver();
 		
 		 jsExecutor = (JavascriptExecutor) driver;

		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
 	}
	
	
	 
//	@Test
	public void TC_01_JS() throws Exception {
		navigateToUrlByJS("http://live.demoguru99.com/");
		
		String liveDomain = (String) executeForBrowser(" return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");
		
		String liveUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveUrl, "http://live.demoguru99.com/");
		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button");
		
		String pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));
		
		clickToElementByJS("//a[text()='Customer Service']");
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");
		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(2000);
		

		 pageInnerText = (String) executeForBrowser("return document.documentElement.innerText");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
	
		navigateToUrlByJS("http://live.demoguru99.com/");
		String demoDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(demoDomain, "live.demoguru99.com");
	}
	 
//	@Test
	public void TC_02_Remove_Attribute() {
		
		driver.get("http://demo.guru99.com/v4");
		
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();
		
		String homePageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homePageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='heading3']/td[text()='Manger Id : " + username + "']")).isDisplayed());
		
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		// Input data to new customer form
		
		driver.findElement(nameTextbox).sendKeys(customerName);
		driver.findElement(genderRadio).click();
		
		// Remove attribute type ='date' (Date of birth)
		
		removeAttributeInDOM("//input[@id='dob']","type");
		driver.findElement(dobTextbox).sendKeys(dateOfBirth);

		driver.findElement(addressTextArea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(phoneTextbox).sendKeys(phone);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passwordTextbox).sendKeys(password);
		driver.findElement(submitButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());
		
		// Verify output data = input data 
		
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText());
		Assert.assertEquals(phone, driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText());
			 
	}
	 
	@Test
	public void TC_03_Create_Account() { 
		
		driver.get("http://live.guru99.com/");
		
		// Trick (Invisible)
		 clickToElementByJS("//div[@id='header-account']//a[text()='My Account']");
		 
		 clickToElementByJS("//span[text()='Create an Account']");
		 
		 sendkeyToElementByJS("//input[@id='firstname']","Automation" );
		 sendkeyToElementByJS("//input[@id='lastname']","FC" );
		 sendkeyToElementByJS("//input[@id='email_address']", "automationfc" + randomNumber() + "@gmail.com" );
		 sendkeyToElementByJS("//input[@id='password']","123456" );
		 sendkeyToElementByJS("//input[@id='confirmation']","123456" );
		 clickToElementByJS("//button[@title='Register']");
		 
		 String innerText = (String) (executeForBrowser("return document.documentElement.innerText"));
		 
		 Assert.assertTrue(innerText.contains("Thank you for registering with Main Website Store."));
		 
		 // Trick (invisible)
		 clickToElementByJS("//div[@id='header-account']//a[text()='Log Out']");
		 
		 Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		 
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	
	// Browser
		public Object executeForBrowser(String javaSript) {
			return jsExecutor.executeScript(javaSript);
		}

		public boolean verifyTextInInnerText(String textExpected) {
			String textActual = (String) jsExecutor.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
			System.out.println("Text actual = " + textActual);
			return textActual.equals(textExpected);
		}

		public void scrollToBottomPage() {
			jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}

		public void navigateToUrlByJS(String url) {
			jsExecutor.executeScript("window.location = '" + url + "'");
		}

		// Element
		public void highlightElement(String locator) {
			element = driver.findElement(By.xpath(locator));
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 5px solid red; border-style: dashed;");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);

		}

		public void clickToElementByJS(String locator) {
			element = driver.findElement(By.xpath(locator));

			jsExecutor.executeScript("arguments[0].click();", element);
		}

		public void scrollToElement(String locator) {
			element = driver.findElement(By.xpath(locator));

			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
		}

		public void sendkeyToElementByJS(String locator, String value) {
			element = driver.findElement(By.xpath(locator));

			jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		}

		public void removeAttributeInDOM(String locator, String attributeRemove) {
			element = driver.findElement(By.xpath(locator));
			jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
		}
		public int randomNumber() {
			Random rand = new Random();
			return rand.nextInt(100000);
		}
		
	}