package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.BoraTech;

public class DeleteExperience {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";

		try {
			BoraTech.login(driver, username, password);
			BoraTech.deleteAllExperiences(driver);

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
