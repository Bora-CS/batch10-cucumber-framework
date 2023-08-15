package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class ApplicaitonForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		
		
		String firstName = "ruben";
		String phoneNumber = "5715386915";
		String lastName = "mendoza";
		String email = "rubenmendozabri@gmail.com";
		//					//*[text()='Apply Now']"
		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
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
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phoneNumber);
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
			Keywords.wait(3);
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
