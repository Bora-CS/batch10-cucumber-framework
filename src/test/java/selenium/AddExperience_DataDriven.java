package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.BoraTech;
import pojo.Experience;

public class AddExperience_DataDriven {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";

		Experience exp1 = new Experience("Junior Cashier", "McDonald's", "Annandale, VA", "02/12/2013", "02/12/2014",
				false, "Just learning how to take orders and count some money");
		Experience exp2 = new Experience("Cashier", "Popeyes", "Fairfax, VA", "02/16/2014", "08/12/2020", false,
				"Counting money everyday");
		Experience exp3 = new Experience("Senior Cashier", "Chik-fil-a", "Fairfax, VA", "08/13/2020", "", true,
				"Bro I know I still count money, but I eat 'healthy' nowdays");

		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			BoraTech.login(driver, username, password);

			for (Experience experience : experiences) {
				BoraTech.addExperience(driver, experience);
			}

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
