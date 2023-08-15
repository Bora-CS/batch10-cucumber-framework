package lenaTest;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.Keywords;

public class lenaTest_utilities_Keywords {

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}

	public static void waitWithoutTry(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (Exception e) {

		}
	}

	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "";
	}

	/**
	 * Converts date string from "MM/dd/yyyy" format to "yyyy-mm-dd" format
	 * 
	 * @param inputDateString
	 * @return formattedDateString
	 */
	public static String formatInputDate(String inputDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(inputDateString, formatter);
		return date.toString();
	}

	public static boolean checkIfElementExists(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void checkIfElementExists(WebDriver driver, By locator, String errorMessage) throws Exception {
		boolean found = checkIfElementExists(driver, locator);

		if (!found) {
			throw new Exception(errorMessage);
		}
	}

	public static void switchToTheOtherWindow(WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();
		String mainHandle = driver.getWindowHandle();

		for (String handle : handles) {
			if (!handle.equals(mainHandle)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public static void getProducts(WebDriver driver) throws InterruptedException {

		driver.findElement(By
				.xpath("//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"))
				.click();
		for (int i = 1; i <= 199; i++) {
			String parentXpath = "(//div[@data-component-type='s-search-result'])";
			List<WebElement> cards = driver.findElements(By.xpath(parentXpath));
			driver.findElement(By.xpath(
					"//a[@class='s-pagination-item s-pagination-next s-pagination-button s-pagination-separator']"))
					.click();
			
			for (int index = 1; index <= cards.size(); index++) {
				String titleXpath = parentXpath + "[" + index + "]//h2";
				String priceXpath = parentXpath + "[" + index + "]//span[@class='a-price']";

				String title = driver.findElement(By.xpath(titleXpath)).getText();
				String price = driver.findElement(By.xpath(priceXpath)).getText();
				price = price.replace("\n", ".");
				
				System.out.println("ID: " + index + " Title: " + title + " Price: " + price);
			}

		}

	}

	public static void getFirstPage(WebDriver driver) {
		String parentXpath = "(//div[@data-component-type='s-search-result'])";
		List<WebElement> cards = driver.findElements(By.xpath(parentXpath));

	}

}
