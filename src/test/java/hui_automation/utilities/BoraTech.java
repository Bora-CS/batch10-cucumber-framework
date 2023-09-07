package hui_automation.utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BoraTech {

	public static void login(WebDriver driver, String email, String password) {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Testkeys.waitUtilURL_Contains(driver, "dashboard", 10);
	}

}
