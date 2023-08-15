package helen;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonDataCollectionTest {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String searchTerm = "toddler book";
		int count = 1;
		int numberToSearch = 200;

		ArrayList<Double> priceNumberList = new ArrayList<Double>();

		try {
			driver.get("https://www.amazon.com/");
			driver.findElement(By.xpath("//input[@aria-label='Search Amazon']")).sendKeys(searchTerm + Keys.ENTER);

			// validate the page
			helen.utilities.Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchTerm + "')]"),
					"Expected to be on the search result page for '" + searchTerm + "'");

			String parentXpath = "(//div[@data-component-type='s-search-result'])";
			List<WebElement> cards = driver.findElements(By.xpath(parentXpath));
			String nextButtonXpath = "//span[@class='s-pagination-item s-pagination-selected']";
			
			while (count < numberToSearch) {

				for (int index = 1; index <= cards.size(); index++) {
					
					String titleXpath = parentXpath + "[" + index + "]//h2";
					String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

					if (helen.utilities.Keywords.checkIfElementExists(driver, By.xpath(priceXpath))) {
						String title = driver.findElement(By.xpath(titleXpath)).getText();
						String price = driver.findElement(By.xpath(priceXpath)).getText();
						
						price = price.replace("\n", ".");

						// convert String price with $ into Double without $ and put in ArrayList
						double priceNumber = Double.parseDouble(price.replace("$", ""));

						System.out.println("[" + index + "] " + title + " | " + price);
						priceNumberList.add(priceNumber);
						count++;
					}
					//break for loop once numberToSearch is reached
					if (count == numberToSearch) {
						break;
					}

				}
				
				//click the next page button
				if (helen.utilities.Keywords.checkIfElementExists(driver, By.xpath("nextButtonXpath"))) {
					driver.findElement(By.xpath(nextButtonXpath)).click();
				} else {
					break;
				}
			}

			// call the method to get highest, lowest and average price
			Double maxPrice = helen.utilities.Keywords.getMaxPrice(driver, priceNumberList);
			Double minPrice = helen.utilities.Keywords.getMinPrice(driver, priceNumberList);
			Double avePrice = helen.utilities.Keywords.getAvePrice(driver, priceNumberList);

			System.out.println("------------------------\nThe highest price: $" + maxPrice + "\nThe lowest price:  $"
					+ minPrice + "\nThe average price: $" + avePrice + "\n------------------------");

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();

		} finally {
			driver.close();
			driver.quit();
		}
	}
}
