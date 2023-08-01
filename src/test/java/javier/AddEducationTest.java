package javier;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8000));

		try {
			// Login
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("javierlopez5037@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("752161jJ");
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();

			// Waiting until the add education button is visible
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/add-education']"))).click();
			// Add Education
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys("Bora Tech");
			driver.findElement(By.xpath("//input[@name='degree']"))
					.sendKeys("Bora Tech Advanced Automaiton Testing degree");
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Automation Testing");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("05/06/2023");
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//textarea[@name='description']"))
					.sendKeys("Fluent in Java, Proficient in Selenium");
			driver.findElement(By.xpath("//input[@type='submit']")).click();

			// Wait until back on dashboard to start validation
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Dashboard']")));
			// Validation
			driver.findElement(By.xpath("//td[text()='Bora Tech Advanced Automaiton Testing degree']"));
			driver.findElement(By.xpath("//td [text()='Bora Tech']"));

			System.out.println("Test passed");
		} catch (Exception e) {
			System.out.println("Test failed");
		} finally {
			driver.quit();
		}

	}

}
