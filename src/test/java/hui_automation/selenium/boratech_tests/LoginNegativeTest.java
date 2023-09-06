package hui_automation.selenium.boratech_tests;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.openqa.selenium.WebDriver;

import hui_automation.utilities.DriverFactory;
import hui_automation.utilities.Testkeys;
import pages.bora_tech.LoginPage;

public class LoginNegativeTest {

	public static void main(String[] args) {
		WebDriver driver = DriverFactory.getDriver("chrome");
		driver.manage().window().maximize();
		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login("invalid.someone@invalid.email.com", "invalid123");
			Testkeys.pause(driver, 1);
			String page = driver.getPageSource();
			File pageFile = new File("./src/test/java/hui_automation/selenium/target_html/failed_login.html");
			FileWriter fw = new FileWriter(pageFile);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(page);

			bw.close();
			System.out.println("Yay!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
