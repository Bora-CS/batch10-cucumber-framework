package lenaTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Keywords;

public class AmazonDataCollection {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		String searchTerm = "Shampoo";

		try {
			driver.navigate().to("https://www.amazon.com/");
			driver.findElement(By.cssSelector("#twotabsearchtextbox")).sendKeys(searchTerm + Keys.ENTER);

			Keywords.checkIfElementExists(driver,
					By.xpath(
							"//*[@data-component-type='s-result-info-bar']//*[contains(text(), '" + searchTerm + "')]"),
					"Expected to be on the search result page for '" + searchTerm + "'");
			
			
			lenaTest_utilities_Keywords.getProducts( driver);

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
