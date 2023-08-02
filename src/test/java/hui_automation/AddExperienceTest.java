package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		String expCompany = "Walmart";
		expCompany += " " + TestAsst.getUniqueMillsTimeStr();
		String expTitle = "Cashier";
		String expStartDate = "2010/06/06";
		String expEndDate = "2012/11/11";
		boolean current = true;

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// adding experience
			testDriver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			testDriver.findElement(By.xpath("//input[@name='title']")).sendKeys(expTitle);
			testDriver.findElement(By.xpath("//input[@name='company']")).sendKeys(expCompany);
			testDriver.findElement(By.xpath("//input[@name='location']")).sendKeys("Manassas, Virginia");

			testDriver.findElement(By.xpath("//input[@name='from']"))
					.sendKeys(TestAsst.findInputDateStrYMD(expStartDate, "yyyy/MM/dd"));
			if (current)
				testDriver.findElement(By.name("current")).click();
			else
				testDriver.findElement(By.xpath("//input[@name='to']"))
						.sendKeys(TestAsst.findInputDateStrYMD(expEndDate, "yyyy/MM/dd"));
			testDriver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Payment collection.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);

			// validation
			// validation
			boolean targetFound = false;
			// locating target row
			String targetRowXpath = "//h2[text()='Experience Credentials']/following-sibling::table/tbody//td[text()='"
					+ expCompany + "']/ancestor::tr";
			if (TestAsst.containsElement(testDriver, By.xpath(targetRowXpath))) {
				List<WebElement> targetCells = testDriver.findElement(By.xpath(targetRowXpath))
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
