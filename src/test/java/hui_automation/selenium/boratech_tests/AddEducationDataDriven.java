package hui_automation.selenium.boratech_tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Education;
import hui_automation.utilities.Testkeys;

public class AddEducationDataDriven {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		Education edu1 = new Education("George Mason University", "Bachelor's Degree", "Biology", "2008/01/31",
				"2010/05/15", false, "Practice the scientific study of life.", null);
		Education edu2 = new Education("BoraTech School", "Certificate of Completion", "Test Automation Engineering",
				"2023/05/07", null, true, "Web application testing for quality control.", null);
		Education[] educations = { edu1, edu2 };

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			testDriver.manage().window().maximize();
			BoraTech.login(testDriver, email, password);

			// adding education
			for (Education education : educations) {
				BoraTech.addEducation(testDriver, education);
			}
			Testkeys.pause(3);

			// delete all educations
			BoraTech.deleteAllEducations(testDriver);
			Testkeys.jsViewTop(testDriver);

			System.out.println("Test Passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
		} finally {
			Testkeys.terminate(testDriver);
			System.out.println("Test completed.");
		}
	}

}
