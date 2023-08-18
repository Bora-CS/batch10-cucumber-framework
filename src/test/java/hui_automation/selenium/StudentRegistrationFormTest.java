package hui_automation.selenium;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import hui_automation.utilities.Testkeys;

public class StudentRegistrationFormTest {

	public static void main(String[] args) {
		// data set 1
		HashMap<String, String> testData1 = new HashMap<>();
		testData1.put("lastName", "Smith");
		submitForm(testData1, 1);

		// data set 2
		HashMap<String, String> testData2 = new HashMap<>();
		testData2.put("firstName", "John");
		testData2.put("phoneNumber", "123456");
		submitForm(testData2, 2);

		// data set 3
		HashMap<String, String> testData3 = new HashMap<>();
		testData3.put("firstName", "John");
		testData3.put("lastName", "Smith");
		testData3.put("phoneNumber", "1234569999");
		testData3.put("gender", "Male");
		submitForm(testData3, 3);

		// data set 4
		HashMap<String, String> testData4 = new HashMap<>();
		testData4.put("gender", "Male");
		testData4.put("firstName", "John");
		testData4.put("lastName", "Smith");
		testData4.put("email", "john.smith@somemail.com");
		testData4.put("phoneNumber", "1234569999");
		testData4.put("subjects", "Biology");
		testData4.put("dob", "12/25/1999");
		submitForm(testData4, 4);

	}

	private static void submitForm(HashMap<String, String> formData, int testNumber) {
		WebDriver localDriver = Testkeys.getChromeDriver();
		System.out.printf("Test %d =>%n", testNumber);
		try {
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			localDriver.manage().window().maximize();
			localDriver.get("https://demoqa.com/automation-practice-form");

			// sending data
			for (String dataKey : formData.keySet()) {
				switch (dataKey.toLowerCase()) {
				case "dob": // dateOfBirthInput
					selectDob(localDriver, formData.get(dataKey), "MM/dd/uuuu");
					break;
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
			Testkeys.pause(3);
		} catch (Exception e) {
			System.out.println("Test failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			System.out.println("Test completed.");
			System.out.println();
			Testkeys.terminate(localDriver);
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

	private static void selectDob(WebDriver driver, String dateStr, String datePattern) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(datePattern));
		int year = date.getYear();
		int month = date.getMonthValue();
		int day = date.getDayOfMonth();

		driver.findElement(By.id("dateOfBirthInput")).click();
		Select yearSelect = new Select(driver.findElement(By.className("react-datepicker__year-select")));
		yearSelect.selectByVisibleText("" + year);
		Select monthSelect = new Select(driver.findElement(By.className("react-datepicker__month-select")));
		monthSelect.selectByValue("" + (month - 1));
		String dayOfWeekStr = date.format(DateTimeFormatter.ofPattern("EEEE"));
		String monthOfYearStr = date.format(DateTimeFormatter.ofPattern("MMMM"));
		String dayXpath = "//div[contains(@aria-label, '" + dayOfWeekStr + ", " + monthOfYearStr + " " + day + "')]";
		driver.findElement(By.xpath(dayXpath)).click();
	}

}
