package lenaTest.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import lenaTest.pojo.Education;
import utilities.Keywords;

public class Add_Education {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {

		driver.get("https://boratech-practice-app.onrender.com/login");

		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
	}

	public static void deleteAllEducations(WebDriver driver) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Education Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No education found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " educations(s).");
		}
	}

	public static void addEducations(WebDriver driver, Education education) throws Exception {

		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(education.school);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(education.degree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(education.major);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(education.from);
		driver.findElement(By.xpath("//input[@name='to']")).sendKeys(education.to);
		driver.findElement(By.tagName("textarea")).sendKeys(education.Description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
//		if (education.current) {
//			driver.findElement(By.xpath("//input[@name='current']")).click();
//		} else {
//			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(education.to);
//		}

//		String tableXpath = "//h2[text()='Education Credentials']/following-sibling::table[1]";
//		String tableRowXpath = tableXpath + "/tbody/tr";

//		List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));

//		boolean found = false;
//		for (WebElement row : rows) {
//			List<WebElement> cells = row.findElements(By.tagName("td"));
//			String actualSchool = cells.get(0).getText();
//			String actualDegree = cells.get(1).getText();
//			if (education.school.equals(actualSchool) && education.degree.equals(actualDegree)) {
//				found = true;
//				break;
//			}
//		}
//
//		if (!found) {
//			throw new Exception("The newly entered education was not found");
//		}

	}

}
