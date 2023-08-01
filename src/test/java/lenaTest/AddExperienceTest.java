package lenaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/login#!");

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("chenlena@outlook.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("798911");

			driver.findElement(By.xpath("//input[@type='submit']")).click();

			Thread.sleep(2000);

			// adding experience
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			Thread.sleep(2000);

			driver.findElement(By.name("title")).sendKeys("UX designer");
			Thread.sleep(2000);

			driver.findElement(By.name("company")).sendKeys("Russian School Of Mathmatics");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Ashburn, Virginia");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("01/25/2023");
			Thread.sleep(2000);

			driver.findElement(By.name("current")).click();
			Thread.sleep(2000);

			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(
					"Provide better experiences to target users by creating user stories,user-flow,prototype..etc");

			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000);

			String textMessage = driver.findElement(By.tagName("td")).getText();

			if (textMessage.equals("Russian School Of Mathmatics"))

				System.out.println("Test passed.");

		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}

}
