package turhunjohn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class minimals {

	public static void main(String[] args) {
		
     WebDriver driver= new ChromeDriver();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5) );
     driver.manage().window().maximize();
     
     String username="demo@minimals.cc";
     String password="demo1234";
	
	try {
		driver.get("https://minimals.cc/auth/amplify/login/");
		Keywords.wait(1);
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		
		Keywords.wait(2);
		
		
		System.out.println(" Test pass");
		
	}
	
	catch(Exception e) {
		e.printStackTrace();
		System.out.println(" Test failed");
		System.out.println("Reason: " + e.getMessage());
	}
      finally {
    	  driver.quit();
      }
}
}