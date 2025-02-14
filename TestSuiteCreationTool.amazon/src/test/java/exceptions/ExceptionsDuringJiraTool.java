package exceptions;

import java.io.IOException;
import java.time.Duration;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import inputDataFiles.TestSuiteExcelDataTest;

public class ExceptionsDuringJiraTool {


	//java.lang.IllegalMonitorStateException
	//Error
	/***[Utils] [ERROR] [Error] org.testng.TestNGException: 
		Cannot inject @Test annotated Method [testdata] with [class java.lang.String, class java.lang.String].**/
	//Solution: Provide dataprovider name for input data in @Test method
	//java.lang.NegativeArraySizeException: -1

	//org.openqa.selenium.ElementClickInterceptedException
	/**</div> is not clickable at point (402, 392). Other element would receive the click:
	 *  <div aria-hidden="true" class="aui-blanket" tabindex="0">*/

	//InvalidPathName exception
	/** check the path name and replace single back slash \ with double back slash \\ for correct path**/

	//NegativeArrayException -1
	/**check whether the rows and columns are initialized properly within accessible method**
	 * Make sure the row & col variable is initialized inside method**/

}
//driver.findElement(By.xpath("//div[@title='"+Testversion+"']/following-sibling::div/div")).click();
		/*
		 * Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
		 * .withTimeout(Duration.ofSeconds(30)) .pollingEvery(Duration.ofSeconds(1))
		 * .ignoring(NoSuchElementException.class);
		 * 
		 * WebElement createCyclebtn1 = wait.until(new Function<WebDriver, WebElement>()
		 * { public WebElement apply(WebDriver driver) { return
		 * driver.findElement(By.xpath("//a[text()='Create Cycle']")); } });
		 * createCyclebtn1.click();
		 */
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Create Cycle']"))).click();
		/*
		 * Actions curser = new Actions(driver); WebElement createCyclebtn =
		 * driver.findElement(By.xpath("//a[text()='Create Cycle']"));
		 * curser.click(createCyclebtn).perform();
		 */
		//executor.executeScript("arguments[0].click();",createCyclebtn);
		//driver.findElement(By.xpath("//a[text()='Create Cycle']")).click();
