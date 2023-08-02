package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		String expSchool = "George Mason University";
		expSchool += " " + TestAsst.getUniqueMillsTimeStr();
		String expDegree = "Bachelor's Degree";
		String expStartDate = "2008/01/31";
		String expEndDate = "2010/05/15";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@name='school']")).sendKeys(expSchool);
			testDriver.findElement(By.xpath("//input[@name='degree']")).sendKeys(expDegree);
			testDriver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Biology");

			testDriver.findElement(By.xpath("//input[@name='from']"))
					.sendKeys(TestAsst.findInputDateStrYMD(expStartDate, "yyyy/MM/dd"));
			testDriver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(TestAsst.findInputDateStrYMD(expEndDate, "yyyy/MM/dd"));
			testDriver.findElement(By.xpath("//textarea[@name='description']"))
					.sendKeys("Practice the scientific study of life.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);

			// validation
			boolean targetFound = false;
			// locating target row
			String targetRowXpath = "//h2[text()='Education Credentials']/following-sibling::table/tbody//td[text()='"
					+ expSchool + "']/ancestor::tr";
			if (TestAsst.containsElement(testDriver, By.xpath(targetRowXpath))) {
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
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}
	}

}
