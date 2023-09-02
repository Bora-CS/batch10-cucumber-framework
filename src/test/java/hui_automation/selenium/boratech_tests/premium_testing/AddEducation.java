package hui_automation.selenium.boratech_tests.premium_testing;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.pojos.Education;
import hui_automation.selenium.boratech_tests.BoraTech;
import hui_automation.utilities.Testkeys;

public class AddEducation {

	public static void main(String[] args) {
		String school = "BoraTech School";
		String degree = "Certificate of Test Automation Engineering";
		String fieldofstudy = "Test Automation Engineering";
		String startDate = "05/07/2023";
		// positive test cases
		Education edu1 = new Education(school, degree, fieldofstudy, startDate, "", true,
				"Web application testing for quality control.", null);

		String errMsg1 = "School is required";
		String errMsg2 = "Degree is required";
		String errMsg3 = "Field of study is required";
		String errMsg4 = "From date is required";
		// negative test cases
		Education edu2 = new Education("", "", "", "", "", true, "",
				new String[] { errMsg1, errMsg2, errMsg3, errMsg4 });
		Education edu3 = new Education("", degree, fieldofstudy, startDate, "", true, "", new String[] { errMsg1 });
		Education edu4 = new Education(school, "", fieldofstudy, startDate, "", true, "", new String[] { errMsg2 });
		Education edu5 = new Education(school, degree, "", startDate, "", true, "", new String[] { errMsg3 });
		Education edu6 = new Education(school, degree, fieldofstudy, "", "", true, "", new String[] { errMsg4 });

		Education edu7 = new Education("", "", fieldofstudy, startDate, "", true, "",
				new String[] { errMsg1, errMsg2 });
		Education edu8 = new Education(school, "", "", startDate, "", true, "", new String[] { errMsg2, errMsg3 });
		Education edu9 = new Education(school, degree, "", "", "", true, "", new String[] { errMsg3, errMsg4 });
		Education edu10 = new Education("", degree, fieldofstudy, "", "", true, "", new String[] { errMsg1, errMsg4 });

		Education edu11 = new Education(school, "", "", "", "", true, "", new String[] { errMsg2, errMsg3, errMsg4 });
		Education edu12 = new Education("", degree, "", "", "", true, "", new String[] { errMsg1, errMsg3, errMsg4 });
		Education edu13 = new Education("", "", fieldofstudy, "", "", true, "",
				new String[] { errMsg1, errMsg2, errMsg4 });
		Education edu14 = new Education("", "", "", startDate, "", true, "",
				new String[] { errMsg1, errMsg2, errMsg3 });

		Education[] educations = { edu1, edu2, edu3, edu4, edu5, edu6, edu7, edu8, edu9, edu10, edu11, edu12, edu13,
				edu14 };

		runTests(educations);
	}

	private static void runTests(Education[] eduTestData) {
		WebDriver driver = new ChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			BoraTech.login(driver, email, password);

			// testing
			int testCount = 1;
			for (Education edu : eduTestData) {
				System.out.println(String.format("Test %d =>", testCount));
				String testType = edu.isTestPositive ? "Positive" : "Negative";
				System.out.println(testType + " test:");
				runTest(driver, edu);
				testCount++;
			}
			Testkeys.pause(driver, 3);

			BoraTech.deleteAllEducations(driver);

			System.out.println("Test set completed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
		}

	}

	private static void runTest(WebDriver driver, Education edu) {
		try {
			BoraTech.addEducation(driver, edu);
			BoraTech.validateEducation(driver, edu);
			System.out.println("Test passed.\n");
		} catch (Exception e) {
			System.out.println("Test failed.");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
