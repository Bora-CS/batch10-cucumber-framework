package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class ApplicaitonForm {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

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

			driver.findElement(By.name("firstname")).sendKeys("Xiaoli");
			driver.findElement(By.name("lastname")).sendKeys("Liang");
			driver.findElement(By.name("dob")).sendKeys("08/03/2022");
			driver.findElement(By.xpath("//input[@value='female']")).click();
			driver.findElement(By.xpath("//input[@name='email']")).click();
			
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
