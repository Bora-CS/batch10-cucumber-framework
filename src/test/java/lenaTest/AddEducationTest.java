package lenaTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/login#!");

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("chenlena@outlook.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("798911");

			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);

			// adding education
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.name("school")).sendKeys("Bora Tech");
			driver.findElement(By.name("degree")).sendKeys("Bachelor's Degree");
			driver.findElement(By.name("fieldofstudy")).sendKeys("Test Automation Engineer");
			driver.findElement(By.name("from")).sendKeys("5/8/2023");
			driver.findElement(By.name("current")).click();
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Quality Assurance");

			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);

			// validation
			String textMessage = driver.findElement(By.tagName("td")).getText();

			if (textMessage.equals("Bora Tech"))
				System.out.println("Test passed.");

		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}

}
