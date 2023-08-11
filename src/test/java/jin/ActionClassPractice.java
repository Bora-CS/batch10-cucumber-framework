package jin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class ActionClassPractice {

	public static void main(String[] args) {
		checkBox();

	}

	static void checkBox() {
		WebDriver driver1 = ActionClassPractice.driverFactory();
		driver1.get("https://jqueryui.com/checkboxradio/");
		driver1.switchTo().frame(0);
		driver1.findElement(By.id("checkbox-1")).click();
		boolean star_2 = driver1.findElement(By.id("checkbox-1")).isSelected();
		System.out.println(star_2);

	}

	private static WebDriver driverFactory() {
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	static void draggable() {
		WebDriver driver = ActionClassPractice.driverFactory();

		Actions ac = new Actions(driver);

		driver.get("https://jqueryui.com/droppable/");

		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		ac.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build()
				.perform();

//go outside frame again

		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[@href='https://jqueryui.com/resizable/']")).click();

//go inside frame again

		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		ac.clickAndHold(driver.findElement(By.className("ui-resizable-se"))).moveByOffset(100, -30).build().perform();

//go outside frame again	
		driver.switchTo().defaultContent();
		driver.findElement(By.linkText("Sortable")).click();
//go inside of frame
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
		ac.clickAndHold(driver.findElement(By.xpath("//*[contains(@class,'ui-sortable-handle[0]')]")))
				.moveByOffset(0, 80).release().build().perform();
		driver.switchTo().defaultContent();

		try {
			Keywords.wait(3);

		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.close();
		driver.quit();

//
//		ac.clickAndHold(driver.findElement(By.id("draggable"))).moveToLocation(300, -100).build().perform();

//		
//		
//		ac.dragAndDrop(driver.findElement(By.id("resizable")), driver.findElement(By.linkText("droppable"))).build().perform();
//		

	}

}