package karmap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KarmaAddEducationTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("kpaljor306@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Bora123");

			Thread.sleep(5000);
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			driver.findElement(By.xpath("//a[@href='#!']/span"));

			System.out.println("Pass");

		} catch (Exception e) {

			System.out.println("Fail");
		}

		finally {
			driver.quit();
		}
	}

}
