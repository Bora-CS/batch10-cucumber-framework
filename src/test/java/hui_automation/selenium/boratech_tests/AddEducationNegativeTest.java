package hui_automation.selenium.boratech_tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.utilities.Testkeys;

public class AddEducationNegativeTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		List<String> errMessages = new ArrayList<>();
		errMessages.add("School is required");
		errMessages.add("Degree is required");
		errMessages.add("Field of study is required");
		errMessages.add("From date is required");

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			// fake attempt at adding education
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.jsViewTop(driver);

			// validation
			List<WebElement> errBlocks = driver.findElements(By.cssSelector(".alert.alert-danger"));
			if (errBlocks.size() == errMessages.size()) {
				for (WebElement errBlock : errBlocks) {
					if (!errMessages.contains(errBlock.getText()))
						throw new Exception(
								"Such error message does not exist in the system: [" + errBlock.getText() + "]");
				}
			} else {
				String testFailStr = "Error messages mismatch\n";
				testFailStr += "Expected numbers of error messages: " + errMessages.size() + "\n";
				testFailStr += "Actual numbers of error messages: " + errBlocks.size() + "\n";
				throw new Exception(testFailStr);
			}

			Testkeys.pause(driver, 3);
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			Testkeys.terminate(driver);
		}

	} // main

}
