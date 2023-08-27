package practiceSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class findElements {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("https://www.nvcc.edu/academic-tools/");
			List<WebElement> allDIV = driver.findElements(By.tagName("div"));
			for (WebElement div : allDIV) {
				System.out.println(div.getText());

			}

		} catch (Exception e) {
			System.out.println("Ran into some problems");
		} finally {
			driver.quit();
		}

	}

}
