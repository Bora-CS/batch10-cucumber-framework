package rubenTrials;

import java.awt.RenderingHints.Key;
import java.sql.Driver;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OkSomeNewStuff {

	
	
	public static WebDriver driver; 
	
	
	
	public static void main(String[] args) {
		search_validation();
		
	}
	
	public static void search_validation() {
		setUpTest();
		driver.findElement(By.id("atSearchProducts")).sendKeys("beer" + Keys.ENTER);
		
		String searchResult = driver.findElement(By.xpath("//h1[contains(@class, 'resultTitle')]")).getText();
		if (searchResult.contains("beer")) {
			System.out.println("bad failure.");
			
		} else {
			System.out.println("gud Job m8");
		}
		
		driver.findElement(By.xpath("//*[contains(@class, 'linkButton') and text()= 'Beer' ]").click();
		
		WebDriver wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		try {
			ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@id, 'checkBox')] /*text()= 'Ale (1,891)']"));
			
		}
	}
		
	public static void 		setUpTest () {
		
		driver= new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	
	driver.findElement(By.xpath("//h1[contains[@class, 'resultTitle']]")).sendKeys("beer" + Keys.ENTER);
}
