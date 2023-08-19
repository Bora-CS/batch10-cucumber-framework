package turhunjohn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import lydia.utilities.Keywords;

public class HandleWindowsPracticeAtHome {

	public static void main(String[] args) {
	

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		
		try {
			
			driver.get("https://www.google.com/");
			driver.findElement(By.name("q")).sendKeys("BoraTech" + Keys.ENTER);
		    String mainHandle =driver.getWindowHandle();
		    System.out.println("Main Window handle: " + mainHandle);
			
			driver.switchTo().newWindow(WindowType.TAB);
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.cssSelector("#twotabsearchtextbox"))
			.sendKeys("iphone case" +Keys.ENTER);
			Keywords.wait(3);
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			driver.close();
			driver.quit();
		}
		
		

	}

}
