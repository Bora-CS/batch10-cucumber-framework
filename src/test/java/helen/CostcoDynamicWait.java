package helen;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CostcoDynamicWait {

	public static void main(String[] args) {

		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofMinutes(1));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().window().maximize();
		
		try {
			driver.navigate().to("http://www.costco.com/");
			WebElement audioDealsCard = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='shop audio deals']")));
			audioDealsCard.click();
			
			helen.utilities.Keywords.checkIfElementExists(driver, By.xpath("//h1[@id='refineCntMsg'][contains(text(), 'Audio/Video')]"), "Cannot find Audio/Video text");
			//h1[@id='refineCntMsg'][contains(text(), 'Audio/Video')]
			
			
			
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
