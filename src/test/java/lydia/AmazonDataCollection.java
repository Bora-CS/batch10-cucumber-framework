package lydia;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import lydia.POJO.AmazonSearchResult;
import lydia.utilities.ExcelLydia;
import lydia.utilities.Keywords;

public class AmazonDataCollection {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		List<AmazonSearchResult> results = new ArrayList<>();

		int numberOfItemNeeded = 200;
		String searchItem = "shampoo";

		try {
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchItem + Keys.ENTER);
			Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchItem + "')]"),
					"Expected to be on the search result page for '" + searchItem + "'");
			int counter = 0;
			while (counter < numberOfItemNeeded) {
				String parentXpath = "(//div[@data-component-type='s-search-result'])";
				List<WebElement> cards = wait
						.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(parentXpath), 48));

				for (int index = 1; index <= cards.size(); index++) {
					String titleXpath = parentXpath + "[" + index + "]//h2";
					String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

					String title = driver.findElement(By.xpath(titleXpath)).getText();
					String price = null;
					try {
						price = driver.findElement(By.xpath(priceXpath)).getText();
						price = price.replaceAll("\n", ".").replace("$", "");

					} catch (NoSuchElementException e) {
						continue;
					}

					results.add(new AmazonSearchResult(++counter, Double.valueOf(price), title));

					if (counter == numberOfItemNeeded) {
						break;
					}
				}

				if (counter == numberOfItemNeeded) {
					break;
				}
				driver.findElement(By.xpath("//a[contains(@class,'s-pagination-next')]")).click();

			}
			System.out.println("Test Passed");

		} catch (Exception e) {
			System.out.println("Test Failed");
			e.printStackTrace();
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}
		ExcelLydia.exportAmazonSearchResults(searchItem, results);
	}

}
