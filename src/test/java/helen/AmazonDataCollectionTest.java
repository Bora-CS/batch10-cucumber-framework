package helen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import helen.pojo.AmazonSearchResult;
import helen.utilities.Excel;

public class AmazonDataCollectionTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		//ArrayList<Double> priceNumberList = new ArrayList<Double>();
		List<AmazonSearchResult> results = new ArrayList<>();
		
		String searchTerm = "toddler book";
		
		try {

			driver.get("https://www.amazon.com/");
			driver.findElement(By.xpath("//input[@aria-label='Search Amazon']")).sendKeys(searchTerm + Keys.ENTER);

			// validate the page
			helen.utilities.Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchTerm + "')]"),
					"Expected to be on the search result page for '" + searchTerm + "'");

			int counter = 1;
			while (counter < 200) {

				String parentXpath = "(//div[@data-component-type='s-search-result'])";
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(parentXpath), 48));
				List<WebElement> cards = driver.findElements(By.xpath(parentXpath)); 

				for (int index = 1; index <= cards.size(); index++) {
					String titleXpath = parentXpath + "[" + index + "]//h2";
					String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";
					
					if (helen.utilities.Keywords.checkIfElementExists(driver, By.xpath(priceXpath))) {
						String title = driver.findElement(By.xpath(titleXpath)).getText();
						String price = null;
	
						try {
							price = driver.findElement(By.xpath(priceXpath)).getText();
							price = price.replace("\n", ".").replace("$", "");
							
						} catch (NoSuchElementException e) {
							continue; 
						}
						//if price == 0, skip to the next..?
						results.add(new AmazonSearchResult (counter++, Double.valueOf(price), title));
				
					}
					
					if (counter > 200) { 
						break;	//for loop
					}
				}	
				
				if (counter > 200) {
					break;	//while loop
				}
				//click next page button
				driver.findElement(By.xpath("//a[contains(@class,'s-pagination-next')]")).click();
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();

		} finally {
			driver.close();
			driver.quit();
		}

	
//		for (AmazonSearchResult result : results) {
//			System.out.println("ID: " + result.id + " Price: " + result.price + " Title: " + result.title);
//		}
		
		Excel.exportAmazonSearchResults(searchTerm, results);
		
	}

}
