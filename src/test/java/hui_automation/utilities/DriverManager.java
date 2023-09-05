package hui_automation.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static WebDriver driver = null;

	private DriverManager() {
	};

	public static WebDriver getInstance() {
		if (driver == null) {
			String browserName = Configuration.get("browser");
			driver = DriverFactory.getDriver(browserName);
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
