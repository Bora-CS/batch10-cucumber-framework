package utilities;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import pojo.Experience;

public class BoraTech {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
		Keywords.wait(2);
	}

	public static void deleteAllExperiences(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Experience Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				Keywords.wait(1);
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No experience found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " experience(s).");
		}
	}

	public static void addExperience(WebDriver driver, Experience experience) throws Exception {
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
		Keywords.wait(2);

		String tableXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]";
		String tableRowXpath = tableXpath + "/tbody/tr";

		List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));

		boolean found = false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String actualCompany = cells.get(0).getText();
			String actualJobTitle = cells.get(1).getText();
			if (experience.jobTitle.equals(actualJobTitle) && experience.company.equals(actualCompany)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The newly entered experience was not found");
		}
	}
	
}
