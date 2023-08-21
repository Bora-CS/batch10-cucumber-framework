package hui_automation.selenium.boratech_tests.premium_testing;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Experience;
import hui_automation.selenium.boratech_tests.BoraTech;
import hui_automation.utilities.Testkeys;

public class AddExperience {

	public static void main(String[] args) {
		// positive test cases
		Experience exp1 = new Experience("Walmart", "Cashier", "Manassas, Virginia", "2006/06/06", "2008/11/11", false,
				"Payment collection.", null);
		Experience exp2 = new Experience("NASA", "Astronaut", "Merritt Island, Florida", "2009/01/11", "2019/08/08",
				false, "Maintain safety of the international space station and fly space shuttle.", null);
		Experience exp3 = new Experience("Self-Employed", "Pirate ", "High Sea", "2019/08/31", "", true,
				"High sea raiding.", null);

		String title = "Junior Villain";
		String company = "Wuckert Inc";
		String startDate = "2023/08/09";
		String errMsg1 = "Title is required";
		String errMsg2 = "Company is required";
		String errMsg3 = "From date is required";
		// negative test cases
		Experience exp4 = new Experience("", "", "", "", "", true, "", new String[] { errMsg1, errMsg2, errMsg3 });
		Experience exp5 = new Experience(company, "", "", startDate, "", true, "", new String[] { errMsg1 });
		Experience exp6 = new Experience("", title, "", startDate, "", true, "", new String[] { errMsg2 });
		Experience exp7 = new Experience(company, title, "", "", "", true, "", new String[] { errMsg3 });
		Experience exp8 = new Experience("", "", "", "", "", true, "", new String[] { "Something" });
		Experience exp9 = new Experience("", "", "", startDate, "", true, "", new String[] { errMsg1, errMsg2 });
		Experience exp10 = new Experience(company, "", "", "", "", true, "", new String[] { errMsg1, errMsg3 });
		Experience exp11 = new Experience("", title, "", "", "", true, "", new String[] { errMsg2, errMsg3 });

		Experience[] experiences = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, exp9, exp10, exp11 };

		runTests(experiences);
	}

	private static void runTests(Experience[] expTestData) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			testDriver.manage().window().maximize();
			BoraTech.login(testDriver, email, password);

			// testing
			int testCount = 1;
			for (Experience exp : expTestData) {
				System.out.println(String.format("Test %d =>", testCount));
				String testType = exp.isTestPositive ? "Positive" : "Negative";
				System.out.println(testType + " test:");
				runTest(testDriver, exp);
				testCount++;
			}
			Testkeys.pause(3);

			BoraTech.deleteAllExperiences(testDriver);

			System.out.println("Test set completed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(testDriver);
		}

	}

	private static void runTest(WebDriver driver, Experience exp) {
		try {
			BoraTech.addExperience(driver, exp);
			BoraTech.validateExperience(driver, exp);
			System.out.println("Test passed.\n");
		} catch (Exception e) {
			System.err.println("Test failed.");
			System.err.println("Reason: " + e.getMessage());
		}
	}

}
