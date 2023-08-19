package ardal_practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ardal_practice.pojo.Experience;

public class Data_Driven_ExperienceFlow {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		String userName = "ardal002713@gmail.com";
		String password = "ardal123";

		String error1 = "Title is required";
		String error2 = "Company is required";
		String error3 = "From date is required";

		Experience exp1 = new Experience("Apple", "L1 Devop", "Vancouver", "08/24/2013", "08/25/2014", false,
				"Living the dream", null);
		Experience exp2 = new Experience("Google", "L2 Devop", "Vancouver", "08/24/2015", "", true, "Living the dream",
				null);
		Experience exp3 = new Experience("Microsoft", "", "Vancouver", "08/24/2016", "08/25/2017", false,
				"Living the dream", new String[] { error2 });
		Experience exp4 = new Experience("", "L1 Devop", "Vancouver", "08/24/2018", "08/25/2019", false,
				"Living the dream", new String[] { error1 });
		Experience exp5 = new Experience("Apple", "L1 Devop", "Vancouver", "", "", false, "Living the dream",
				new String[] { error3 });
		Experience exp6 = new Experience("", "", "Vancouver", "08/24/2013", "", true, "Living the dream",
				new String[] { error1, error2 });
		Experience exp7 = new Experience("Apple", "", "Vancouver", "", "08/25/2021", false, "Living the dream",
				new String[] { error2, error3 });
		Experience exp8 = new Experience("", "", "Vancouver", "", "08/25/2022", false, "Living the dream",
				new String[] { error1, error2, error3 });

		Experience[] experiences = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8 };

		try {
			BoraTechArdal.login(driver, userName, password);

			for (Experience experience : experiences) {
				BoraTechArdal.addExperience(driver, experience);
				BoraTechArdal.experienceValidation(driver, experience);
			}

			BoraTechArdal.deleteAllExperiences(driver);
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
