package hui_automation;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

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
			testDriver.findElement(By.name("firstname")).sendKeys(firstName);
			testDriver.findElement(By.name("lastname")).sendKeys(lastName);

			// sending messed up dob
			LocalDate current = LocalDate.now();
			if (invalid) {
				current = current.plusDays(2);
				String messDob = TestAsst.findInputDateStrMDY(current.toString(), "uuuu-MM-dd");
				testDriver.findElement(By.name("dob")).sendKeys(messDob);
			} else {
				current = current.minusYears(19);
				String dob = TestAsst.findInputDateStrMDY(current.toString(), "uuuu-MM-dd");
				testDriver.findElement(By.name("dob")).sendKeys(dob);
			}

			// sending messed up phone number
			if (invalid)
				testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			else {
				phoneNumber = phoneNumber.replace("-", "");
				testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			}

			// sending messed up email
			if (invalid)
				testDriver.findElement(By.name("email")).sendKeys(email);
			else {
				email = email.replaceAll("[!#$%^&*]", "@");
				testDriver.findElement(By.name("email")).sendKeys(email);
			}

			testDriver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();

			Select courseSelect = new Select(testDriver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(testDriver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			testDriver.findElement(By.name("notarobot")).click();

			TestAsst.sleep(6);
			if (sumbitButton.isEnabled())
				sumbitButton.click();

			// locating error messages
			JavascriptExecutor js = (JavascriptExecutor) testDriver;
			By alertLocator = By.cssSelector(".alert.alert-success");
			if (TestAsst.containsElement(testDriver, alertLocator)) {
				js.executeScript("window.scrollTo(0, 0)");
				String realMessage = testDriver.findElement(alertLocator).getText();
				TestAsst.sleep(2);
				System.out.println(realMessage);
				throw new Exception("The application should have been denied.");
			}

			List<WebElement> errMsgBlocks = testDriver.findElements(By.cssSelector(".alert.alert-danger"));
			for (WebElement errMsgBlock : errMsgBlocks) {
				System.out.println(errMsgBlock.getText());
			}
			js.executeScript("window.scrollTo(0, 0)");

			TestAsst.sleep(3);
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
