package ardal_practice;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceNegativeTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String jobTitle = "";
		String company = "";
		String location = "Burnaby,BC";
		String from = "02/14/2020";
		String to = "";
		Boolean current = true;
		String description = "Keeping everyone safe";

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("ardal002713@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Ardal002713" + Keys.ENTER);
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@href='/add-experience']")).click();

			// start adding experience functions
			driver.findElement(By.name("title")).sendKeys(jobTitle);
			driver.findElement(By.name("company")).sendKeys(company);
			driver.findElement(By.name("location")).sendKeys(location);
			driver.findElement(By.xpath("//*[@name='from']")).sendKeys(from);

			if (current) {
				driver.findElement(By.xpath("//*[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//*[@name='to']")).sendKeys(to);
			}

			driver.findElement(By.xpath("//*/textarea[@name='description']")).sendKeys(description);
			driver.findElement(By.xpath("//*/input[@type='submit']")).click();
			Thread.sleep(2000);

			// see if error messages number is same

			List<WebElement> errorMessages = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
			List<String> actualErrors = new ArrayList<>();
			for (WebElement errorMessage : errorMessages) {
				actualErrors.add(errorMessage.getText());
			}

			List<String> expectedErrors = new ArrayList<>();
			expectedErrors.add("Title is required");
			expectedErrors.add("Company is required");

			if (actualErrors.size() != expectedErrors.size()) {
				throw new Exception("Error message size doesn't match" + actualErrors);
			}

			// comparing all the errors

			for (String expectedError : expectedErrors) {
				if (!actualErrors.contains(expectedError)) {
					throw new Exception("Expected errors not found" + actualErrors);
				}
			}

			System.out.println("Test Passed");

		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason:" + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
