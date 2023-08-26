package rubenTrials;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class COSTCoPRACTICE extends BaseTest {

	static By homepage_searchBox = By.id("search-field");
	static By PLP_addToCartButtonS = By.xpath("//*[contains(@id, 'addbutton')]");
	static By header_cartNumber = By.xpath(("//*[@class='cart-number ']//span)[3]"));
	static By shoppingCart_pageTitle = By.id(("cart-title"));
	static By header_cartLink = By.id(("cart-T"));

	public static void user_add_item_into_cart() {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://www.costco.com/");

			driver.findElement(homepage_searchBox).sendKeys("chairs", Keys.ENTER);
			List<WebElement> addToCarts = driver.findElements(PLP_addToCartButtonS);

			for (int i = 0; i < 4; i++) {
				addToCarts.get(i).click();

			}
			String cartPageTitle = driver.findElement(shoppingCart_pageTitle).getText();
			String itemInCart = driver.findElement(header_cartNumber).getText();
			if (itemInCart.equals("4")) {

			}

			else {
				Assertions.fail("not right number of items in kart").getText();

				Assertions.assertTrue(cartPageTitle.contains("4"), "The kart page shows no titile> " + cartPageTitle);
				driver.close();
				driver.quit();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
