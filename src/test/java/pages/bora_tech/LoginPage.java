package pages.bora_tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

	private static WebElement element;

	public static WebElement emailInputBox(WebDriver driver) {
		element = driver.findElement(By.name("email"));
		return element;
	}

	public static WebElement passwordInputBox(WebDriver driver) {
		element = driver.findElement(By.name("password"));
		return element;
	}

	public static WebElement mainLoginButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//input[@value='Login']"));
		return element;
	}

}
