package hui_automation.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

	private static WebDriver driver = null;

	private DriverManager() {
	};

	public static WebDriver getInstance() {
		if (driver == null) {
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver = new ChromeDriver(co);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		return driver;
	}

	public static void tearDown() {
		if (driver != null)
			driver.quit();
		driver = null;
	}

}
