package Nafisa_test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateProfile {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
			System.out.println(titleText);
			Thread.sleep(2000);
			
			driver.findElement(By.name("email")).sendKeys("nanfeise@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Nafisa1996");
			driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath("//a[@class='btn btn-light']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//select[@name=\"status\"]/option[text()=\"SDET\"]\n"
					+ "")).click();
			
			driver.findElement(By.name("company")).sendKeys("BoraTech");
			driver.findElement(By.name("website")).sendKeys("https://www.boratechschool.com/");
			driver.findElement(By.name("location")).sendKeys("Annandale va");
			driver.findElement(By.name("skills")).sendKeys("SQL,HTML,JAVA,CSS");
			driver.findElement(By.name("githubusername")).sendKeys("Nanfeise");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//input[@class='btn btn-primary my-1']")).click();
			
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}

	}



	}


