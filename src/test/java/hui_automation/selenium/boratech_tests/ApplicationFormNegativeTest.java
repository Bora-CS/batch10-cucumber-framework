package hui_automation.selenium.boratech_tests;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import hui_automation.utilities.Testkeys;

public class ApplicationFormNegativeTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String firstName = "John";
		String lastName = "Smith";
		String gender = "Other";
		String email = "john.smith.1225#testmail.com";
		String phoneNumber = "123-456-9999";
		boolean invalid = true;

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			driver.get("https://boratech-practice-app.onrender.com/apply");
			WebElement sumbitButton = driver.findElement(By.xpath("//input[@value='Submit Application']"));

			// fill out the application form
			if (invalid) {
				firstName = "";
				lastName = "";
			}
			driver.findElement(By.name("firstname")).sendKeys(firstName);
			driver.findElement(By.name("lastname")).sendKeys(lastName);

			// sending messed up dob
			LocalDate current = LocalDate.now();
			if (invalid) {
				current = current.plusDays(2);
				String messDob = Testkeys.findDateInputStrMDY(current.toString(), "uuuu-MM-dd");
				driver.findElement(By.name("dob")).sendKeys(messDob);
			} else {
				current = current.minusYears(19);
				String dob = Testkeys.findDateInputStrMDY(current.toString(), "uuuu-MM-dd");
				driver.findElement(By.name("dob")).sendKeys(dob);
			}

			// sending messed up phone number
			if (!invalid)
				phoneNumber = phoneNumber.replace("-", "");
			driver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);

			// sending messed up email
			if (!invalid)
				email = email.replaceAll("[!#$%^&*]", "@");
			driver.findElement(By.name("email")).sendKeys(email);

			driver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();

			Select courseSelect = new Select(driver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(driver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			driver.findElement(By.name("notarobot")).click();
			Testkeys.pause(driver, 3);

			if (sumbitButton.isEnabled())
				sumbitButton.click();
			Testkeys.jsViewTop(driver);

			// locating error messages
			By alertLocator = By.cssSelector(".alert.alert-success");
			if (Testkeys.containsElement(driver, alertLocator)) {
				String realMessage = driver.findElement(alertLocator).getText();
				Testkeys.pause(driver, 3);
				System.out.println(realMessage);
				throw new Exception("The application should have been denied.");
			}

			List<WebElement> errMsgBlocks = driver.findElements(By.cssSelector(".alert.alert-danger"));
			for (WebElement errMsgBlock : errMsgBlocks) {
				System.out.println("Error: " + errMsgBlock.getText());
			}
			Testkeys.jsViewTop(driver); // view error messages
			Testkeys.pause(driver, 3);

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Test completed!");
			Testkeys.terminate(driver);
		}

	}

}
