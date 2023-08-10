package krystal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class HomePractice {

	public static void main(String[] args) {

		String email = "anth0ny@gmail.com";
		String password = "PaSsWoRd123!";
		String homePage = "//*[@href='/']";
		String experienceLink = "//*[@href='/add-experience']";
		String educationLink = "//*[@href='/add-education']";

		WebDriver driver = new ChromeDriver();

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);

			String addExperienceText = driver.findElement(By.xpath(experienceLink)).getText();
			String addEducationText = driver.findElement(By.xpath(educationLink)).getText();
			String homePageText = driver.findElement(By.xpath(homePage)).getText();

			if (!addEducationText.equals("Add Education")) {
				throw new Exception("does not match + \n it should be " + addEducationText);
			}
			if (!addExperienceText.equals("Add Experience")) {
				throw new Exception("does not match + \n it should be " + addExperienceText);
			}
			if (!homePageText.equals("BoraTech")) {
				throw new Exception("does not match + \n it should be " + homePageText);
			}
			System.out.println("no error woohoo");

		} catch (Exception e) {
			System.out.println("error");
			System.out.println(e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	} // main

}
