<<<<<<< HEAD
 package selenium;
=======
package selenium;
>>>>>>> master

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLogin {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
<<<<<<< HEAD
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("testemail@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Sewon12345");

			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(3000);
=======
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("muradil.erkin@boratechschool.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Boratech");

			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(2000);
>>>>>>> master

			// Validation
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			if (!titleText.equals("Dashboard")) {
				throw new Exception("Title Text doesn't match, Expected: Dashboard vs Actual: " + titleText);
			}

			String currentUrl = driver.getCurrentUrl();
			if (!currentUrl.endsWith("/dashboard")) {
				throw new Exception(
						"Page url is wrong, Expected url contains: /dashboard vs Actual url: " + currentUrl);
			}

			driver.findElement(By.xpath("//a[@href='#!']/span[text()='Logout']"));

			System.out.println("Test Passed");
<<<<<<< HEAD

=======
>>>>>>> master
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}
<<<<<<< HEAD
=======

>>>>>>> master
}
