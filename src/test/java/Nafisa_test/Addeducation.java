package Nafisa_test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Addeducation {

	public static void main(String[] args) {
		

			WebDriver driver = new ChromeDriver();

			try {
				driver.get("http://boratech-practice-app.onrender.com/");
				driver.findElement(By.linkText("Login")).click();
				
				Thread.sleep(2000);
				
				
				String subtitleText = driver.findElement(By.xpath("//p[@class='lead']")).getText();
				System.out.println(subtitleText);
				
				driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nanfeise@gmail.com");
				driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Nafisa1996");
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				Thread.sleep(3000);
				String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
				if(!titleText.equals("Dashboard")) {
					throw new Exception("Title not match, va actual : " +titleText);
				}

				System.out.println("Test Passed");
				
				driver.findElement(By.xpath("//a[@href='/add-education']")).click();
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='school']")).sendKeys("James Madison University");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='degree']")).sendKeys("Bachelor degree");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Social Work ");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='from']")).sendKeys("01/05/2015");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys("12/05/2019");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Social workers typically do the following: Identify people and communities in need of help. Assess clients' needs, situations, strengths, and support networks to determine their goals. Help clients adjust to changes and challenges in their lives, such as illness, divorce, or unemployment.");
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@class='btn btn-primary my-1']")).click();
				Thread.sleep(2000);
				
				
				
				
			} catch (Exception e) {
				System.out.println("Test Failed");
				System.out.println("Reason: " + e.getMessage());
			} finally {
				driver.quit();
			}

	}}
