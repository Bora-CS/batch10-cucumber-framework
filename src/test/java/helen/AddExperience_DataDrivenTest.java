package helen;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helen.pojo.Experience;
import helen.utilities.BoraTech;

public class AddExperience_DataDrivenTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String username = "helenhjahn@gmail.com";
		String password = "06102021";

		// exp1 happy path
		Experience exp1 = new Experience("Test Automation Engineer", "Bora Tech", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", null);
		//exp2 sad path - omit jobTitle
		Experience exp2 = new Experience("", "Bora Tech", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Title is required"});
		//exp3 sad path - omit company
		Experience exp3 = new Experience("Test Automation Engineer", "", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Company is required"});
		//exp4 sad path - omit from date
		Experience exp4 = new Experience("Test Automation Engineer", "Bora Tech", "Annandale,VA", "", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"From date is required"});
		//exp5 sad path - omit jobTitle and company
		Experience exp5 = new Experience("", "", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Title is required", "Company is required"});
		//exp6 sad path - omit jobTitle and from date
		Experience exp6 = new Experience("", "Bora Tech", "Annandale,VA", "", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Title is required", "From date is required"});
		//exp7 sad path - omit company and from date
		Experience exp7 = new Experience("Test Automation Engineer", "", "Annandale,VA", "", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Company is required", "From date is required"});
		//exp8 sad path - omit jobTitle, company and from date
		Experience exp8 = new Experience("", "", "Annandale,VA", "", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", new String[] {"Title is required", "Company is required", "From date is required"});
		
		Experience[] experiences = { exp1, exp2, exp3, exp4, exp5, exp6, exp7, exp8};

		try {
			BoraTech.login(driver, username, password);
			for (Experience exp : experiences) {
				helen.utilities.BoraTech.addExperience(driver, exp);
				helen.utilities.BoraTech.experienceValidation(driver, exp);
			}
			
			System.out.println("Test Passed");
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
