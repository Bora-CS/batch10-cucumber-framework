package ardal_practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ardal_practice.pojo.ExcelArdal;
import ardal_practice.pojo.IndigoSearchResult;
import utilities.Keywords;

public class IndigoDataCollection {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		List<IndigoSearchResult> results = new ArrayList<>();
		String searchTerm = "Jellycat";

		try {

			driver.navigate().to("https://www.indigo.ca/en-ca/");
			driver.findElement(By.name("q")).sendKeys(searchTerm + Keys.ENTER);

//			Keywords.checkIfElementExists(driver, By.xpath("//span[@data-search-keyword=" + searchTerm + "]"),
//					"Expected search result is :" + searchTerm);

			WebElement loadBtn = driver.findElement(By.xpath("//button[@class='btn btn-tertiary more']"));
			loadBtn.click();
//			loadBtn.click();
//			loadBtn.click();
//			loadBtn.click();

			int counter = 0;
			while (counter < 45) {
				String parentPath = "//div[@class='tiles col-6 col-lg-4 px-lg-1 searchProductTiles']";
				wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(parentPath), 40));
				List<WebElement> cards = driver.findElements(By.xpath(parentPath));
				for (int i = 1; i <= cards.size(); i++) {

					String titlePath = parentPath + "[" + i + "]//div[@class='pdp-link']";
					String pricePath = parentPath + "[" + i + "]//span[@class='value']";

					String title = driver.findElement(By.xpath(titlePath)).getText();
					String price = driver.findElement(By.xpath(pricePath)).getText();
					price = price.replace("$", "");

					results.add(new IndigoSearchResult(++counter, Double.valueOf(price), title));

					if (counter == 45) {
						break;
					}

				}
				if (counter == 45) {
					break;
				}

			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Something went wrong");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

		ExcelArdal.exportIndigoSearchResults(searchTerm, results);

	}

}
