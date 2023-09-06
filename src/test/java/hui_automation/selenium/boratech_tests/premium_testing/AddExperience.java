package hui_automation.selenium.boratech_tests.premium_testing;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.pojos.Experience;
import hui_automation.utilities.BoraTech;
import hui_automation.utilities.Testkeys;
import pages.bora_tech.AddExperiencePage;

public class AddExperience {

	public static void main(String[] args) {
		// positive test cases
		Experience exp1 = new Experience("Walmart", "Cashier", "Manassas, Virginia", "06/06/2006", "11/11/2008", false,
				"Payment collection.", null);

		String title = "Junior Villain";
		String company = "Wuckert Inc";
		String startDate = "08/09/2017";
		String errMsg1 = "Title is required";
		String errMsg2 = "Company is required";
		String errMsg3 = "From date is required";
		// negative test cases
		Experience exp2 = new Experience("", "", "", "", "", true, "", new String[] { errMsg1, errMsg2, errMsg3 });
		Experience exp3 = new Experience(company, "", "", startDate, "", true, "", new String[] { errMsg1 });
		Experience exp4 = new Experience("", title, "", startDate, "", true, "", new String[] { errMsg2 });
		Experience exp5 = new Experience(company, title, "", "", "", true, "", new String[] { errMsg3 });
		Experience exp6 = new Experience("", "", "", startDate, "", true, "", new String[] { errMsg1, errMsg2 });
		Experience exp7 = new Experience(company, "", "", "", "", true, "", new String[] { errMsg1, errMsg3 });
		Experience exp8 = new Experience("", title, "", "", "", true, "", new String[] { errMsg2, errMsg3 });

		Experience[] experiences = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8, };

		runTests(experiences);
	}

	private static void runTests(Experience[] expTestData) {
		WebDriver driver = new ChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			BoraTech.login(driver, email, password);

			// testing
			int testCount = 1;
			for (Experience exp : expTestData) {
				System.out.println(String.format("Test %d =>", testCount));
				String testType = exp.isTestPositive ? "Positive" : "Negative";
				System.out.println(testType + " test:");
				runTest(driver, exp);
				testCount++;
			}
			Testkeys.pause(driver, 3);

			BoraTech.deleteAllExperiences(driver);

			System.out.println("Test set completed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
		}

	}

	private static void runTest(WebDriver driver, Experience exp) {
		try {
			BoraTech.addExperience(driver, exp);
			AddExperiencePage.submitButton(driver).click();
			Testkeys.jsViewTop(driver);
			BoraTech.validateExperience(driver, exp);
			System.out.println("Test passed.\n");
		} catch (Exception e) {
			System.out.println("Test failed.");
			System.out.println("Reason: " + e.getMessage());
		}
	}

}
