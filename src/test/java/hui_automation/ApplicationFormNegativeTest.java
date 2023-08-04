package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ApplicationFormNegativeTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();
		String firstName = "88";
		String lastName = "Smith";
		String dob = "12/25/2000";
		String gender = "Other";
		String email = "john.smith.1225@testmail.com";
		String phoneNumber = "12345";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.get("https://boratech-practice-app.onrender.com/apply");
			WebElement sumbitButton = testDriver.findElement(By.xpath("//input[@value='Submit Application']"));

			// fill out the application form
			testDriver.findElement(By.name("firstname")).sendKeys(firstName);
			testDriver.findElement(By.name("lastname")).sendKeys(lastName);
			testDriver.findElement(By.name("dob")).sendKeys(dob);
			testDriver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();
			testDriver.findElement(By.name("email")).sendKeys(email);
			testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			
			Select courseSelect = new Select(testDriver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(testDriver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");
						
			testDriver.findElement(By.name("notarobot")).click();
			if (sumbitButton.isEnabled())
				sumbitButton.click();
			
			List<WebElement> errMsgBlocks = testDriver.findElements(By.cssSelector(".alert.alert-danger"));
			for (WebElement errMsgBlock : errMsgBlocks) {
				System.out.println(errMsgBlock.getText());
			}

			TestAsst.sleep(3);
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			testDriver.quit();
		}

	}

}
