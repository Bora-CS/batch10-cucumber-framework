package krystal;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.BoraTech;

public class Delete_Educations_Krystal {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String username = "krystal.lee.om@gmail.com";
		String password = "123456";

		try {
			BoraTech_Krystal.login(driver, username, password);
			BoraTech_Krystal.deleteAllEducations(driver);

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
