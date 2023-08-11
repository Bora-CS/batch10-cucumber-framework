package rubenTrials;

import java.awt.RenderingHints.Key;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class AiTaskPractice {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		Actions ac = new Actions(driver);
		try {
			driver.get("https://jqueryui.com/draggable/");
			ac.clickAndHold(driver.findElement(By.className
					("ui-widget-content ui-draggable ui-draggable-handle")))
				.moveByOffset(500, -13000);
			
			
			Thread.sleep(3000);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			
		}
	}

}
