package hui_automation.selenium.boratech_tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.pojos.Education;
import hui_automation.utilities.BoraTech;
import hui_automation.utilities.Testkeys;

public class AddEducationDataDriven {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		Education edu1 = new Education("George Mason University", "Bachelor's Degree", "Biology", "2008/01/31",
				"05/15/2010", false, "Practice the scientific study of life.", null);
		Education edu2 = new Education("BoraTech School", "Certificate of Test Automation Engineering",
				"Test Automation Engineering", "05/07/2023", null, true, "Web application testing for quality control.",
				null);
		Education[] educations = { edu1, edu2 };

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			BoraTech.login(driver, email, password);

			// adding education
			for (Education education : educations) {
				BoraTech.addEducation(driver, education);
			}
			Testkeys.pause(driver, 3);

			// delete all educations
			BoraTech.deleteAllEducations(driver);
			Testkeys.jsViewTop(driver);

			System.out.println("Test Passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
		} finally {
			Testkeys.terminate(driver);
			System.out.println("Test completed.");
		}
	}

}
