package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverManager {

	private static ThreadLocal<WebDriver> threadLocalDriver;

	private DriverManager() {
	};

	public static WebDriver getInstance() {
		if (threadLocalDriver == null) {
			threadLocalDriver = new ThreadLocal<WebDriver>();
		}

		if (threadLocalDriver.get() == null) {
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			threadLocalDriver.set(driver);
		}

		return threadLocalDriver.get();
	}

	public static void teardown() {
		if (threadLocalDriver.get() != null) {
			threadLocalDriver.get().close();
			threadLocalDriver.get().quit();
		}
		threadLocalDriver.set(null);
	}

}
