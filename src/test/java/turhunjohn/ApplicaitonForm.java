package turhunjohn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import utilities.Keywords;

public class ApplicaitonForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String FirstNmae = "Turhun";
		String MiddleNmae = "john";
		String LastNmae = "Juma";
		String Dob = "03/27/1979";
		String Gender = "Male";
		String EmailAddress = "turhunjuma@gmail.com";
		String PhoneNumber = "5718888888";
		String Course = "Software development engineer in test";
		String HearAbout= "Other";
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
			Keywords.wait(2);
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys(FirstNmae);
			driver.findElement(By.xpath("//input[@placeholder='Middle Name']")).sendKeys(MiddleNmae);
			driver.findElement(By.xpath("//input[@placeholder='* Last Name']")).sendKeys(LastNmae);
			driver.findElement(By.xpath("//input[@name='dob']")).sendKeys(Dob);
			driver.findElement(By.xpath("//input[@name='gender'][@value='male']")).click();
			driver.findElement(By.xpath("//input[@type='email']")).sendKeys(EmailAddress);
			driver.findElement(By.xpath("//input[@placeholder='* Phone Number']")).sendKeys(PhoneNumber);
			
			Select course= new Select(driver.findElement(By.xpath("//select[@name='course']")));
			course.selectByVisibleText("Software Development Engineer in Test - Java & Selenium");
			
			driver.findElement(By.tagName("h5")).click();
			driver.findElement(By.xpath("//input[@type='submit']")).getText();
			
			
			
			
			
//			String expectedTitleTexting = "Submit Application";
//			String actualTitleTexting= driver.findElement(By.xpath("//input[@class='btn btn-primary']")).getText();
//			if (!actualTitleTexting.equals(expectedTitleTexting)) {
//				throw new Exception(
//				 "Title Text doesn't match.\nExpected: " + expectedTitleTexting + "\nActual: " + actualTitleTexting);
//			}
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
