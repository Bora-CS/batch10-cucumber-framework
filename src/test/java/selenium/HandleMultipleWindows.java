package selenium;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class HandleMultipleWindows {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.google.com/");
			driver.findElement(By.name("q")).sendKeys("BoraTech" + Keys.ENTER);
			// get the window handle for the current window
			String mainHandle = driver.getWindowHandle();
			System.out.println("Main Window Handle: " + mainHandle);

			// open a new tab, switches selenium to focus on the new tab
			driver.switchTo().newWindow(WindowType.TAB);
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys("iPhone Case" + Keys.ENTER);
			String newHandle = driver.getWindowHandle();
			System.out.println("New Window Handle: " + newHandle);

			Set<String> handles = driver.getWindowHandles();

			for (String handle : handles) {
				if (!handle.equals(mainHandle)) {
					driver.switchTo().window(handle);
				}
			}

			System.out.println("All handles: " + handles);

			// closes the google window
			driver.switchTo().window(mainHandle);
			driver.close();

			Keywords.wait(2);

			// closes the amazon window
			driver.switchTo().window(newHandle);
			driver.close();

			Keywords.wait(2);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
