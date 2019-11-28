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
public class Topic_07_Textbox_Dropdown_1 {
	WebDriver driver;
	Select select;
	
 	@BeforeClass
	public void beforeClass() {
 		// Khởi tạo trình duyệt 
 		
	driver = new FirefoxDriver();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	
	}
	 
	@Test
	public void TC_01_Locator() throws Exception {
		driver.get ("https://egov.danang.gov.vn/reg");
		
		// Thao tác với City dropdown
		select = new Select(driver.findElement(By.name("tinhThuongTru")));
		
		// Kiểm tra dropdown không được phép chọn 
		boolean cityDropdownStatus = select.isMultiple();
		System.out.println("City status = " + cityDropdownStatus);
		Assert.assertFalse(cityDropdownStatus);
		
		// Chọn thành phố HCM 
		select.deselectByIndex(4);
		Thread.sleep(3000);
		select.getFirstSelectedOption().getText();
		
		// Sau khi chọn thành công, làm sao để kiểm tra nó đã đúng 
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "thành phố Hồ Chí Minh");
		
		// Chọn Cà Mau
		select.selectByValue("11803");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Cà Mau");


		// Chọn Lạng Sơn 
		select.selectByVisibleText("tỉnh Lạng Sơn");
		Thread.sleep(3000);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "tỉnh Lạng Sơn");

		// Làm sao để biết trong dropdown có bao nhiêu item 
		int cityNumber = select.getOptions().size();
		System.out.println("Tất cả tỉnh thành" + cityNumber);
		Assert.assertEquals(cityNumber, 65);
		
		// In ra tất cả giá trị nằm trong dropdown -> Xem tất cả các giá trị trong dropdown này Sort đúng hay không 
		// Sort asc/decs
		List <WebElement> allOptions = 	select.getOptions();
		List <String> arrayList = new ArrayList<String>();
		for (WebElement option: allOptions) {
			arrayList.add(option.getText());
			System.out.println(option.getText());
		}

		for(String text: arrayList) {
			System.out.println(text);
		}
		
	}
	 
	@Test
	public void TC_02_() {
		
		
	}
	 
	@AfterClass
	public void afterClass() {
		driver.quit();
		
	}
	 
	}