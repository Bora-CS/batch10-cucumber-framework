package anthony;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import anthony_pojo.Experience;

public class experience_DataDriven {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);

		String username = "anth0ny@gmail.com";
		String password = "PaSsWoRd123!";

		Experience exp1 = new Experience("Jr Noob Automator", "Megasoft", "Burke, VA", "01/01/2000", "01/01/2002",
				false, "Sysout squares ▦");
		Experience exp2 = new Experience("Noob Developer", "Capitol None", "Springfield, VA", "02/02/2002",
				"02/02/2005", false, "Sysout rectangles ▭");
		Experience exp3 = new Experience("Sr Noob Automator", "BookFace", "Arlington, VA", "01/23/2020", "04/05/2020",
				false, "Sysout triangle △ ");
		Experience exp4 = new Experience("Sr Noob Developer", "Microhard", "Woodbridge, VA", "02/23/2023", "", true,
				"Sysout diamonds ❖ ");

		Experience[] experiences = { exp1, exp2, exp3, exp4 };

		try {
			login_exp_edu.login(driver, username, password);

			for (Experience experience : experiences) {
				login_exp_edu.addExperience(driver, experience);
			}

			deleteLoopTest.delete4EaLoop(driver);

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
