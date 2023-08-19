package ardal_practice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ardal_practice.pojo.Education;

public class Data_Driven_AddEducation {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String userName = "ardal002713@gmail.com";
		String password = "ardal123";

		Education edu1 = new Education("UBC", "Bachelor", "Civil Engineering", "03/09/2015", false, "05/27/2016",
				"Picked such a hard major and had to drop");
		Education edu2 = new Education("UCLA", "Master", "City Engineering", "05/28/2016", false, "05/27/2017",
				"Picked such a hard major and had to drop again");
		Education edu3 = new Education("UFO", "PHD", "Subway Engineering", "03/10/2017", false, "05/27/2018",
				"Picked such a hard major and had to drop agian and again");
		Education edu4 = new Education("Harward", "Post-PHD", "Wivil Engineering", "03/09/2018", true, "",
				"Picked such a hard major and had to drop again and again and again");

		List<Education> educations = new ArrayList<>();
		educations.add(edu1);
		educations.add(edu2);
		educations.add(edu3);
		educations.add(edu4);

		try {
			BoraTechArdal.login(driver, userName, password);

			for (Education education : educations) {
				BoraTechArdal.addEducations(driver, education);
			}

			BoraTechArdal.deleteAllEducations(driver);

		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Something went wrong " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
