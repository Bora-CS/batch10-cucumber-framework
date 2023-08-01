package anthony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("anth0ny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("PaSsWoRd123!");
			driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
			Thread.sleep(5000);

			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();

			if (!titleText.equals("Dashboard")) {
				throw new Exception("Title text did not match. "
						+ "\nDashboard text should be: " + titleText);
				
			}

			driver.findElement(By.xpath("//*[@href='/add-education']")).click();

			String educationIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']")).getText();

			if (!educationIntro.equals("Add Your Education")) {
				throw new Exception("Education text did not match. "
						+ "\nEducation text should be: " +educationIntro);
			}

			driver.findElement(By.name("school")).sendKeys("Bora University");
			driver.findElement(By.name("degree")).sendKeys("Bachelor");
			driver.findElement(By.name("fieldofstudy")).sendKeys("Test Automation Engineer");
			driver.findElement(By.xpath("//*[@name='from']")).sendKeys("01-01-2023");
			driver.findElement(By.xpath("//*[@name='to']")).sendKeys("12-12-2023");
			driver.findElement(By.xpath("//*/textarea[@name='description']"))
					.sendKeys("I can sysout you a triangle ▵ or a diamond  ◇ ");
			driver.findElement(By.xpath("//*/input[@type='submit']")).click();

			Thread.sleep(5000);

			System.out.println("Test passed");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Something went wrong!!!: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}

}
