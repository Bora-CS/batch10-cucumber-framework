package hui_automation.boratech_tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.Testkeys;

public class AddEducationNegativeTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		List<String> errMessages = new ArrayList<>();
		errMessages.add("School is required");
		errMessages.add("Degree is required");
		errMessages.add("Field of study is required");
		errMessages.add("From date is required");

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.manage().window().maximize();
			testDriver.get("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// fake attempt at adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.jsViewTop(testDriver);
			
			// validation
			List<WebElement> errBlocks = testDriver.findElements(By.cssSelector(".alert.alert-danger"));
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
			
			Testkeys.pause(3);
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.close();
			testDriver.quit();
		}

	} // main

}
