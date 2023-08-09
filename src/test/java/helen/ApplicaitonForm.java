package helen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Keywords;

public class ApplicaitonForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		
		String firstName = "helen";
		String middleName = "h";
		String lastName = "ahn";
		String dob = "06/10/2021";
		String gender = "female";
		String email = "helenhjahn@gmail.com";
		String phoneNumber= "202-2022-2023";
		String referredBy = "Alice";
		
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
			
			//inputs
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='middlename']")).sendKeys(middleName);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(dob);
			driver.findElement(By.xpath("//input[@name='gender'][@value='" + gender.toLowerCase() + "']")).click();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phoneNumber);
		
			Select course = new Select(driver.findElement(By.xpath("//select[@name='course']")));
			course.selectByIndex(1);
			//course.selectByValue("sdet");
			//course.selectByVisibleText("Software Development Engineer in Test - Java & Selenium");
			Select source = new Select (driver.findElement(By.xpath("//select[@name='source']")));
			source.selectByValue("internalreferral");
			driver.findElement(By.xpath("//input[@name='referredby']")).sendKeys(referredBy);
			WebElement submitButton = driver.findElement(By.xpath("//input[@type='submit']"));
			
			
			//make sure the submit button is disabled
		
			if (submitButton.isEnabled()) {
				throw new Exception("Submit button should be disabled");
			}
			
			driver.findElement(By.xpath("//input[@name='notarobot']")).click();
			
			if (!submitButton.isEnabled()) {
				throw new Exception("Submit button should be enabled");
			}
		
			
			submitButton.click();
			
			Keywords.wait(2);
			
//			
//			String successAlertText = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();
//			String expectedAlertText = "Your submission is successful";
//			if (successAlertText.equals(expectedAlertText)) {
//				throw new Exception("Success alert text mismatch.\nExpected: " + expectedAlertText + "\nActual: " + successAlertText);
//			}
//			
//			
		
		
			

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
