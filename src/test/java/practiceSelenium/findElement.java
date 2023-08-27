package practiceSelenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class findElement {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.navigate().to("https://www.nvcc.edu/academic-tools/");

			// WebElement myNova = driver.findElement(By.tagName("div"));
			// String searchinMynova = myNova.getText();

		// System.out.println("Text is : " + searchinMynova);
		// System.out.println(myNova.getText());
			// Thread.sleep(3000);
			boolean result = chechkIfExistAnElement(driver, By.tagName("div"));
			System.out.println(result);

		} catch (Exception e) {
			System.out.println("Ran into some problems");
			// e.printStackTrace(); to throws NewSuchElementException
		} finally {
			driver.quit();
		}
	}

	public static boolean chechkIfExistAnElement(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;

		}

	}

	public static boolean checkIfExistElements(WebDriver driver, By locator) {

		List<WebElement> matchElements = driver.findElements(locator);
		return matchElements.size() > 0;

	}

}
