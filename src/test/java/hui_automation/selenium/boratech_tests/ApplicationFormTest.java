package hui_automation.selenium.boratech_tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import hui_automation.utilities.Testkeys;

public class ApplicationFormTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String firstName = "John";
		String lastName = "Smith";
		String dob = "12/25/1999";
		String gender = "Male";
		String email = "john.smith.1225@testmail.com";
		String phoneNumber = "123-456-9999";
		boolean invalid = false;

		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			driver.manage().window().maximize();
			driver.get("https://boratech-practice-app.onrender.com/apply");
			WebElement submitButton = driver.findElement(By.xpath("//input[@value='Submit Application']"));

			// fill out the application form
			driver.findElement(By.name("firstname")).sendKeys(firstName);
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.name("dob")).sendKeys(dob);
			driver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();
			driver.findElement(By.name("email")).sendKeys(email);
			// switch between valid and invalid phone number
			if (invalid)
				driver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			else {
				phoneNumber = phoneNumber.replace("-", "");
				driver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);
			}

			Select courseSelect = new Select(driver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(driver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			driver.findElement(By.name("notarobot")).click();

			Testkeys.pause(driver, 6);
			if (submitButton.isEnabled())
				submitButton.click();
			Testkeys.jsViewTop(driver);

			By locator = By.cssSelector(".alert.alert-success");
			String realMessage = "";
			if (Testkeys.containsElement(driver, locator)) {
				WebElement goodTextElment = driver.findElement(locator);
				realMessage = goodTextElment.getText();
			} else {
				List<WebElement> errMsgBlocks = driver.findElements(By.cssSelector(".alert.alert-danger"));
				for (WebElement errMsgBlock : errMsgBlocks)
					System.out.println("Error: " + errMsgBlock.getText());
				Testkeys.pause(driver, 3);
				throw new Exception("The application has been denied.");
			}

			System.out.println("Test passed.");
			System.out.println(realMessage);
			Testkeys.pause(driver, 3);
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println(e.getMessage());
		} finally {
			System.out.println("Test completed.");
			Testkeys.terminate(driver);
		}

	}

}
