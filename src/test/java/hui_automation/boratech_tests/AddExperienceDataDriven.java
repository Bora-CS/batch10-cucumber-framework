package hui_automation.boratech_tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import hui_automation.Testkeys;
import hui_automation.pojo.Experience;

public class AddExperienceDataDriven {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		Experience exp1 = new Experience("Walmart", "Cashier", "Manassas, Virginia", "2006/06/06", "2008/11/11", false,
				"Payment collection.");
		Experience exp2 = new Experience("NASA ", "Astronaut", "Merritt Island, Florida", "2009/01/11", "2019/08/08",
				false, "Maintain safety of the international space station and fly space shuttle.");
		Experience exp3 = new Experience("Self-employed", "Pirate", "High Sea", "2019/08/31", "", true,
				"High sea raiding.");
		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			testDriver.manage().window().maximize();
			BoraTech.login(testDriver, email, password);

			// adding experience
			for (Experience experience : experiences) {
				BoraTech.addExperience(testDriver, experience);
			}
			Testkeys.pause(3);

			// delete all experiences
			BoraTech.deleteAllExperiences(testDriver);
			Testkeys.jsViewTop(testDriver);

			System.out.println("Test Passed.");
			Testkeys.pause(3);
		} catch (Exception e) {
			System.out.println("Test failed.");
		} finally {
			Testkeys.terminate(testDriver);
			System.out.println("Test completed.");
		}
	}

}
