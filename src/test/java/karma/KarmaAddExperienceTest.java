
package karma;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class KarmaAddExperienceTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		try {

			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
		
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("kpaljor306@gmail.com");
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Bora123");
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.xpath("//input[@type='text']")).sendKeys("Automation Engineer");
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys("Bora Tech");
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys("Virginia");
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("06/06/2019");
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys("07/30/2023");
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Design, "
					+ "develop, and maintain automated test scripts using industry-standard "
					+ "testing tools and frameworks");
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
