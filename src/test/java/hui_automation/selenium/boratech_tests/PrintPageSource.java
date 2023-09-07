package hui_automation.selenium.boratech_tests;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hui_automation.pages.bora_tech.LoginPage;
import hui_automation.utilities.DriverFactory;
import hui_automation.utilities.Testkeys;

public class PrintPageSource {

	public static void main(String[] args) {
		printToFile(getPageSource());
	}

	private static String getPageSource() {
		WebDriver driver = DriverFactory.getDriver("chrome");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		String page = null;
		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			LoginPage loginPage = new LoginPage(driver);
			loginPage.login("hui-pretender@outlook.com", "Hui123456");
			Testkeys.waitUtilURL_Contains(driver, "dashboard", 5);
			driver.findElement(By.xpath(
					"//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr//button[@class='btn btn-danger']"))
					.click();
			Testkeys.pause(driver, 1);
			page = driver.getPageSource();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
		return page;
	}

	private static void printToFile(String page) {
		try {
			File pageFile = new File("./src/test/resources/html_to_examine/remove_experience.html");
			FileWriter fw = new FileWriter(pageFile);
			BufferedWriter bw = new BufferedWriter(fw);

			bw.write(page);

			bw.close();
			System.out.println("Yay!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
