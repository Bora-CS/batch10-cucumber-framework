package helen;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helen.pojo.Education;
import helen.utilities.BoraTech;


public class AddEducation_DataDrivenTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		String username = "helenhjahn@gmail.com";
		String password = "06102021";

		
		//edu1) happy path
		Education edu1 = new Education("Boratech", "Certified Test Automation Engineer", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", null);
		//edu2) sad path - omit school
		Education edu2 = new Education("", "Certified Test Automation Engineer", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required" });
		//edu3) sad path - omit degree
		Education edu3 = new Education("Boratech", "", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "Degree is required" });
		//edu4) sad path - omit fieldOfStudy
		Education edu4 = new Education("Boratech", "Certified Test Automation Engineer", "", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "Field of study is required" });
		//edu5) sad path - omit from date
		Education edu5 = new Education("Boratech", "Certified Test Automation Engineer", "Certified Test Automation Engineer", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "From date is required" });
		//edu6) sad path - omit school and degree
		Education edu6 = new Education("", "", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Degree is required" });
		//edu7 sad path - omit school and fieldOfStudy
		Education edu7 = new Education("", "Certified Test Automation Engineer", "", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Field of study is required" });
		//edu8 sad path - omit school and from date
		Education edu8 = new Education("", "Certified Test Automation Engineer", "Certified Test Automation Engineer", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required",  "From date is required"});
		//edu9 sad path - omit degree and fieldOfStudy
		Education edu9 = new Education("Boratech", "", "", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "Degree is required" , "Field of study is required"});
		//edu10 sad path - omit degree and from date
		Education edu10  = new Education("Boratech", "", "Certified Test Automation Engineer", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "Degree is required", "From date is required" });
		//edu11 sad path - omit fieldOfStudy and from date
		Education edu11 = new Education("Boratech", "Certified Test Automation Engineer", "", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] {"Field of study is required", "From date is required"});
		//edu12 sad path - omit school, degree and fieldOfStudy 
		Education edu12  = new Education("", "", "", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Degree is required", "Field of study is required" });
		//edu13 omit school, degree and from date
		Education edu13  = new Education("", "","Certified Test Automation Engineer", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Degree is required", "From date is required" });
		//edu14 sad path - omit school, fieldOfStudy and from date
		Education edu14  = new Education("", "Certified Test Automation Engineer", "", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Field of study is required", "From date is required" });
		//edu15 sad path - omit degree, fieldOfStudy and from date
		Education edu15 = new Education("Boratech", "", "", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "Degree is required", "Field of study is required", "From date is required" });
		//edu16 sad path - omit school, degree, fieldOfStudy and from date
		Education edu16 = new Education("", "", "", "", "10/30/2022", false, "Learning all about Test Automation.", new String[] { "School is required", "Degree is required", "Field of study is required", "From date is required" });
						
						
		
		Education[] educations = { edu1, edu2, edu3, edu4, edu5, edu6, edu7, edu8, edu9, edu10, 
				edu11, edu12, edu13, edu14, edu15, edu16 };
		
		
		try {
			BoraTech.login(driver, username, password);
			for (Education edu : educations) {
				helen.utilities.BoraTech.addEducation(driver, edu);
				helen.utilities.BoraTech.educationValidation(driver, edu);
			}
	
			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: "+ e.getMessage());	
			e.printStackTrace();
			} finally {
			driver.close();
			driver.quit();	
		}
	}
		
	
	
}
