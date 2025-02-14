package inputDataFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class UtilitiesData{
	WebDriver driver;
	public Properties prop;

	public UtilitiesData() {
		prop = new Properties();
		File propfile = new File(System.getProperty("user.dir")+"/src/main/java/com/utils/UserInput-Config.properties");

		try {
			FileInputStream FIS = new FileInputStream(propfile);
			prop.load(FIS);
			/*
			 * String excelfile = prop.getProperty("username");
			 * System.out.println(excelfile);
			 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getBrowser(String browsername) {
		if(browsername.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if(browsername.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if(browsername.equalsIgnoreCase("edge")) {
			driver=new EdgeDriver();
		}
		return driver;
	}

}
