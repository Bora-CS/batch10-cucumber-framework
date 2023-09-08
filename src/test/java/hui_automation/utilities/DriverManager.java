package hui_automation.utilities;

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

	public static void reset() {
		if (driver != null)
			driver.quit();
		driver = null;
	}

}
