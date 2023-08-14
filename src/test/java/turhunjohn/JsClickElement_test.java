package turhunjohn;

import java.time.Duration;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class JsClickElement_test {

	public static void main(String[] args) {

		HashMap<String, String> test_1 = new HashMap<>();
		test_1.put("lastName", "John");
//		submitApplicationForm(test_1);

		HashMap<String, String> test_2 = new HashMap<>();
		test_2.put("firstName", "John");
		test_2.put("email", "test@t.com");
//		submitApplicationForm(test_2);

		HashMap<String, String> test_3 = new HashMap<>();
		test_3.put("firstName", "John");
		test_3.put("email", "test@t.com");
		test_3.put("gender", "male");
		test_3.put("phonenumber", "703-222-2222");
		test_3.put("subject", "IT");
		test_3.put("hobbies", "Sports");
		test_3.put("Selectpicture", "no file choosen");
		test_3.put("CurrentAddress", "4663 Forestdale Dr");
		test_3.put("Selectstate", "VA");
		test_3.put("Selectcity", "Fairfax");

		submitApplicationForm(test_3);

	}

	public static void submitApplicationForm(HashMap<String, String> formData) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		try {

			driver.get("https://demoqa.com/automation-practice-form");

			for (String key : formData.keySet()) {
				switch (key.toLowerCase()) {

				case "firstname":
//				enterFirstName();
					driver.findElement(By.id("firstName")).sendKeys(formData.get(key));
					break;
				case "lastname":
					driver.findElement(By.id("lastName")).sendKeys(formData.get(key));
					break;
				case "email":
					driver.findElement(By.id("userEmail")).sendKeys(formData.get(key));
					break;
				case "phonenumber":
					driver.findElement(By.id("userNumber")).sendKeys(formData.get(key));
					break;
				case "subject":
					driver.findElement(By.id("subjectsInput")).sendKeys(formData.get(key));
					break;
				case "dateofbirthday":
					driver.findElement(By.id("dateOfBirthInput")).sendKeys(formData.get(key));
					break;
				case "hobbies":
					driver.findElement(By.xpath("//input[@type='checkbox']")).sendKeys(formData.get(key));
					break;
				case "Selectpicture":
					driver.findElement(By.xpath("//label[@for='uploadPicture']")).sendKeys(formData.get(key));
					break;
				case "CurrentAddress":
					driver.findElement(By.xpath("//textarea[@placeholder='Current Address']"))
							.sendKeys(formData.get(key));
					break;
				case "Selectstate":
					driver.findElement(By.xpath("//div[@class='col-md-4 col-sm-12'][1]")).sendKeys(formData.get(key));
					break;
				case "Selectcity":
					driver.findElement(By.xpath("//div[@class='col-md-4 col-sm-12'][2]")).sendKeys(formData.get(key));
					break;
				case "submit":
					driver.findElement(By.xpath("//button[@id='submit']")).click();
					break;

				case "gender":
					selectGender(driver, formData.get(key));

					String emailText = driver.findElement(By.id("userEmail-label")).getText();
					if (!emailText.equals("Email")) {
						throw new Exception(
								"EmailText doesn't match, Ecpected: [Email]  vs Actual " + "[" + emailText + "]");
					}

				}
			}

//	localDriver.findElement(By.id("submit")).click();
			jsClick(driver, By.id("submit"));
			Keywords.wait(4);

			Keywords.wait(2);
			System.out.println("Test Pass!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Test failed");
			System.out.println(" Reason :" + e.getMessage());
		} finally {
			driver.quit();
		}
	}

	private static void jsClick(WebDriver driver, By id) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", driver.findElement(id));
	}

	private static void selectGender(WebDriver driver, String gender) {
		if (gender.equalsIgnoreCase("male")) {
			driver.findElement(By.xpath("//*[@for='gender-radio-1']")).click();
		} else if (gender.equalsIgnoreCase("female")) {
			driver.findElement(By.xpath("//*[@for='gender-radio-2']")).click();
		} else {
			driver.findElement(By.xpath("//*[@for='gender-radio-3']")).click();
		}
	}
}
