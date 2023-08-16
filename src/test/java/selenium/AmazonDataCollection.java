package selenium;

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

import pojo.AmazonSearchResult;
import utilities.BoraTech;
import utilities.Excel;
import utilities.Keywords;

public class AmazonDataCollection {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		List<AmazonSearchResult> results = new ArrayList<>();

		String searchTerm = "Shampoo";

		try {
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(searchTerm + Keys.ENTER);

			Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchTerm + "')]"),
					"Expected to be on the search result page for '" + searchTerm + "'");

			int counter = 0;
			while (counter < 200) {
				String parentXpath = "(//div[@data-component-type='s-search-result'])";
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(parentXpath), 48));
				List<WebElement> cards = driver.findElements(By.xpath(parentXpath));

				for (int index = 1; index <= cards.size(); index++) {
					String titleXpath = parentXpath + "[" + index + "]//h2";
					String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

					String title = driver.findElement(By.xpath(titleXpath)).getText();
					String price = null;
					try {
						price = driver.findElement(By.xpath(priceXpath)).getText();
						price = price.replace("\n", ".").replace("$", "");
					} catch (NoSuchElementException e) {
						continue;
					}

					results.add(new AmazonSearchResult(++counter, Double.valueOf(price), title));

					if (counter == 200) {
						break;
					}
				}

				if (counter == 200) {
					break;
				}
				driver.findElement(By.xpath("//a[contains(@class,'s-pagination-next')]")).click();
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

		Excel.exportAmazonSearchResults(searchTerm, results);

	}

}
