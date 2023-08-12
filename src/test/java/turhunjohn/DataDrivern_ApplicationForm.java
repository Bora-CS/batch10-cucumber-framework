package turhunjohn;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class DataDrivern_ApplicationForm {

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
//		test_3.put("dateofbirthday", "03/27/1979");
		test_3.put("hobbies", "Sports");
		test_3.put("Selectpicture", "no file choosen");
		test_3.put("CurrentAddress", "4663 Forestdale Dr");
		test_3.put("Selectstate", "VA");
		test_3.put("Selectcity", "Fairfax");

		submitApplicationForm(test_3);

	}

	public static void submitApplicationForm(HashMap<String, String> formData) {

		WebDriver localDriver = driverFactory();
		try {

			localDriver.get("https://demoqa.com/automation-practice-form");

			for (String key : formData.keySet()) {
				switch (key.toLowerCase()) {

				case "firstname":
//				enterFirstName();
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
					localDriver.findElement(By.id("subjectsInput")).sendKeys(formData.get(key));
					break;
				case "dateofbirthday":
					localDriver.findElement(By.id("dateOfBirthInput")).sendKeys(formData.get(key));
					break;
				case "hobbies":
					localDriver.findElement(By.xpath("//input[@type='checkbox']")).sendKeys(formData.get(key));
					break;
				case "Selectpicture":
					localDriver.findElement(By.xpath("//label[@for='uploadPicture']")).sendKeys(formData.get(key));
					break;
				case "CurrentAddress":
					localDriver.findElement(By.xpath("//textarea[@placeholder='Current Address']"))
							.sendKeys(formData.get(key));
					break;
				case "Selectstate":
					localDriver.findElement(By.xpath("//div[@class='col-md-4 col-sm-12'][1]"))
							.sendKeys(formData.get(key));
					break;
				case "Selectcity":
					localDriver.findElement(By.xpath("//div[@class='col-md-4 col-sm-12'][2]"))
							.sendKeys(formData.get(key));
					break;
				case "submit":
					localDriver.findElement(By.xpath("//button[@id='submit']")).click();
					break;

				case "gender":
					selectGender(localDriver, formData.get(key));

					String emailText = localDriver.findElement(By.id("userEmail-label")).getText();
					if (!emailText.equals("Email")) {
						throw new Exception(
								"EmailText doesn't match, Ecpected: [Email]  vs Actual " + "[" + emailText + "]");
					}

				}
			}

//	localDriver.findElement(By.id("submit")).click();
			jsClick(localDriver, By.id("submit"));
			Keywords.wait(4);

			Keywords.wait(2);
			System.out.println("Test Pass!");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			localDriver.close();
			localDriver.quit();
		}

	}

	private static void jsClick(WebDriver localDriver, By id) {
	 JavascriptExecutor executor = (JavascriptExecutor) localDriver;
     executor.executeScript("arguments[0].click();", localDriver.findElement(id));
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

	public static WebDriver driverFactory() {
		WebDriver driver = new ChromeDriver();
		return driver;

	}

}
