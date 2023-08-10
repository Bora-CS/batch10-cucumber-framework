package anthony;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utilities.Keywords;

public class login_exp_edu {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
		Keywords.wait(2);
	}

	public static void addExperience(WebDriver driver, anthony_pojo.Experience experience) throws Exception {
		Keywords.wait(15);

		driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
		Keywords.wait(2);

		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(experience.jobTitle);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(experience.company);
		driver.findElement(By.xpath("//input[@name='location']")).sendKeys(experience.location);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(experience.from);
		if (experience.current) {
			driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(experience.to);
		}
		driver.findElement(By.tagName("textarea")).sendKeys(experience.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Keywords.wait(10);
//computer or site being slow

	}

	public static void addEducation(WebDriver driver, anthony_pojo.Education education) throws Exception {
		Keywords.wait(15);
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();
		Keywords.wait(2);

		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(education.school);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(education.degree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(education.fieldOfStudy);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(education.from);
		if (education.current) {
			driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(education.to);
		}
		driver.findElement(By.tagName("textarea")).sendKeys(education.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Keywords.wait(2);

	}
}
