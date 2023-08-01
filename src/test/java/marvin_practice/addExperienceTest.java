package marvin_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		try {
			driver.get("https://boratech-practice-app.onrender.com");
			driver.findElement(By.xpath("//li/a[@href='/login']")).click();


			driver.findElement(By.xpath("//input[@placeholder='Email Address']")).sendKeys("lopezmarvin678@gmail.com");
			driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("MlBb721031@");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			
			Thread.sleep(3000);
			
			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			driver.findElement(By.xpath("//input[@placeholder='* Job Title']")).sendKeys("Engineer");
			driver.findElement(By.xpath("//input[@placeholder='* Company']")).sendKeys("Ghost Tint");
			driver.findElement(By.xpath("//input[@placeholder='Location']")).sendKeys("Sterling Virginia");
			driver.findElement(By.xpath("//input[@type='date'][@name='from']")).sendKeys("08/19/2020");
			driver.findElement(By.xpath("//input[@type='checkbox']")).click();
			driver.findElement(By.xpath("//textarea[@placeholder='Job Description']")).sendKeys("working professional with years of experience");
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(5000);
//validation
			driver.findElement(By.xpath("//tbody/tr/td[text()='Ghost Tint']"));
			driver.findElement(By.xpath("//td[text()='Engineer']"));
			driver.findElement(By.xpath("//time[text()='2020/08/18']"));
			
			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}