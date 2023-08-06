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
		HashMap<String, String> testData1 = new HashMap<>();
		testData1.put("lastName", "Smith");
		submitForm(testData1);

//		HashMap<String, String> testData2 = new HashMap<>();
//		testData2.put("lastName", "Smith");
//		testData2.put("userEmail", "john.smith@somemail.com");
//		subitRegistrationForm(testData2);

//		HashMap<String, String> testData3 = new HashMap<>();
//		testData3.put("firstName", "John");
//		testData3.put("userEmail", "john.smith@somemail.com");
//		testData3.put("subjects", "Biology");
//		subitRegistrationForm(testData3);

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
			
			// check css values
			System.out.println("Before: " + localDriver.findElement(By.id("lastName")).getCssValue("border"));
			
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
			Testkeys.pause(3);

			// using JavascriptExecutor to click the hidden element
			Testkeys.jsClick(localDriver, By.id("submit"));

			
			// result page
			// validation
//			Testkeys.pause(5);
//			if (!Testkeys.containsElement(localDriver, By.id("example-modal-sizes-title-lg"))) {
//				throw new Exception("Expected success pop-up message not visible.");
//			}
//			String realMessage = localDriver.findElement(By.id("example-modal-sizes-title-lg")).getText();
			System.out.println("After: " + localDriver.findElement(By.id("lastName")).getCssValue("border"));
			
//			System.out.println("Test passed.");
//			System.out.println(realMessage);
			Testkeys.pause(3);
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			e.printStackTrace();
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
