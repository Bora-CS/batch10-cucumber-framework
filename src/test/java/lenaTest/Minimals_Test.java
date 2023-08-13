package lenaTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import lenaTest.utilities.Minimals_Logging;
import utilities.Keywords;

public class Minimals_Test {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		Actions ac = new Actions(driver);
		driver.manage().window().maximize();

		String username = "demo@minimals.cc";
		String password = "demo1234";

		try {
			Minimals_Logging.login(driver, username, password);

			driver.findElement(By.xpath("//span[text()='user']")).click();
			driver.findElement(By.xpath("//a[@href='/dashboard/user/list']")).click();
			driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]")).click();

			// click two positions
			driver.findElement(By.xpath("//li[@data-value='HR Manager']")).click();
			ac.scrollToElement(driver.findElement(By.xpath("//li[@data-value='Project Manager']"))).perform();
			driver.findElement(By.xpath("//li[@data-value='Project Manager']")).click();
			driver.findElement(By.tagName("body")).click();
			Keywords.wait(2);

			// Validation
			String expectedText1 = "HR Manager";
			String expectedText2 = "Data Analyst";
			String actuallText = driver
					.findElement(By.xpath("//span[@class='MuiChip-label MuiChip-labelSmall css-tavflp']")).getText();

			if ((!actuallText.equals(expectedText1)) && (!actuallText.equals(expectedText2))) {
				throw new Exception(
						"Title Text doesn't match.\nExpected: " + expectedText1 + expectedText2 + "\nActual: " + actuallText);
			}

		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
