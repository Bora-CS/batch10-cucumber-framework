package karma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KarmaAddEducationTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("kpaljor306@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Bora123");
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys("Bora Tech");
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys("Automation Engineer");
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Automation Engineering");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("06/08/2021");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("07/09/2023");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Test Automation Engineer, "
					+ "you will play a vital role in ensuring the smooth functioning and reliability of software "
					+ "applications through automated testing processes");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			driver.findElement(By.xpath("//a[@href='/']")).click();
			driver.findElement(By.xpath("//a[@href='#!']/span")).click();

			System.out.println("Pass");

		} catch (Exception e) {

			System.out.println("Fail");
		}

		finally {
			driver.quit();
		}
	}

}
