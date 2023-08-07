package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddApplication {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		try {
			driver.navigate().to("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.xpath("//*[text()='Apply Now']")).click();
				
		}
		catch (Exception e) {
			System.out.println("Failed test");
			System.out.println("reasonBeing: " + e.getMessage() );
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
