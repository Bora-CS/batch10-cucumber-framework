package selenium;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v114.runtime.model.ExceptionDetails;
import org.openqa.selenium.chrome.ChromeDriver;

public class BoraTechLogin {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();

		driver.navigate().to("https://boratech-practice-app.onrender.com/");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("://input``"));
		
		
		
	}

}
