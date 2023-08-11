package anthony;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import anthony_pojo.Education;

public class education_DataDriven {

	public static void main(String[] args) {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--start-maximized");

		WebDriver driver = new ChromeDriver(options);

		String username = "anth0ny@gmail.com";
		String password = "PaSsWoRd123!";

		Education edu1 = new Education("ToraBech", "Bachelors", "Noob Automation Engineer", "01/01/2001", "02/02/2006",
				false, "I can Sysout squares ▦");
		Education edu2 = new Education("B0RAT3CH", "J@V@ Certificate", "Oracle Java", "03/04/2007", "12/22/2007", false,
				"I can Sysout rectangles ▭");
		Education edu3 = new Education("Mason George University", "Masters", "atrl+f4", "11/11/2009", "06/21/2010",
				false, "I can Sysout triangle △");
		Education edu4 = new Education("NVCC", "PHD", "Computer Psychology", "05/19/2020", "", true,
				"I can Sysout diamonds ❖");

		Education[] educations = { edu1, edu2, edu3, edu4 };

		try {
			login_exp_edu.login(driver, username, password);

			for (Education education : educations) {
				login_exp_edu.addEducation(driver, education);
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
