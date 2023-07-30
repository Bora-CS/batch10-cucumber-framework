package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraLoginSelenium {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("javierlopez5037@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("752161jJ");
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
			Thread.sleep(5000);

			// Validation
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception("The title text does not match, Expected Dashboard vs Actual: " + titleText);
			}
			String currentUrlSuffex = driver.getCurrentUrl();
			if (!currentUrlSuffex.endsWith("/dashboard")) {
				throw new Exception("The current Url should end with /dashboard vs current:" + currentUrlSuffex);
			}

			driver.findElement(By.xpath("//a[@href='#!']/span[text()='Logout']"));
			
			System.out.println("Test Passed");

		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason:" + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
