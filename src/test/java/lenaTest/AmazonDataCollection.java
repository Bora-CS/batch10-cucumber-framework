package lenaTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonDataCollection {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		List<String> twoHundredShampoos = new ArrayList<>();
		int count = 1;
		int numberOfItemNeeded = 200;
		String searchItem = "shampoo";

		try {
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.id("twotabsearchtextbox")).sendKeys(searchItem + Keys.ENTER);
			String parentXpath = "(//div[@data-component-type='s-search-result'])";

			while (count < numberOfItemNeeded) {
				List<WebElement> cards = driver.findElements(By.xpath(parentXpath));

				for (int index = 1; index <= cards.size(); index++) {
					String shampoo = "";
					try {
						String titleXpath = parentXpath + "[" + index + "]//h2";
						String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

						String title = driver.findElement(By.xpath(titleXpath)).getText();
						String price = driver.findElement(By.xpath(priceXpath)).getText();
						price = price.replaceAll("\n", ".");
						shampoo = "ID: " + count + " Title: " + title + " Price: " + price;
					} catch (NoSuchElementException e) {
						continue;
					}

					twoHundredShampoos.add(shampoo);
					count++;
					if (twoHundredShampoos.size() == numberOfItemNeeded) {
						break;
					}
				}
				driver.findElement(By.xpath(
						"//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"))
						.click();

			}
			for (String shampoo : twoHundredShampoos) {
				System.out.println(shampoo);
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

	}

}
