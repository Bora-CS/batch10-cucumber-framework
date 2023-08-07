package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
public class IWantMyVitaRepaired {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		
		
		
		try {
			driver.navigate().to("https://videogame911.com/ps-vita-repair/");
			driver.findElement(By.xpath("//*[@id=\"pa_shipping-options\"]")).click();
			driver.findElement(By.xpath("//*[@value=\"ship-my-console\"]")).click();
			driver.findElement(By.xpath(" //*[@class=\'input-text qty text\'] ")).clear();
			driver.findElement(By.xpath(" //*[@class=\'input-text qty text\'] ")).sendKeys("2");
			driver.findElement(By.xpath("//button[text()='Start Repair Order']")).click();
			
			Thread.sleep(3000);
		}
		
		catch (Exception e) {
			System.out.println("Failed test");
			System.out.println("reasonBeing: " + e.getMessage() );
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}
	}

}
