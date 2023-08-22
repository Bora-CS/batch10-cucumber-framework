package hui_automation.selenium.boratech_tests.premium_testing;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Education;
import hui_automation.selenium.boratech_tests.BoraTech;
import hui_automation.utilities.Testkeys;

public class AddEducation {

	public static void main(String[] args) {
		String school = "BoraTech School";
		String degree = "Certificate of Completion";
		String fieldofstudy = "Test Automation Engineering";
		String startDate = "2023/05/07";
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

		Education edu11 = new Education("", "", "", startDate, "", true, "",
				new String[] { errMsg1, errMsg2, errMsg3 });
		Education edu12 = new Education(school, "", "", "", "", true, "", new String[] { errMsg2, errMsg3, errMsg4 });

		Education[] educations = { edu1, edu2, edu3, edu4, edu5, edu6, edu7, edu8, edu9, edu10, edu11, edu12 };

		runTests(educations);
	}

	private static void runTests(Education[] eduTestData) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			testDriver.manage().window().maximize();
			BoraTech.login(testDriver, email, password);

			// testing
			int testCount = 1;
			for (Education edu : eduTestData) {
				System.out.println(String.format("Test %d =>", testCount));
				String testType = edu.isTestPositive ? "Positive" : "Negative";
				System.out.println(testType + " test:");
				runTest(testDriver, edu);
				testCount++;
			}
			Testkeys.pause(3);

			BoraTech.deleteAllEducations(testDriver);

			System.out.println("Test set completed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(testDriver);
		}

	}

	private static void runTest(WebDriver driver, Education edu) {
		try {
			BoraTech.addEducation(driver, edu);
			BoraTech.validateEducation(driver, edu);
			System.out.println("Test passed.\n");
		} catch (Exception e) {
			System.err.println("Test failed.");
			System.err.println("Reason: " + e.getMessage());
		}
	}

}
