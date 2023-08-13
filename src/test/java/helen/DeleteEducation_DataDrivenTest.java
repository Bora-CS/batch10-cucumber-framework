package helen;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helen.pojo.Education;
import helen.utilities.BoraTech;

public class DeleteEducation_DataDrivenTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		String username = "helenhjahn@gmail.com";
		String password = "06102021";
		
		Education edu1 = new Education("Bora Tech", "Test Automation Engineer", "Java, Selenium", "05/01/2022",
								"10/30/2022", false, "Learning all about Test Automation", new String[] { "School is required" });
		Education edu2 = new Education("Bora School", "Tools", "My SQL", "05/01/2023",
				"7/05/2023", false, "Learning all about Test Automation", new String[] { "School is required" });

		Education[] educations = {edu1, edu2};
		
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			
			BoraTech.login(driver, username, password);

			for (Education education : educations) {
				BoraTech.addEducation(driver, education);
			}
			
			BoraTech.deleteAllEducation(driver);
			
	
			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: "+ e.getMessage());	
		} finally {
			driver.close();
			driver.quit();	
		}
	}
	
}
