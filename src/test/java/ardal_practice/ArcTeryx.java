package ardal_practice;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ArcTeryx {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			driver.get("https://arcteryx.com/us/en/");
			Thread.sleep(1000);
			WebElement men = (WebElement) js.executeScript(
					"return document.querySelector('arcteryx-outdoor-header').shadowRoot.querySelector('nav').querySelector('span')");
			men.click();
			Thread.sleep(1000);
			String url = driver.getCurrentUrl();
			System.out.println(url);
			System.out.println("Yeah!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	} // main

}

	
	
	
	

