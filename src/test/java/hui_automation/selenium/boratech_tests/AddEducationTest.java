package hui_automation.selenium.boratech_tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.utilities.Testkeys;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		String expSchool = "George Mason University";
		expSchool += " " + Testkeys.getUniqueMillsTimeStr();
		String expDegree = "Bachelor's Degree";
		String expStartDate = "01/31/2008";
		String expEndDate = "05/15/2010";

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait.until(ExpectedConditions.urlContains("dashboard"));

			// adding education
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys(expSchool);
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(expDegree);
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Biology");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(expStartDate);
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(expEndDate);
			driver.findElement(By.xpath("//textarea[@name='description']"))
					.sendKeys("Practice the scientific study of life.");

			Testkeys.pause(driver, 1);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.jsViewTop(driver);
			Testkeys.pause(driver, 1);

			// validation
			boolean targetFound = false;
			// locating target row
			String targetRowXpath = "//h2[text()='Education Credentials']/following-sibling::table[1]/tbody//td[text()='"
					+ expSchool + "']/ancestor::tr";
			if (Testkeys.containsElement(driver, By.xpath(targetRowXpath))) {
				List<WebElement> targetCells = driver.findElement(By.xpath(targetRowXpath))
						.findElements(By.tagName("td"));
				// locating target cell
				for (WebElement cell : targetCells) {
					if (cell.getText().equals(expDegree)) {
						targetFound = true;
						break;
					}
				}
			}

			if (!targetFound) {
				throw new Exception("Expected education data is not found.");
			}

			System.out.println("Test passed.");
			List<WebElement> targetCells = driver.findElement(By.xpath(targetRowXpath)).findElements(By.tagName("td"));
			System.out.println(targetCells.get(0).getText());
			System.out.println(targetCells.get(1).getText());
			System.out.println(targetCells.get(2).getText());
			Testkeys.pause(driver, 3);
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			Testkeys.terminate(driver);
		}
	}

}
