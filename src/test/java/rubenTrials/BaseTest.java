package rubenTrials;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

	public static void main(String[] args) {
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(url);
		
	}
	public static void endTest() {
		driver.close();
		driver.quit();
	} 
	public static void customWait(int second) {
		try {
			Thread.sleep(1000);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
