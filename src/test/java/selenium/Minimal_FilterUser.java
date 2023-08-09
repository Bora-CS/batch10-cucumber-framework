package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class Minimal_FilterUser {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		String username = "demo@minimals.cc";
		String password = "demo1234";

		try {
			driver.get("https://minimals.cc/auth/amplify/login");

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

			driver.findElement(By.xpath("//span[text()='user']")).click();
			driver.findElement(By.xpath("//a[@href='/dashboard/user/list']")).click();

			driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]//span[text()='Role']/../../.."))
					.click();

			driver.findElement(By.xpath("//li[@data-value='UX/UI Designer']")).click();
			driver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();

			driver.findElement(By.tagName("body")).click();

			Keywords.wait(2);

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
