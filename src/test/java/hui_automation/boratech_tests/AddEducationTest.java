package hui_automation.boratech_tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.Testkeys;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		String expSchool = "George Mason University";
		expSchool += " " + Testkeys.getUniqueMillsTimeStr();
		String expDegree = "Bachelor's Degree";
		String expStartDate = "2008/01/31";
		String expEndDate = "2010/05/15";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.manage().window().maximize();
			testDriver.get("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.pause(3);
			
			// adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@name='school']")).sendKeys(expSchool);
			testDriver.findElement(By.xpath("//input[@name='degree']")).sendKeys(expDegree);
			testDriver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Biology");

			testDriver.findElement(By.xpath("//input[@name='from']"))
					.sendKeys(Testkeys.findDateInputStrMDY(expStartDate, "yyyy/MM/dd"));
			testDriver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(Testkeys.findDateInputStrMDY(expEndDate, "yyyy/MM/dd"));
			testDriver.findElement(By.xpath("//textarea[@name='description']"))
					.sendKeys("Practice the scientific study of life.");
			
			Testkeys.pause(3);
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.jsViewTop(testDriver);
			Testkeys.pause(3);
			
			// validation
			boolean targetFound = false;
			// locating target row
			String targetRowXpath = "//h2[text()='Education Credentials']/following-sibling::table[1]/tbody//td[text()='"
					+ expSchool + "']/ancestor::tr";
			if (Testkeys.containsElement(testDriver, By.xpath(targetRowXpath))) {
				List<WebElement> targetCells = testDriver.findElement(By.xpath(targetRowXpath))
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
			List<WebElement> targetCells = testDriver.findElement(By.xpath(targetRowXpath))
					.findElements(By.tagName("td"));
			System.out.println(targetCells.get(0).getText());
			System.out.println(targetCells.get(1).getText());
			System.out.println(targetCells.get(2).getText());
			Testkeys.pause(3);
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.close();
			testDriver.quit();
		}
	}

}
