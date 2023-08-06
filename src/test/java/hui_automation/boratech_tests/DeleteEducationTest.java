package hui_automation.boratech_tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.Testkeys;

public class DeleteEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.manage().window().maximize();
			testDriver.get("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Testkeys.pause(3);

			// deleting education
			List<WebElement> eduRows = testDriver.findElements(
					By.xpath("//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr"));
			// pre-deletion size
			int totalRows = eduRows.size();
			String delSchool = "";
			if (eduRows.size() > 0) {
				for (WebElement row : eduRows) {
					delSchool = row.findElement(By.tagName("td")).getText();
					row.findElement(By.tagName("button")).click(); // click delete
					break;
				}
			}
			
			Testkeys.pause(3);
			// validation
			eduRows = testDriver.findElements(
					By.xpath("//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr"));
			if (!(totalRows - eduRows.size() == 1)) {
				String testFailStr = "Deletion failed\n";
				testFailStr += "Total rows before deletion: " + totalRows + "\n";
				testFailStr += "Total rows after deletion: " + eduRows.size() + "\n";
				throw new Exception(testFailStr);
			}
			
			System.out.println("Test passed.");
			System.out.println("School deleted: " + delSchool);
			System.out.println("Final total rows: " + eduRows.size());
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
