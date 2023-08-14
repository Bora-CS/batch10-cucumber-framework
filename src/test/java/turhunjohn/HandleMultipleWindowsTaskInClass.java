package turhunjohn;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleMultipleWindowsTaskInClass {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.costco.com/");
			driver.findElement(By
					.xpath("//div[@class='col-xs-12 col-md-4'][3]")).click();
			// get the window handle for the current window
			String mainHandle = driver.getWindowHandle();
			System.out.println("Main Window Handle: " + mainHandle);
	
			
//			// open a new tab, switches selenium to focus on the new tab	
			driver.switchTo().newWindow(WindowType.TAB);
			driver.navigate().to("https://www.costco.com/");
			driver.findElement(By.xpath("//a[@href='/all-costco-grocery.html']")).click();
			String newHandle = driver.getWindowHandle();
			System.out.println("New Window Handle: " + newHandle);
			
			
			Set<String> handles = driver.getWindowHandles();
			for (String handle : handles) {
				if (!handle.equals(mainHandle)) {
					driver.switchTo().window(handle);
				}
			}

			System.out.println("All handles: " + handles);

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.quit();
		}
	}

}
