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

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		String expCompany = "Walmart";
		expCompany += " " + Testkeys.getUniqueMillsTimeStr();
		String expTitle = "Cashier";
		String expStartDate = "06/06/2010";
		String expEndDate = "11/11/2012";
		boolean current = true;

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

			// adding experience
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(expTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(expCompany);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Manassas, Virginia");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(expStartDate);
			if (current)
				driver.findElement(By.name("current")).click();
			else
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(expEndDate);
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Payment collection.");

			Testkeys.pause(driver, 3);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.jsViewTop(driver);
			Testkeys.pause(driver, 3);

			// validation
			boolean targetFound = false;
			// locating target row
			String targetRowXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]/tbody//td[text()='"
					+ expCompany + "']/ancestor::tr";
			if (Testkeys.containsElement(driver, By.xpath(targetRowXpath))) {
				List<WebElement> targetCells = driver.findElement(By.xpath(targetRowXpath))
						.findElements(By.tagName("td"));
				// locating target cell
				for (WebElement cell : targetCells) {
					if (cell.getText().equals(expTitle)) {
						targetFound = true;
						break;
					}
				}
			}

			if (!targetFound) {
				throw new Exception("Expected experience data is not found.");
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
