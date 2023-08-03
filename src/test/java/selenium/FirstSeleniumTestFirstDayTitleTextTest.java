package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumTestFirstDayTitleTextTest {
	public static void main(String[] args) {
		
	
	WebDriver driver = new ChromeDriver();
	try {
		driver.navigate().to("file:///Users/apple/projects/batch10-html-practice/index.html");
		WebElement titleText = driver.findElement(By.tagName("h1"));
		 String  titleTextContent  =  titleText.getText();
		 
		 System.out.println("===> Title Text is: "  + titleTextContent );

	} catch (Exception e) {
		System.out.println("Ran into some problem");

	} finally {
		driver.quit();
	}
}
}


