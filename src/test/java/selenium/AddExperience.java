package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BoraTech;
import utilities.Keywords;

public class AddExperience {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";
		String jobTitle = "Senior Cashier";
		String company = "Chik-fil-a" + Keywords.getTimeStamp();
		String location = "Fairfax, VA";
		String from = "08/13/2020";
		String to = "";
		boolean current = true;
		String description = "Bro I know I still count money, but I eat 'healthy' nowdays";

		try {
			BoraTech.login(driver, username, password);

			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
//			wait.until(ExpectedConditions.)
			Keywords.wait(2);

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			if (current) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			}
			driver.findElement(By.tagName("textarea")).sendKeys(description);
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
				if (jobTitle.equals(actualJobTitle) && company.equals(actualCompany)) {
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("The newly entered experience was not found");
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
