package jira;

import java.time.Duration;
import java.util.Scanner;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import inputDataFiles.TestSuiteExcelDataTest;

public class LaunchJiraTest extends TestSuiteExcelDataTest{
	WebDriver driver;
	String username = prop.getProperty("Username");
	String password = prop.getProperty("Password");
	String Testversion = prop.getProperty("TestVersion");

	/*
	 * @BeforeClass public void userInput() { Scanner sc = new Scanner(System.in);
	 * Zukey = sc.nextLine(); System.out.println("Enter Test version :");
	 * Testversion = sc.nextLine(); System.out.println("Enter excel file path");
	 * excelFilePath = sc.nextLine(); }
	 */
	
    //Providing User credentials for mid-way authentication before proceeding to test
	@BeforeClass
	public void launchURL(){ 
		driver = new ChromeDriver();
		//driver = getBrowser(prop.getProperty("Browser"));
		driver.manage().window().maximize(); 
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.get(
				"https://issues.labcollab.net/projects/APPSEAQA?jwupdated=35854&selectedItem=com.thed.zephyr.je:zephyr-tests-page#test-cycles-tab"
				); 
		driver.findElement(By.xpath("//div[@class='idpDescription float']/span[text()='Amazon Employees/Contractors']"
				)).click();
		driver.findElement(By.id("user_name")).sendKeys(prop.getProperty("Username"));
		driver.findElement(By.id("password")).sendKeys(prop.getProperty("Password"));
		driver.findElement(By.id("verify_btn")).click();
		System.out.println("Press Yubikey for OTP");

		driver.findElement(By.xpath("//a[@id='aui-test-cycles-tab']")).click();
	}

	//Test cycles creation by providing input data through excel file using data provider annotation
	@Test (dataProvider = "ExcelData")
	public void testdata(Object CycleName, Object Testcases) {
		System.out.println(CycleName+"-"+Testcases);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		JavascriptExecutor executor = (JavascriptExecutor)driver;

		driver.findElement(By.id("create-cycle-tree-trigger")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement option = driver.findElement(By.id("cycle_version"));
		wait.until(ExpectedConditions.elementToBeClickable(option));
		option.click();
		Select options = new Select(option);
		options.selectByVisibleText(Testversion);

		driver.findElement(By.xpath("//input[@id='cycle_name']")).sendKeys((String) CycleName);
		driver.findElement(By.xpath("//input[@value='Save']")).click();

		System.out.println(CycleName + " cycle created successfully");
		WebElement versionInputField =  driver.findElement(By.xpath("//input[@id='search-tree']"));
		versionInputField.clear();
		versionInputField.sendKeys(Testversion + Keys.ENTER);
		driver.findElement(By.xpath("//a[@name='"+Testversion+"']/preceding-sibling::i")).click();

		WebElement cycleAddicon= driver.findElement(By.xpath("//div[@title='" + CycleName
				+"']/following-sibling::div/div[@class='contextMenuIcon aui-icon aui-icon-small aui-iconfont-handle-horizontal']"));
		executor.executeScript("arguments[0].click();",cycleAddicon);

		driver.findElement(By.xpath("//a[text()='Add Tests']")).click();
		driver.findElement(By.xpath("//button[text()='Via JQL Search']")).click();
		driver.findElement(By.cssSelector("#JQL-query-input")).sendKeys("filter="+Testcases); 

		WebElement searchbtn = driver.findElement(By.cssSelector("#search-jql-query"));
		executor.executeScript("arguments[0].click();",searchbtn);

		driver.findElement(By.cssSelector("#select-all-issues")).click();
		driver.findElement(By.xpath("//button[text()='Add']")).click();
		driver.findElement(By.cssSelector("#aui-dialog-close")).click();

		System.out.println("Test cases addedd successfully for "+CycleName);
		versionInputField.clear();
	}
	
	  @AfterClass 
	  public void teardown() {
		  driver.quit(); 
		  }
	 
}

// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(options));
// >>>>To find the size of the total versions found in the table
// List<WebElement> list =
// driver.findElements(By.xpath("//select[@id='cycle_version']/option"));
// System.out.println(list.size());

