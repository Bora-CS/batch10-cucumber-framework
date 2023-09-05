package pages.bora_tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddExperiencePage {

	private static WebElement element;

	public static WebElement titleInputBox(WebDriver driver) {
		element = driver.findElement(By.name("title"));
		return element;
	}

	public static WebElement companyInputBox(WebDriver driver) {
		element = driver.findElement(By.name("company"));
		return element;
	}

	public static WebElement locationInputBox(WebDriver driver) {
		element = driver.findElement(By.name("location"));
		return element;
	}

	public static WebElement fromDateInputBox(WebDriver driver) {
		element = driver.findElement(By.name("from"));
		return element;
	}

	public static WebElement toDateInputBox(WebDriver driver) {
		element = driver.findElement(By.name("to"));
		return element;
	}

	public static WebElement currentCheckBox(WebDriver driver) {
		element = driver.findElement(By.name("current"));
		return element;
	}

	public static WebElement descriptionInputBox(WebDriver driver) {
		element = driver.findElement(By.xpath("//textarea[@name='description']"));
		return element;
	}

	public static WebElement submitButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@type='submit']"));
		return element;
	}

}
