package hui_automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
		String phoneNumber = "1234569999";

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
			testDriver.findElement(By.name("phonenumber")).sendKeys(phoneNumber);

			Select courseSelect = new Select(testDriver.findElement(By.name("course")));
			courseSelect.selectByValue("sdet");
			Select sourceSelect = new Select(testDriver.findElement(By.name("source")));
			sourceSelect.selectByValue("website");

			testDriver.findElement(By.name("notarobot")).click();

			TestAsst.sleep(6);
			if (sumbitButton.isEnabled())
				sumbitButton.click();

			JavascriptExecutor js = (JavascriptExecutor) testDriver;
			By locator = By.cssSelector(".alert.alert-success");
			String realMessage = "";
			if (TestAsst.containsElement(testDriver, locator)) {
				WebElement goodTextElment = testDriver.findElement(locator);
				js.executeScript("window.scrollTo(0, 0)");
				realMessage = goodTextElment.getText();
			} else {
				js.executeScript("window.scrollTo(0, 0)");
				TestAsst.sleep(3);
				throw new Exception("The application has been denied.");
			}

			TestAsst.sleep(3);
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
