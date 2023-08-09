package hui_automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AutomationTestPractice0808 {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		try {
			String testUrl = "https://minimals.cc/";
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			testDriver.manage().window().maximize();
			testDriver.get(testUrl);
			
			// login
			testDriver.findElement(By.xpath("//a[@href='/dashboard'][text()='Login']")).click();
			
			String email = testDriver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[1]")).getText();
			String password = testDriver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[2]")).getText();
			
			testDriver.findElement(By.name("email")).sendKeys(email);
			testDriver.findElement(By.name("password")).sendKeys(password);
			
			testDriver.findElement(By.xpath("//button[@type='submit']")).click();
			
			Testkeys.pause(2);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Testkeys.terminate(testDriver);
			System.out.println("Test completed.");
		}
	} // main

}
