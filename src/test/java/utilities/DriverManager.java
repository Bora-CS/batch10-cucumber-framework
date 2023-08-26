package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	private static WebDriver driver = null;

	private DriverManager() {
	};

	public static WebDriver getInstance() {
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		return driver;
	}

	public static void teardown() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		driver = null;
	}

}
