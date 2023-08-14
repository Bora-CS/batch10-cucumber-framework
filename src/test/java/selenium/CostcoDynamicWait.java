package selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Keywords;

public class CostcoDynamicWait {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();

		try {
			driver.navigate().to("https://www.costco.com/");
			WebElement audioDealsCard = wait
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='shop audio deals']")));
			audioDealsCard.click();

			Keywords.checkIfElementExists(driver, By.xpath("//h1[@id='refineCntMsg'][contains(text(), 'Audio/Video')]"),
					"Failed to navigate to Audio/Video search result page");

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.quit();
		}

	}

}
