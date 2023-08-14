package hui_automation;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonDataCollection {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String searchTerm = "men's shoes";
		int numResults = 200;

		try {
			driver.get("https://www.amazon.com/");
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(searchTerm + Keys.ENTER);

			Testkeys.containsElement(driver,
					By.xpath(String.format(
							"//div[contains(@cel_widget_id,'UPPER-RESULT_INFO_BAR')]//*[contains(text(), \"%s\")]",
							searchTerm)),
					String.format("%s not found", searchTerm));

			// search results
			String parentXpath = "(//div[@data-component-type='s-search-result'])";
			List<WebElement> cards = driver.findElements(By.xpath(parentXpath));

			int count = 0;
			SEARCH_LOOP: while (count < numResults) {
				for (int index = 1; index <= cards.size(); index++) {
					String titleXpath = String.format("%s[%d]//h2", parentXpath, index);
					String labelXpath = String.format("%s[%d]//h2/a", parentXpath, index);
					String priceXpath = String.format("%s[%d]//span[@class='a-price']", parentXpath, index);

					// only if price is available
					if (Testkeys.containsElement(driver, By.xpath(priceXpath))) {
						String title = driver.findElement(By.xpath(titleXpath)).getText();
						String label = driver.findElement(By.xpath(labelXpath)).getText();
						String realTitle = title + " - " + label;
						String price = driver.findElement(By.xpath(priceXpath)).getText();
						price = price.replace("\n", ".");

						System.out.println("ID: " + (count + 1) + " Title: " + realTitle + " Price: " + price);
						count++;
					}
					// max results reached
					if (count == numResults)
						break SEARCH_LOOP;
				} // for loop - result page
				String nextPageXpath = "//a[contains(@aria-label,'Go to next page')]";
				if (Testkeys.containsElement(driver, By.xpath(nextPageXpath)))
					driver.findElement(By.xpath(nextPageXpath)).click();
				else
					break;
			} // while loop

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
		}

	} // main

}
