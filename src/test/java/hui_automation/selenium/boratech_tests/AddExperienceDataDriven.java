package hui_automation.selenium.boratech_tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import hui_automation.pojos.Experience;
import hui_automation.utilities.Testkeys;

public class AddExperienceDataDriven {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		Experience exp1 = new Experience("Walmart", "Cashier", "Manassas, Virginia", "2006/06/06", "2008/11/11", false,
				"Payment collection.", null);
		Experience exp2 = new Experience("NASA", "Astronaut", "Merritt Island, Florida", "2009/01/11", "2019/08/08",
				false, "Maintain safety of the international space station and fly space shuttle.", null);
		Experience exp3 = new Experience("Self-employed", "Pirate", "High Sea", "2019/08/31", null, true,
				"High sea raiding.", null);
		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			testDriver.manage().window().maximize();
			BoraTech.login(testDriver, email, password);
			Testkeys.pause(3);

			// adding experience
			for (Experience experience : experiences) {
				BoraTech.addExperience(testDriver, experience);
			}
			Testkeys.pause(6);

			// delete all experiences
			BoraTech.deleteAllExperiences(testDriver);
			Testkeys.pause(3);

			System.out.println("Test Passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
		} finally {
			Testkeys.terminate(testDriver);
			System.out.println("Test completed.");
		}
	}

}
