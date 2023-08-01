package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLogin {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://boratech-practice-app.onrender.com/login#!");
		
		driver.findElement(By.name("email")).sendKeys();
		

		
	}

}
