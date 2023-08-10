package krystal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class kyrstalApplicationForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String firstName = "Krystal";
		String middleName = "Hyejinlee";
		String lastName = "Om";
		String DOB = "01/13/1993";
		String genderMale = "Male";
		String genderFemale = "Female";
		String genderOther = "Other";
		String email = "kyrstal.lee.om@gmail.com";
		String phoneNumber = "571-420-1515";

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
			Keywords.wait(2);
			driver.findElement(By.xpath("//*[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='middlename']")).sendKeys(middleName);
			
			
			driver.findElement(By.xpath("//*[@name='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@type='date']")).sendKeys(DOB);
			driver.findElement(By.xpath("//input[@value='female']")).click();
			
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phoneNumber);

			
			Keywords.wait(3);

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


//if (genderMale == "Male") {
//	driver.findElement(By.xpath("//input[@value='male']")).click();
//}
//
//if (genderFemale == "Female") {
//	driver.findElement(By.xpath("//input[@value='female']")).click();
//	if (genderOther == "Other") {
//		driver.findElement(By.xpath("//input[@value='male']")).click();
//	}
//		
//	}else {
//		driver.findElement(By.xpath("//input[@value='other']")).click();
