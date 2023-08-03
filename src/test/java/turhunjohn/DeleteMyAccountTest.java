package turhunjohn;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DeleteMyAccountTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();

		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";
		
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			// driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//a[@class='btn btn-light']")).click();

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			wait(2);
			
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();

			if (!titleText.equals("Dashboard")) {
				throw new Exception(
						"Title text doesn't match, Ecpected: [Dashboard]  vs Actual " + "[" + titleText + "]");
			}
			
			
		int deleteMyAccountButtons = driver.findElements(By.xpath("//button[@class='btn btn-danger']")).size();
			
			for (int i = 0; i < deleteMyAccountButtons; i++) {
				
			WebElement	deleteButton = driver.findElement(By.xpath("//button[@class='btn btn-danger']"));
			deleteButton.click();
			wait(2);
				
			}

			System.out.println("Delete My Account - Success! ");
			
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Test Failed");
		System.out.println("Reason: " + e.getMessage());
	} finally {
		driver.quit();
	}
}

public static void wait(int second) throws InterruptedException {
	Thread.sleep(second * 1000);
}
}
