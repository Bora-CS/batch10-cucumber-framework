package lenaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Keywords;

public class ApplicationForm {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		String firstName = "Lena";
		String lastName = "Chen";
		String dob = "07/01/1995";
		String email = "chenlena@outlook.com";
		String phoneNumber = "571-777-777";

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

			
			// Input information
			driver.findElement(By.xpath("//input[@type ='text']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@placeholder='* Last Name']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(dob);
			driver.findElement(By.xpath("//input[@value='female']")).click();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phoneNumber);
			Keywords.wait(2);
			Select course = new Select(driver.findElement(By.xpath("//select[@name='course']")));
			course.selectByIndex(3);
//          course.selectByValue("aws");
//          course.selectByVisibleText("");
			Select source = new Select(driver.findElement(By.xpath("//select[@name='source']")));
			source.selectByIndex(2);
			driver.findElement(By.xpath("//input[@name='notarobot']")).click();
			Keywords.wait(2);

			
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
