package ardal_practice;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Keywords;

public class ApplicationFormNegativeTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String firstName = "Ardal";
		String lastName = "";
		String dob = "10/27/1994";
		String email = "";
		String phone = "7787787788";

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
			Keywords.wait(2);

			String actualUrl = driver.getCurrentUrl();
			if (!actualUrl.endsWith("/apply")) {
				throw new Exception("Expected to have '/apply' in the url.\nCurrent URL: " + actualUrl);
			}

			String expectedTitleText = "Application Form";
			String actualTitleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!actualTitleText.equals(expectedTitleText)) {
				throw new Exception(
						"Title Text doesn't match.\nExpected: " + expectedTitleText + "\nActual: " + actualTitleText);
			}

			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//*[@name='dob']")).sendKeys(dob);
			driver.findElement(By.xpath("//input[@value='male']")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phone);

			Select course = new Select(driver.findElement(By.xpath("//*[@name='course']")));
			course.selectByValue("sdet");

			Select source = new Select(driver.findElement(By.xpath("//*[@name='source']")));
			source.selectByIndex(2);

			driver.findElement(By.xpath("//*[@name='notarobot']")).click();
			Keywords.wait(5);

			driver.findElement(By.xpath("//*[@value='Submit Application']")).click();
			Keywords.wait(1);

			List<WebElement> errorMessages = driver.findElements(By.xpath("//*[@class='alert alert-danger']"));
			List<String> actualMessages = new ArrayList<>();
			for (WebElement errorMessage : errorMessages) {
				actualMessages.add(errorMessage.getText());
			}

			List<String> expectedMessages = new ArrayList<>();
			expectedMessages.add("Last name is required");
			expectedMessages.add("Email is required");
			expectedMessages.add("Please enter a valid email");

			for (String expectedMessage : expectedMessages) {
				if (!actualMessages.contains(expectedMessage)) {
					throw new Exception("Expected error message not found");
				}
			}

			if (expectedMessages.size() != actualMessages.size()) {
				throw new Exception("Error messages number doesn't match\nActual Messages: " + actualMessages
						+ "\nExpected Messages: " + expectedMessages);
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
