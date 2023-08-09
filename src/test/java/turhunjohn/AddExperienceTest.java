package turhunjohn;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";
		String jobTitle = "Information Security Officer";
		String company = "AWS";
		String location = "Chantilly, VA ";
		String from = "10/05/2021";
		String to = "10/14/2022 ";
		boolean current = false;
		String description = "Conduct inspections, audits and reviews in your area of responsibility";

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

			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			wait(5);

			String experienceIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']"))
					.getText();
			if (!experienceIntro.equals("Add An Experience")) {
				throw new Exception(
						" An Experience text doesn't match." + "\n An Experience text should be: " + experienceIntro);
			}

			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
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
