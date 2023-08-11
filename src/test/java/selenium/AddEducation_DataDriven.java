package selenium;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pojo.Education;
import utilities.BoraTech;

public class AddEducation_DataDriven {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";

		Education edu1 = new Education("", "Certified Test Automation Engineer", "Test Automation Engineering",
				"08/13/2020", "02/13/2021", false, "Thinking about how to automate everything everyday",
				new String[] { "School is required" });
		Education edu2 = new Education("BoraTech", "", "Test Automation Engineering", "08/13/2020", "02/13/2021", false,
				"Thinking about how to automate everything everyday", new String[] { "Degree is required" });
		Education edu3 = new Education("", "", "Test Automation Engineering", "08/13/2020", "02/13/2021", false,
				"Thinking about how to automate everything everyday",
				new String[] { "School is required", "Degree is required" });

		Education[] educations = { edu1, edu2, edu3 };

		try {
			BoraTech.login(driver, username, password);

			for (Education education : educations) {
				BoraTech.addEducation(driver, education);
				BoraTech.educationValidation(driver, education);
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
