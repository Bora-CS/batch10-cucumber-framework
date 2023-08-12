package helen;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonDataCollectionTest {

	public static void main(String[] args) {
		
//		
//		search for an item
//		collect the title, price for each result item
//		collect 200 results
//		write the result into an excel file
//		calculate the hightest price, lowest price and average price
//		
	

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		String searchTerm = "toddler book";


		try {
			driver.get("amazon.com");
			driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(searchTerm + Keys.ENTER);
			
			WebElement searchBarResult = driver.findElement(By.xpath("//*[@data-component-type='s-result-info-bar']"));
			helen.utilities.Keywords.checkIfElementExists(driver, 
					By.xpath("\"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '"+searchTerm + "')]"), 
					"Expected to be on the search result page for " + searchTerm);
			
			String parentXpath = "(//div[@data-component-type='s-search-result])";
			
			driver.findElement(By.xpath(parentXpath));
			List<WebElement> cards = driver.findElements(By.xpath(parentXpath));
			
			for (int index = 1; index <= cards.size(); index++) {
				String titleXpath = parentXpath + "[" + index + "]//h2";
				String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";
				
				String title = driver.findElement(By.xpath(titleXpath)).getText();
				String price = driver.findElement(By.xpath(priceXpath)).getText();
				price = price.replace("\n", ".");				
				System.out.println(title);
				System.out.println(price);
			}
				
			
				System.out.println("Test Passed");
			} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
			
		
			} finally {
			driver.quit();
	}


	
		
		
		
		
	}

}
