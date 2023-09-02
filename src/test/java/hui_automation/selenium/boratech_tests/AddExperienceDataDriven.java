package hui_automation.selenium.boratech_tests;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.pojos.Experience;
import hui_automation.utilities.Testkeys;

public class AddExperienceDataDriven {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";

		Experience exp1 = new Experience("Walmart", "Cashier", "Manassas, Virginia", "06/06/2006", "11/11/2008", false,
				"Payment collection.", null);
		Experience exp2 = new Experience("NASA", "Astronaut", "Merritt Island, Florida", "01/11/2009", "08/08/2019",
				false, "Maintain safety of the international space station and fly space shuttle.", null);
		Experience exp3 = new Experience("Self-employed", "Pirate", "High Sea", "2019/08/31", null, true,
				"High sea raiding.", null);
		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();
			BoraTech.login(driver, email, password);
			Testkeys.pause(driver, 3);

			// adding experience
			for (Experience experience : experiences) {
				BoraTech.addExperience(driver, experience);
			}
			Testkeys.pause(driver, 3);

			// delete all experiences
			BoraTech.deleteAllExperiences(driver);
			Testkeys.pause(driver, 3);

			System.out.println("Test Passed.");
		} catch (Exception e) {
			System.out.println("Test failed.");
		} finally {
			Testkeys.terminate(driver);
			System.out.println("Test completed.");
		}
	}

}
