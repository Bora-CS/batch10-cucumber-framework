package helen;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Minimals_FilterUser_Test {
	
	
	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		
		String username = "demo@minimals.cc";
		String password = "demo1234";
		
		
		try {
			driver.get("https://minimals.cc/auth/amplify/login");
			//login	
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();
			
			//click [User] and then [List]
			driver.findElement(By.xpath("//span[text()='user']")).click();
			driver.findElement(By.xpath("//span[text()='list']")).click();
			driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]//span[text()='Role']/../../..")).click();
			
			//select roles to search
			WebElement selectRole1 = driver.findElement(By.xpath("//li[@data-value='UX/UI Designer']"));
			WebElement selectRole2 = driver.findElement(By.xpath("//li[@data-value='Data Analyst']"));
			selectRole1.click();
			selectRole2.click();
			driver.findElement(By.tagName("body")).click();
			
			//validate if selected roles match	
			String tableRowXpath = "//table[@class='MuiTable-root css-1pmdcrr']/tbody/tr";
			List<WebElement> roleCell = driver.findElements(By.xpath(tableRowXpath + "/td[5]"));
			
			String actualRole1 = roleCell.get(0).getText();
			String actualRole2 = roleCell.get(1).getText();
			
			if (!selectRole1.getText().equals(actualRole1) || !selectRole2.getText().equals(actualRole2) ) {
				throw new Exception("The newly selected role was not found");
			}
				
			System.out.println("Test Passed");
			
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
		
	}

}
