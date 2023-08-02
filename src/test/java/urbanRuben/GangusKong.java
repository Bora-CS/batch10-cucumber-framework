package urbanRuben;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GangusKong {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		String username = "";
		String email = "rubenmendozabri@gmail.com";
		String password = "12345qwerT!";

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);

			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);

			driver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			System.out.println("test failed");
			System.out.println("reason: " + e.getMessage());
		} finally {
			
			
			driver.quit();
			
		} 

	}

}
