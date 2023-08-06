package hui_automation;

import java.time.Duration;
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StudentRegistrationFormTest {

	public static void main(String[] args) {
		// data set 1
		System.out.println("Test 1 =>");
		HashMap<String, String> testData1 = new HashMap<>();
		testData1.put("lastName", "Smith");
		submitForm(testData1);

		// data set 2
		System.out.println("Test 2 =>");
		HashMap<String, String> testData2 = new HashMap<>();
		testData2.put("firstName", "John");
		testData2.put("phoneNumber", "123456");
		submitForm(testData2);

		// data set 3
		System.out.println("Test 3 =>");
		HashMap<String, String> testData3 = new HashMap<>();
		testData3.put("firstName", "John");
		testData3.put("lastName", "Smith");
		testData3.put("phoneNumber", "1234569999");
		testData3.put("gender", "Male");
		submitForm(testData3);

//		HashMap<String, String> testData4 = new HashMap<>();
//		testData4.put("gender", "Male");
//		testData4.put("firstName", "John");
//		testData4.put("lastName", "Smith");
//		testData4.put("email", "john.smith@somemail.com");
//		testData4.put("phoneNumber", "1234569999");
//		testData4.put("subjects", "Biology");
//		testData4.put("dob", "12/25/2007");
//		subitRegistrationForm(testData4);

	}

	private static void submitForm(HashMap<String, String> formData) {
		WebDriver localDriver = driverFactory();
		try {
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			localDriver.manage().window().maximize();
			localDriver.get("https://demoqa.com/automation-practice-form");

			// sending data
			for (String dataKey : formData.keySet()) {
				switch (dataKey.toLowerCase()) {
//				case "dob": //dateOfBirthInput
//					String dobInputStr = formData.get(dataKey);
//					getWebElement(localDriver, By.id("dateOfBirthInput")).sendKeys(getDobInputDMY(dobInputStr, "MM/dd/uuuu"));
//					break;
				case "gender":
					selectGender(localDriver, formData.get(dataKey));
					break;
				case "firstname": // firstName
					getWebElement(localDriver, By.id("firstName")).sendKeys(formData.get(dataKey));
					break;
				case "lastname": // lastName
					getWebElement(localDriver, By.id("lastName")).sendKeys(formData.get(dataKey));
					break;
				case "email": // userEmail
					getWebElement(localDriver, By.id("userEmail")).sendKeys(formData.get(dataKey));
					break;
				case "phonenumber": // userNumber
					getWebElement(localDriver, By.id("userNumber")).sendKeys(formData.get(dataKey));
					break;
				case "subjects": // subjectsInput
					getWebElement(localDriver, By.id("subjectsInput")).sendKeys(formData.get(dataKey));
					break;
				default:
					break;
				}
			}
			Testkeys.pause(1);

			// using JavascriptExecutor to click the hidden element
			Testkeys.jsClick(localDriver, By.id("submit"));
			Testkeys.pause(2);

			// validation
			if (!Testkeys.containsElement(localDriver, By.xpath("//div[@class=\"modal-dialog modal-lg\"]"))) {
				throw new Exception("Expected success alert message is not visible.");
			}

			String realMessage = localDriver.findElement(By.id("example-modal-sizes-title-lg")).getText();
			System.out.println("Test passed.");
			System.out.println(realMessage);
			Testkeys.pause(2);
		} catch (Exception e) {
			System.out.println("Test failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			System.out.println("Test completed.");
			localDriver.close();
			localDriver.quit();
		}
	}

	private static void selectGender(WebDriver driver, String gender) {
		if (gender.equalsIgnoreCase("male")) {
			WebElement male = driver.findElement(By.xpath("//label[@for='gender-radio-1']"));
			male.click();
		} else if (gender.equalsIgnoreCase("female")) {
			WebElement female = driver.findElement(By.xpath("//label[@for='gender-radio-2']"));
			female.click();
		} else {
			WebElement other = driver.findElement(By.xpath("//label[@for='gender-radio-3']"));
			other.click();
		}
	}

	private static WebElement getWebElement(WebDriver driver, By locator) {
		return driver.findElement(locator);
	}

//	private static void selectDob(WebDriver driver,String dateStr, String datePattern) {
//		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(datePattern));
//		
//	}

	public static WebDriver driverFactory() {
		WebDriver driver = new ChromeDriver();
		return driver;
	}

}
