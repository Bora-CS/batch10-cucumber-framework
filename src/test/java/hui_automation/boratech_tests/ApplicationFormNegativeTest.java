package hui_automation.boratech_tests;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import hui_automation.Testkeys;

public class ApplicationFormNegativeTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();
		String firstName = "John";
		String lastName = "Smith";
		String gender = "Other";
		String email = "john.smith.1225#testmail.com";
		String phoneNumber = "123-456-9999";
		boolean invalid = true;

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.manage().window().maximize();
			testDriver.get("https://boratech-practice-app.onrender.com/apply");
			WebElement sumbitButton = testDriver.findElement(By.xpath("//input[@value='Submit Application']"));

			// fill out the application form
			if (invalid) {
				firstName = "";
				lastName = "";
			}
			testDriver.findElement(By.name("firstname")).sendKeys(firstName);
			testDriver.findElement(By.name("lastname")).sendKeys(lastName);

			// sending messed up dob
			LocalDate current = LocalDate.now();
			if (invalid) {
				current = current.plusDays(2);
				String messDob = Testkeys.findDateInputStrMDY(current.toString(), "uuuu-MM-dd");
				testDriver.findElement(By.name("dob")).sendKeys(messDob);
			} else {
				current = current.minusYears(19);
				String dob = Testkeys.findDateInputStrMDY(current.toString(), "uuuu-MM-dd");
				testDriver.findElement(By.name("dob")).sendKeys(dob);
			}

			// sending messed up phone number
			if (!invalid)
				phoneNumber = phoneNumber.replace("-", "");
			testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);

			// sending messed up email
			if (!invalid)
				email = email.replaceAll("[!#$%^&*]", "@");
			testDriver.findElement(By.name("email")).sendKeys(email);

			testDriver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();

			Select courseSelect = new Select(testDriver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(testDriver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			testDriver.findElement(By.name("notarobot")).click();
			Testkeys.pause(5);
			
			if (sumbitButton.isEnabled())
				sumbitButton.click();
			Testkeys.jsViewTop(testDriver);

			// locating error messages
			By alertLocator = By.cssSelector(".alert.alert-success");
			if (Testkeys.containsElement(testDriver, alertLocator)) {
				String realMessage = testDriver.findElement(alertLocator).getText();
				Testkeys.pause(2);
				System.out.println(realMessage);
				throw new Exception("The application should have been denied.");
			}

			List<WebElement> errMsgBlocks = testDriver.findElements(By.cssSelector(".alert.alert-danger"));
			for (WebElement errMsgBlock : errMsgBlocks) {
				System.out.println("Error: " + errMsgBlock.getText());
			}
			Testkeys.jsViewTop(testDriver); // view error messages
			Testkeys.pause(3);

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Test completed!");
			testDriver.close();
			testDriver.quit();
		}

	}

}
