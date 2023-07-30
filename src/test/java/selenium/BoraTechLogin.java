package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLogin {

	public static void main(String[] args) {
		
		WebDriver driver= new ChromeDriver();
		
		try {
		driver.get("https://boratech-practice-app.onrender.com/");
		driver.findElement(By.linkText("Login")).click();
		
		driver.findElement(By.name("email")).sendKeys("w.lydia.liu@gmail.com");
		driver.findElement(By.name("password")).sendKeys("Liu123456");
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		
		Thread.sleep(2000);
		
		String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();
		
		if(!titleText.equals("Dashboard")) {
			throw new Exception("Title text doesn't match, expected: Dashboard vs Actual : " + titleText);
		}
		
		String currentUrl = driver.getCurrentUrl();
		if(!currentUrl.endsWith("/dashboard")) {
			throw new Exception(" url is wrong, Expected url contains: /dashboard vs Actual url: " + currentUrl);
		}
		driver.findElement(By.xpath("//a[@href='#!']/span[text()='Logout']")).getText();	
		
		
		System.out.println("Test Passed");
		
	} catch(Exception e) {
		System.out.println("Test Failed");
		System.out.println("reason:"+ e.getMessage());
		
	}finally {
		
	driver.quit();
	}

}
}
