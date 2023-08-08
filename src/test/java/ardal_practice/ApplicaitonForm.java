package ardal_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class ApplicaitonForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String firstName = "Ardal";
		String lastName = "Akbar";
		String dob = "10/27/1994";
		String email = "ardal123@gg.com";
		String phone = "7787787788";

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

			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
			driver.findElement(By.xpath("//*[@name='dob']")).sendKeys(dob);
			driver.findElement(By.xpath("//input[@value='male']")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys(phone);
			driver.findElement(By.xpath("//*[@name='course']")).click();
			driver.findElement(By.xpath("//option[@value='sdet']")).click();
			driver.findElement(By.xpath("//*[@name='source']")).click();
			driver.findElement(By.xpath("//option[contains(text(),'Ref')]")).click();
			driver.findElement(By.xpath("//*[@name='notarobot']")).click();

			driver.findElement(By.xpath("//*[@value='Submit Application']")).click();
			Keywords.wait(1);

			String expectedAlert = "Your application is submitted!";

			String actualAlert = driver.findElement(By.xpath("//*[@class='alert alert-success']")).getText();

			if (!expectedAlert.equals(actualAlert)) {
				throw new Exception(
						"Alert message doesn't match \n  Expected: " + expectedAlert + "\nActual:" + actualAlert);
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
