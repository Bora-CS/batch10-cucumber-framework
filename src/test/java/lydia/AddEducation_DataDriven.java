package lydia;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lydia.POJO.Education;
import lydia.utilities.BoraTech_Utilities;



public class AddEducation_DataDriven {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

		
		String username = "w.lydia.liu@gmail.com";
		String password = "Liu123456";
		
		Education edu1 = new Education("", "Master","Business Administration", "01/10/2005", "06/27/2007", 
				false, "It's 2-year in school program.",new String[] {"School is required"});
		Education edu2 = new Education("BoraTech", "", "Test Automation","05/06/2023", "",true, 
				"It's a 6-month BootCampt program." , new String[] {"Degree is required"});
		Education edu3 = new Education("", "", "Test Automation","05/06/2023", "",true,
				"It's a 6-month BootCampt program." , new String[] {"School is required","Degree is required"});
		Education[] educations = {edu1, edu2, edu3};
		

		try {
			BoraTech_Utilities.login(driver, username, password);

			for (Education education : educations) {
				BoraTech_Utilities.addEducation(driver, education);
				BoraTech_Utilities.educationValidation(driver, education);
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



