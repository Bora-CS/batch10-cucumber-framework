package hui_automation;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StudentRegistrationFormPreTest {

	public static void main(String[] args) {
		HashMap<String, String> preTestData = new HashMap<>();
		submitForm(preTestData);

	} // main

	private static void submitForm(HashMap<String, String> formData) {
		WebDriver localDriver = driverFactory();
		try {
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			localDriver.manage().window().maximize();
			localDriver.get("https://demoqa.com/automation-practice-form");

			// using JavascriptExecutor to click the hidden element
			Testkeys.jsClick(localDriver, By.id("submit"));
			Testkeys.pause(1);
			
			System.out.println("Bad data =>");
			System.out.println("First name border color: "
					+ localDriver.findElement(By.id("firstName")).getCssValue("border-color"));
			System.out.println("Last name border color: "
					+ localDriver.findElement(By.id("lastName")).getCssValue("border-color"));
			System.out.println("Phone name border color: "
					+ localDriver.findElement(By.id("userNumber")).getCssValue("border-color"));
			System.out.println("Gender - Other label text color: "
					+ localDriver.findElement(By.xpath("//label[@for=\"gender-radio-3\"]")).getCssValue("color"));
			System.out.println("\nGood data =>\nEmail: "
					+ localDriver.findElement(By.id("userEmail")).getCssValue("border-color"));
			System.out.println("Date of Birth border color: "
					+ localDriver.findElement(By.id("dateOfBirthInput")).getCssValue("border-color"));
			
			System.out.println();
			findRequiredValidationResult(localDriver);
			findValidationResult(localDriver);
			System.out.println();

			System.out.println("Pre-Test passed.");
			Testkeys.pause(2);
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
		} finally {
			System.out.println("Pre-Test completed.");
			localDriver.close();
			localDriver.quit();
		}
	} // submit form

//	private static WebElement getWebElement(WebDriver driver, By locator) {
//		return driver.findElement(locator);
//	}
	
	private static void findRequiredValidationResult(WebDriver driver) {
		//check properties values
		String firstNameProperties = driver.findElement(By.id("firstName")).getDomProperty("validity");
		String firstNameValidStr = firstNameProperties.substring(firstNameProperties.indexOf("valid="));
		String firstNameValidResultStr = firstNameValidStr.substring(firstNameValidStr.indexOf("=") + 1, firstNameValidStr.indexOf(","));
		boolean firstNameValid = Boolean.parseBoolean(firstNameValidResultStr);
		System.out.println("Is first name valid: " + firstNameValid);

		String lastNameProperties = driver.findElement(By.id("lastName")).getDomProperty("validity");
		String lastNameValidStr = lastNameProperties.substring(lastNameProperties.indexOf("valid="));
		String lastNameValidResultStr = lastNameValidStr.substring(lastNameValidStr.indexOf("=") + 1, lastNameValidStr.indexOf(","));
		boolean lastNameValid = Boolean.parseBoolean(lastNameValidResultStr);
		System.out.println("Is last name valid: " + lastNameValid);
		
		String phoneNumberProperties = driver.findElement(By.id("userNumber")).getDomProperty("validity");
		String phoneNumberValidStr = phoneNumberProperties.substring(phoneNumberProperties.indexOf("valid="));
		String phoneNumberValidResultStr = phoneNumberValidStr.substring(phoneNumberValidStr.indexOf("=") + 1, phoneNumberValidStr.indexOf(","));
		boolean phoneNumberValid = Boolean.parseBoolean(phoneNumberValidResultStr);
		System.out.println("Is phone number valid: " + phoneNumberValid);
		
		String genderProperties = driver.findElement(By.id("gender-radio-3")).getDomProperty("validity");
		String genderValidStr = genderProperties.substring(genderProperties.indexOf("valid="));
		String genderValidResultStr = genderValidStr.substring(genderValidStr.indexOf("=") + 1, genderValidStr.indexOf(","));
		boolean genderValid = Boolean.parseBoolean(genderValidResultStr);
		System.out.println("Is gender - other valid: " + genderValid);
	}
	
	private static void findValidationResult(WebDriver driver) {
		String emailProperties = driver.findElement(By.id("userEmail")).getDomProperty("validity");
		String emailValidStr = emailProperties.substring(emailProperties.indexOf("valid="));
		String emailValidResultStr = emailValidStr.substring(emailValidStr.indexOf("=") + 1, emailValidStr.indexOf(","));
		boolean emailValid = Boolean.parseBoolean(emailValidResultStr);
		System.out.println("Is email valid: " + emailValid);
	}

	public static WebDriver driverFactory() {
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
