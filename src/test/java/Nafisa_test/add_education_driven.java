package Nafisa_test;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pojo.Education;
import utilities.BoraTech;

public class add_education_driven {


		public static void main(String[] args) {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

			String username = "nanfeise@gmail.com";
			String password = "Nafisa1996";

			Education edu1 = new Education("James madison university", "Certified socail worker", "socail work",
					"01/15/2015", "12/15/2019", false, "Identify people and communities in need of help",
					new String[] { "School is required" });
			Education edu2 = new Education("BoraTech", "", "Test Automation Engineering", "08/13/2020", "02/13/2021", false,
					"Thinking about how to automate everything everyday", new String[] { "Degree is required" });
			

			Education[] educations = { edu1, edu2 };

			try {
				BoraTech.login(driver, username, password);

				for (Education education : educations) {
					BoraTech.addEducation(driver, education);
					BoraTech.educationValidation(driver, education);
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
