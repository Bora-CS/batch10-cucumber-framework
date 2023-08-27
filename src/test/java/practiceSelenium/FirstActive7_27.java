package practiceSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstActive7_27 {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		driver.navigate().to("https://www.google.com/");
		WebElement searchBox = driver.findElement(By.name("q"));
		searchBox.sendKeys("Mynova" + Keys.ENTER);

	}

}
