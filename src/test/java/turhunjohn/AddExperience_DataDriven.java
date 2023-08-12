package turhunjohn;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import selenium.BoraTechLogin;
import turhun_pojo.Experience;
import utilities.BoraTech;
import utilities.Keywords;

public class AddExperience_DataDriven {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";

		Experience exp1 = new Experience("Junior Cashier", "McDonald's", "Annandale, VA", "02/12/2013", "02/12/2014",
				false, "Just learning how to take orders and count some money");
		Experience exp2 = new Experience("Cashier", "Popeyes", "Fairfax, VA", "02/16/2014", "08/12/2020", false,
				"Counting money everyday");
		Experience exp3 = new Experience("Information Security Officer", "AWS", "Chantilly, VA", "10/05/2021", "", true,
				"Conduct inspections, audits and reviews in your area of responsibility");

		Experience[] experiences = { exp1, exp2, exp3 };

		try {
			BoraTech.login(driver, username, password);

			for (Experience experience : experiences) {
				BoraTech.addExperience(driver, experience);
			}

			BoraTech.deleteAllExperiences(driver);

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
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
			String actualjobTitle = cells.get(1).getText();
			if (experience.jobTitle.equals(actualjobTitle) && experience.company.equals(actualCompany)) {
				found = true;
				break;
			}
		}
		if (!found) {
			throw new Exception("The newly entered experience was not found");
		}
	}
}
