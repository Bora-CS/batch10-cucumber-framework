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

public class AmazonDataCollectionTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		String searchTerm = "toddler book";
		int count = 1;
		int numberToSearch = 200;

		List<AmazonSearchResult> results = new ArrayList<>();
		//ArrayList<Double> priceNumberList = new ArrayList<Double>();

		try {

			driver.get("https://www.amazon.com/");
			driver.findElement(By.xpath("//input[@aria-label='Search Amazon']")).sendKeys(searchTerm + Keys.ENTER);

			// validate the page
			helen.utilities.Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchTerm + "')]"),
					"Expected to be on the search result page for '" + searchTerm + "'");

			while (count < numberToSearch) {

				String parentXpath = "(//div[@data-component-type='s-search-result'])";
				//wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(parentXpath), 48));
				List<WebElement> cards = driver.findElements(By.xpath(parentXpath)); // not affected by implicitly wait

				String nextButtonXpath = "//span[@class='s-pagination-item s-pagination-selected']";

				for (int index = 1; index <= cards.size(); index++) {

					String titleXpath = parentXpath + "[" + index + "]//h2";
					String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

					if (helen.utilities.Keywords.checkIfElementExists(driver, By.xpath(priceXpath))) {
						String title = driver.findElement(By.xpath(titleXpath)).getText();
						String price = null;
						double priceNumber;

						try {
							price = driver.findElement(By.xpath(priceXpath)).getText();
							price = price.replace("\n", ".").replace("$", "");

						} catch (NoSuchElementException e) {
							continue; // affect for loop
						}
						//System.out.println("[" + count++ + "] " + title + " | " + price);
						
						
						results.add(new AmazonSearchResult (count++, Double.parseDouble(price), title) );
						
						// break for loop once numberToSearch is reached (affect for loop)
						if (count > numberToSearch) {
							break;
						}
					}

					// click the next page button after each page loop is done (affect while loop)
					if (helen.utilities.Keywords.checkIfElementExists(driver, By.xpath(nextButtonXpath))) {
						driver.findElement(By.xpath(nextButtonXpath)).click();
					} else {
						break;
					}
				}
			}

			// call the method to get highest, lowest and average price
//			Double maxPrice = helen.utilities.Keywords.getMaxPrice(driver, AmazonSearchResult);
//			Double minPrice = helen.utilities.Keywords.getMinPrice(driver, AmazonSearchResult);
//			Double avePrice = helen.utilities.Keywords.getAvePrice(driver, AmazonSearchResult);

//			System.out.println("------------------------\nThe highest price: $" + maxPrice + "\nThe lowest price:  $"
//					+ minPrice + "\nThe average price: $" + avePrice + "\n------------------------");

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();

		} finally {
			driver.close();
			driver.quit();
		}
		
		System.out.println(results);  //pojo objects printed out
		
		
		for (AmazonSearchResult result : results) {
			System.out.println("ID: " + result.id + " Price: " + result.price + " Title: " + result.title);
		}
	}

}


//ArrayList of Hashmap to put data structure of index, title, price. use pojo class.
