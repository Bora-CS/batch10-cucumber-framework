package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class ClassActionJquery {

	public static void main(String[] args) {
		checkBox();
		action();
	}

	public static void checkBox() {
		WebDriver driver = new ChromeDriver();

		try {
			// checking the newYork box
			driver.get("https://jqueryui.com/checkboxradio/");
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
			driver.findElement(By.xpath("//label[@for='radio-1']")).click();
			driver.findElement(By.xpath("//label[@for='checkbox-4']")).click();

			Thread.sleep(3000);
			// checking 5 starBocks
//			boolean checkFiveStars = driver.findElement(By.xpath("//label[@for='checkbox-4']")).isSelected();
//			System.out.println(checkFiveStars);
			// Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println("test failed because" + e.getMessage());
			e.printStackTrace();
		} finally {
			driver.quit();

		}
	}

	public static void action() {

	}

}
