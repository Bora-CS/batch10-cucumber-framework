package hui_automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ApplicationFormTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();
		String firstName = "John";
		String lastName = "Smith";
		String dob = "12/25/2000";
		String gender = "Other";
		String email = "john.smith.1225@testmail.com";
		String phoneNumber = "123-456-9999";
		boolean invalid = false;

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.manage().window().maximize();
			testDriver.get("https://boratech-practice-app.onrender.com/apply");
			WebElement sumbitButton = testDriver.findElement(By.xpath("//input[@value='Submit Application']"));

			// fill out the application form
			testDriver.findElement(By.name("firstname")).sendKeys(firstName);
			testDriver.findElement(By.name("lastname")).sendKeys(lastName);
			testDriver.findElement(By.name("dob")).sendKeys(dob);
			testDriver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();
			testDriver.findElement(By.name("email")).sendKeys(email);
			// switch between valid and invalid phone number
			if (invalid)
				testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			else {
				phoneNumber = phoneNumber.replace("-", "");
				testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			}

			Select courseSelect = new Select(testDriver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(testDriver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			testDriver.findElement(By.name("notarobot")).click();

			Testkeys.pause(6);
			if (sumbitButton.isEnabled())
				sumbitButton.click();

			By locator = By.cssSelector(".alert.alert-success");
			String realMessage = "";
			if (Testkeys.containsElement(testDriver, locator)) {
				WebElement goodTextElment = testDriver.findElement(locator);
				Testkeys.jsViewTop(testDriver);
				realMessage = goodTextElment.getText();
			} else {
				Testkeys.jsViewTop(testDriver);
				Testkeys.pause(3);
				throw new Exception("The application has been denied.");
			}

			Testkeys.pause(3);
			System.out.println("Test passed.");
			System.out.println(realMessage);
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Test completed.");
			testDriver.close();
			testDriver.quit();
		}

	}

}
