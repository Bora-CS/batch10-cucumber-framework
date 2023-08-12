package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class classPractice0808 {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		
		String username = "demo@minimals.cc";
		String password = "demo1234";
				
		
		try {
			driver.get("https://minimals.cc/auth/amplify/login?returnTo=%2Fdashboard");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		

	}

}
