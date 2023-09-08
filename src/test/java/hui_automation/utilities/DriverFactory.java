package hui_automation.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {

	public static WebDriver getDriver(String browserName) {
		switch (browserName.toLowerCase()) {
		case "chrome":
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			return new ChromeDriver(co);
		case "firefox":
			return new FirefoxDriver();
		case "edge":
			return new EdgeDriver();
		default:
			return null;
		}
	}

}
