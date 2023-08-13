package krystal;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class selenium_0805 {

	public static void main(String[] args) {

		HashMap<String, String> test_1 = new HashMap<>();
		test_1.put("firstName", "John");
		submitApplicationForm(test_1);

		HashMap<String, String> test_2 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		submitApplicationForm(test_2);

		HashMap<String, String> test_3 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_2.put("userNumber", "111-222-3333");
		submitApplicationForm(test_3);

		HashMap<String, String> test_4 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_3.put("userNumber", "111-222-3333");
		test_4.put("subject", "mySelf");
		submitApplicationForm(test_4);

		HashMap<String, String> test_5 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_3.put("userNumber", "111-222-3333");
		test_4.put("subject", "mySelf");
		test_5.put("lastName", "Smith");
		submitApplicationForm(test_5);

		HashMap<String, String> test_6 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_3.put("userNumber", "111-222-3333");
		test_4.put("subject", "mySelf");
		test_5.put("lastName", "Smith");
		test_6.put("DOB", "08 Aug 2000");
		submitApplicationForm(test_6);

		HashMap<String, String> test_7 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_3.put("userNumber", "111-222-3333");
		test_4.put("subject", "mySelf");
		test_5.put("lastName", "Smith");
		test_6.put("DOB", "08 Aug 2000");
		test_7.put("Current Address", "Fairfax, VA");
		submitApplicationForm(test_7);

		HashMap<String, String> test_8 = new HashMap<>();
		test_1.put("firstName", "John");
		test_2.put("email", "test@t.com");
		test_3.put("userNumber", "111-222-3333");
		test_4.put("subject", "mySelf");
		test_5.put("lastName", "Smith");
		test_6.put("DOB", "08 Aug 2000");
		test_7.put("current address", "Fairfax, VA");
		test_8.put("hobbies", "Music");
		submitApplicationForm(test_8);
	}

	public static void submitApplicationForm(HashMap<String, String> formData) {

		WebDriver localDriver = driverFactory();

		try {

			localDriver.get("https://demoqa.com/automation-practice-form");

			for (String key : formData.keySet()) {
				switch (key.toLowerCase()) {

				case "firstname":
					localDriver.findElement(By.id("firstName")).sendKeys(formData.get(key));
					break;

				case "lastname":
					localDriver.findElement(By.id("lastName")).sendKeys(formData.get(key));
					break;

				case "email":
					localDriver.findElement(By.id("userEmail")).sendKeys(formData.get(key));
					break;

				case "phonenumber":
					localDriver.findElement(By.id("userNumber")).sendKeys(formData.get(key));
					break;

				case "subject":
					localDriver.findElement(By.id("subjectInput")).sendKeys(formData.get(key));
					break;

				case "dob":
					localDriver.findElement(By.xpath("//input[@id='dateOfBirthInput']")).sendKeys(formData.get(key));
					break;

				case "current address":
					localDriver.findElement(By.xpath("//textarea[@placeholder='Current Address']"));

				case "gender":
					selectGender(localDriver, formData.get(key));
					Thread.sleep(2000);

				case "hobbies":
					selectHobbies(localDriver, formData.get(key));
					Thread.sleep(2000);
				}
			}

			
			localDriver.findElement(By.id("submit")).click();
			System.out.println("passed");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("test failed");

		} finally {
		
			localDriver.close();
			localDriver.quit();
		}

	}

	private static void selectHobbies(WebDriver localDriver, String hobbies) {
		if (hobbies.equalsIgnoreCase("sports")) {
			localDriver.findElement(By.id("//label[@for='hobbies-checkbox-1']1")).click();
		} else if (hobbies.equalsIgnoreCase("reading")) {
			localDriver.findElement(By.id("//label[@for='hobbies-checkbox-2']")).click();
		} else {
			localDriver.findElement(By.id("//label[@for='hobbies-checkbox-3']")).click();
		}
	}
		
		

	public static void selectGender(WebDriver driver, String gender) {
		if (gender.equalsIgnoreCase("male")) {
			driver.findElement(By.id("gender-radio-1")).click();
		} else if (gender.equalsIgnoreCase("female")) {
			driver.findElement(By.id("gender-radio-2")).click();
		} else {
			driver.findElement(By.id("gender-radio-3")).click();
		}
	}
	
	
	

	static void jsClick(WebDriver driver, By by) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;

		executor.executeScript("arguments[0].click();", driver.findElement(by));
	}

	public static WebDriver driverFactory() {

		WebDriver driver = new ChromeDriver();
		return driver;

	}
}
