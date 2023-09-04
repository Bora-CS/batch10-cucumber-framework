package pages.bora_tech;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private static WebElement element;

	public static WebElement pageTitle(WebDriver driver) {
		element = driver.findElement(By.xpath("//h1[@class='x-large']"));
		return element;
	}

	public static WebElement mainLoginButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@class='btn btn-light']"));
		return element;
	}

}
