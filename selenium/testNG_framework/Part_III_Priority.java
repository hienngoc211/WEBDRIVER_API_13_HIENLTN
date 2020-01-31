package testNG_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Part_III_Priority {

	
  @Test(description = "Create a new customer in Bank system")
  public void Priority_01_Create_New_Customer() {
	  System.out.println("Run test 01");
  }
  @Test(description = "Edit Customer Function")
  public void Priority_02_Edit_Customer() {
	  System.out.println("Run test 02");
  }
  @Test()
  public void Priority_03_Create_New_Account() {
	  System.out.println("Run test 03");
  }
  @Test()
  public void Priority_04_Edit_Account() {
	  System.out.println("Run test 04");
  }
  
  @Test()
  public void Priority_05_Delete_Account() {
	  System.out.println("Run test 05");
  }
  @Test()
  public void Priority_06_Delete_Customer() {
	  System.out.println("Run test 06");
  }
  
  
}
  

