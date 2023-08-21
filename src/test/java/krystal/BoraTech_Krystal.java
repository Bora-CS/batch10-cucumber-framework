package krystal;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.Keywords;

public class BoraTech_Krystal {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
		Keywords.wait(2);
	}

	public static void deleteAllEducations(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Education Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				Keywords.wait(2);
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No education found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " education(s).");
		}
	}

	public static void addEducations(WebDriver driver, Pojo_Education_Krystal education) throws Exception {

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
		Keywords.wait(3);

	}
}


