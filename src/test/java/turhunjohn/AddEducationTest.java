package turhunjohn;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";
		String school = "National Pirogov Memorial Medical University, Vinnytsya";
		String degree = "Master of Science";
		String fieldofstudy = "Urology";
		String from = "08/20/2006";
		String to = "10/05/2008 ";
		boolean current = false;
		String description = "“Learning urology can be challenging, because it's both medicine and surgery";

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			// driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//a[@class='btn btn-light']")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			wait(5);

			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();

			if (!titleText.equals("Dashboard")) {
				throw new Exception(
						"Title text doesn't match, Ecpected: [Dashboard]  vs Actual " + "[" + titleText + "]");
			}

			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			wait(5);

			String educationIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']")).getText();
			
			if (!educationIntro.equals("Add Your Education")) {
				throw new Exception("Education text doesn't match, Ecpected: [Add Your Education] vs Actual " + "["
						+ educationIntro + "]");
			}

			driver.findElement(By.xpath("//input[@name='school']")).sendKeys(school);
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(degree);
			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(fieldofstudy);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			
			if (current) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
				driver.findElement(By.tagName("textarea")).sendKeys(description);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				wait(5);

				System.out.println("Test Passed");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}
}
