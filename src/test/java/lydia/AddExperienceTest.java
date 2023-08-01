package lydia;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.name("email")).sendKeys("w.lydia.liu@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Liu123456");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			Thread.sleep(2000);

			driver.findElement(By.cssSelector("a[href='/add-experience']")).click();
			driver.findElement(By.cssSelector("input[name='title']")).sendKeys("Automation Testing Engineer");
			driver.findElement(By.cssSelector("input[name='company']")).sendKeys("BoraTech");
			driver.findElement(By.cssSelector("input[name='location']")).sendKeys("Annandale, VA");
			Thread.sleep(1000);
			driver.findElement(By.name("from")).sendKeys("01/04/2019");

			driver.findElement(By.name("current")).click();

			driver.findElement(By.cssSelector("textarea[name='description']"))
					.sendKeys("I've been doing automation testing with selenium. "
							+ "The job requires strong testing skills and some coding skills.");

			Thread.sleep(1000);
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			Thread.sleep(1000);

			System.out.println("Test Passed");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test failed");
		} finally {
			driver.quit();
		}
	}

}

