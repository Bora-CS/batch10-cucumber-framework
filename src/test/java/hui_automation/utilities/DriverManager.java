package hui_automation.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> threadLocalDriver;

	private DriverManager() {
	};

	public static WebDriver getInstance() {
		if (threadLocalDriver == null)
			threadLocalDriver = new ThreadLocal<>();
		if (threadLocalDriver.get() == null) {
			String browserName = Configuration.get("browser");
			WebDriver driver = DriverFactory.getDriver(browserName);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			threadLocalDriver.set(driver);
		}
		return threadLocalDriver.get();
	}

	public static void reset() {
		if (threadLocalDriver.get() != null)
			threadLocalDriver.get().quit();
		threadLocalDriver.set(null);
	}

}
