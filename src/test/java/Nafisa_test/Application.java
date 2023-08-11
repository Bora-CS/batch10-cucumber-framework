package Nafisa_test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class Application {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		try {
			driver.get("http://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
			
			Keywords.wait(2);
			
			driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Nanfeise");
			Keywords.wait(2);
			driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Xiaokaiti");
			Keywords.wait(2);
			driver.findElement(By.xpath("//input[@name='dob']")).sendKeys("02/06/1996");
			Keywords.wait(2);
			driver.findElement(By.xpath("//input[@value='female']")).click();
			Keywords.wait(2);
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys("nanfeise@gmail.com");
			driver.findElement(By.xpath("//input[@name='phonenumber']")).sendKeys("270-438-0603");
			Keywords.wait(2);
			driver.findElement(By.xpath("//*[@name='course']")).click();
			driver.findElement(By.xpath("//option[@value='sdet']")).click();
			Keywords.wait(2);
			driver.findElement(By.xpath("//*[@name='source']")).click();
			Keywords.wait(2);
			driver.findElement(By.xpath("//option[@value='internalreferral']")).click();
			Keywords.wait(2);
			driver.findElement(By.xpath("//*[@name='notarobot']")).click();
			Keywords.wait(2);
			
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}




	}

}
