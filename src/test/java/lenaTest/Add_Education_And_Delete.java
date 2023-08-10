package lenaTest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import lenaTest.pojo.Education;
import lenaTest.utilities.Add_Education;
import utilities.Keywords;

public class Add_Education_And_Delete {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().window().maximize();

		String username = "chenlena@outlook.com";
		String password = "798911";

		// Education Objects;
		Education edu1 = new Education("BoraTech", "Bootcamp", "QA Test", "05/08/2023", "11/7/2023",
				"quality assrance Test");
		Education edu2 = new Education("University", "Bachlor", "English", "09/01/2013", "7/1/2017",
				"English and French");

		// Education Array for looping;
		Education[] educations = { edu1, edu2 };

		// Logging ---> loop to add experiences
		try {
			Add_Education.login(driver, username, password);

			for (Education singleEducation : educations) {
				Add_Education.addEducations(driver, singleEducation);
				Keywords.wait(2);
			}

			// Delete all the educations if they exist;
			Keywords.wait(2);
			Add_Education.deleteAllEducations(driver);
			System.out.println("test successful");
		} catch (Exception e) {
			System.out.println("test failed");
			System.out.println("Reason " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
