package mohssin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducation {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		
		String username = "username";
		String password = "password";

		try {
			driver.get("https://boratech-practice-app.onrender.com");
			
			driver.findElement(By.linkText("Login")).click();
			
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[@href='/add-education']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@name='school']")).sendKeys("BoraTech");
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//input[@name='degree']")).sendKeys("Test Automation Engineer");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("IT");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//input[@name='from']")).sendKeys("05/01/2023");
			Thread.sleep(2000);
	
			
			// Assuming driver is already initialized

			// Locate the checkbox element
			WebElement checkBoxElement = driver.findElement(By.xpath("//*[@type='checkbox']"));

			// Check if the checkbox is enabled
			boolean isEnabled = checkBoxElement.isEnabled();

			// performing click operation if the checkbox is enabled
			if (isEnabled) {
			    checkBoxElement.click();
			}

			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("SDLC, STLC, Agile Scrum, Manual Testing, Core Java, Basic HTML, CSS, SQL, Basic Command Line & Terminal , Selenium WebDriver, Git & GitHub, Maven, Cucumber & Gherkin, Automation Frameworks\n"
					+ "");
			Thread.sleep(2000);

			driver.findElement(By.xpath("//*[@type='submit']")).click();
			Thread.sleep(2000);
			
			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}