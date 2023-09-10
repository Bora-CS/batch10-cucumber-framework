package hui_automation.selenium.boratech_tests;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.utilities.Testkeys;

public class LoginTest {

	static WebDriver driver;

	public static void main(String[] args) {
		login();
	}

	static void login() {
		// setup
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		try {
			// home page
			driver.get("https://boratech-practice-app.onrender.com/");
			Testkeys.pause(driver, 2);

			// login page
			driver.findElement(By.xpath("//a[@href='/login'][contains(@class, 'btn')]")).click();
			Testkeys.pause(driver, 2);

			// user email and password
			String email = "hui-pretender@outlook.com";
			String password = "Hui123456";
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.pause(driver, 2);

			// check for dash board page
			boolean urlFound = driver.getCurrentUrl().endsWith("dashboard");
			Assertions.assertTrue(urlFound);
			String title = driver.findElement(By.xpath("//h1[@class]")).getText();
			Assertions.assertEquals("Dashboard", title);
			System.out.println("Test passed.");
		} catch (Exception e) {} finally {
			driver.quit();
		}

	}

}
