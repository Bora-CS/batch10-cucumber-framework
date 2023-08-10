package lydia;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lydia.POJO.Education;
import lydia.utilities.BoraTech_Utilities;


public class AddEducation_DataDriven {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		String username = "w.lydia.liu@gmail.com";
		String password = "Liu123456";
		
		
		Education edu2 = new Education("UNVA", "Master","Business Administration", "01/10/2005", false, "06/27/2007", "It's 2-year in school program.");
		Education edu1 = new Education("BoraTech", "Certificate", "Test Automation" ,"05/06/2023",true, "","It's a 6-month intense bootcamp program.");
		
		Education[] educations = {edu1, edu2};
		try {
			BoraTech_Utilities.login(driver, username, password);

			for (Education education : educations) {
				BoraTech_Utilities.addEducation(driver, education);
			}

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
