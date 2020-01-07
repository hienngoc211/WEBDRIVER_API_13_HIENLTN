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

public class Topic_15_Wait_FindElement_Part2 {
	WebDriver driver;
	List<WebElement> elements;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
//	@Test
	public void TC_01_Find_Element() {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Case 01 - Không tìm thấy element nào hết 
		
		driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("123456"); 
		// Luôn tìm element mà chưa hết timeout - element nó xuất hiện thì vẫn 
		// Trước khi đánh fail: luôn tìm element theo chu kỳ là 0.5s tìm lại 1 lần cho đến hết time out của implicit
		// Kết quả là fail và throw ra exception: No such element
		
		
		// Case 02 - Tìm thấy duy nhất 1 element (node)
		
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@gmail.com");
		
		
		// Case 03 - Tìm thấy nhiều hơn 1 elements -> thao tác với element đầu tiên 
		
		driver.findElement(By.xpath("//button[@type=submit']")).click();
	}
	 
	@Test
	public void TC_02_Find_Elements() {
		driver.get ("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Case 01 - Không tìm thấy element nào hết 
		elements = driver.findElements(By.xpath("//input[@id='id_order']"));
		// Nếu như đang còn tìm element mà chưa hết timeout - element nó xuất hiện thì vẫn pass
		// Luôn tìm element theo chu kì là 0.5s tìm lại 1 lần cho đến hết timeout của implicit
		// Kết quả: không đánh fail testcase và trả về 1 empty list 
		System.out.println("Size of list = " + elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(),0);
		
		
		
		// Case 02 - Tìm thấy duy nhất 1 element (node)
		elements = driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size of list = " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(),1);
		elements.get(0).sendKeys("abc@gmail.com");
		// |1|2|3 -> element
		// |0|1|2 - list
		
		
		// Case 03 - Tìm thấy nhiều hơn 1 elements -> thao tác với element đầu tiên 
		elements = driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Size of list = " + elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(),4);
		
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}