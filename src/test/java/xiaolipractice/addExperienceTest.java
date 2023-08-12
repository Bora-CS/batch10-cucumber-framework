package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addExperienceTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "xiaoli1005@yahoo.com";
		String password = "Goodbora425!";
		String jobTitle = "Automation Testing Engineer ";
		String company = "Boratech";
		String location = "Annandale, VA";
		String from = "01/01/2021";
		String to = "";
		boolean current = true;
		String description = "It's a fun job!";

		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);

			wait(5);

			driver.findElement(By.xpath("//a[@href='add-experience']")).click();
			wait(2);

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='lacation']")).sendKeys(location);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);

			if (current) {
				driver.findElement(By.xpath("//input[@aname='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
			}
			driver.findElement(By.tagName("textarea")).sendKeys(description);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait(2);

		} catch (Exception e) {
			System.out.println("Test Failed");
			e.printStackTrace();
			System.out.println("Reason:" + e.getMessage());

		} finally {
			driver.close();
			driver.quit();
		}

	}

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}

}
