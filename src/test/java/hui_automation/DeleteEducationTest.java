package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// deleting education
			List<WebElement> eduTRows = testDriver.findElements(
					By.xpath("//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr"));
			// pre-deletion size
			int totalRows = eduTRows.size();
			String delSchool = "";
			if (eduTRows.size() > 0) {
				for (WebElement row : eduTRows) {
					delSchool = row.findElement(By.tagName("td")).getText();
					row.findElement(By.tagName("button")).click();
					break;
				}
			}

			// validation
			eduTRows = testDriver.findElements(
					By.xpath("//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr"));
			if (!(totalRows - eduTRows.size() == 1)) {
				String testFailStr = "Deletion failed\n";
				testFailStr += "Total rows before deletion: " + totalRows + "\n";
				testFailStr += "Total rows after deletion: " + eduTRows.size() + "\n";
				throw new Exception(testFailStr);
			}
			
			System.out.println("Test passed.");
			System.out.println("School deleted: " + delSchool);
			System.out.println("Final total rows: " + eduTRows.size());
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			try {
				TestAsst.sleep(3);
			} catch (Exception e) {
				System.out.println("Insomnia".toUpperCase());
			}
			testDriver.close();
			testDriver.quit();
		}
	}

}
