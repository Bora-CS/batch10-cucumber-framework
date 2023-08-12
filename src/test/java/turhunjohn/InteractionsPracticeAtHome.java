package turhunjohn;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class InteractionsPracticeAtHome {

	public static void main(String[] args) {
		
		draggable();
		checkBox();
	}
		
	    static void checkBox()  {
		WebDriver driver= new ChromeDriver();
		driver.get("https://jqueryui.com/checkboxradio/");
	
		driver.switchTo().frame(0);	
		
		boolean star_2	=driver.findElement(By.xpath("//*[@for='checkbox-1']/span")).isSelected();
		System.out.println( "2 Star status Before click is : " + star_2);
		
		driver.findElement(By.xpath("//*[@for='checkbox-1']")).click();	
		star_2	=driver.findElement(By.xpath("//*[@for='checkbox-1']/span")).isSelected();
		System.out.println( "2 Star status is : " + star_2);

	   driver.quit();
	
	}

		    static void draggable() {
			WebDriver driver = new ChromeDriver();
			
		try {
			Actions action = new Actions(driver);
			driver.get("https://jqueryui.com/droppable/");
			Keywords.wait(3);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

//			action.clickAndHold(driver.findElement(By.id("draggable"))).moveByOffset(200, -100).build().perform();

			action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable"))).build()
					.perform();

			Keywords.wait(3);
			driver.switchTo().defaultContent();

			driver.findElement(By.linkText("Resizable")).click();
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

			action.clickAndHold(driver.findElement(By.className("ui-resizable-se"))).moveByOffset(100, -30).build()
					.perform();

			driver.switchTo().defaultContent();

			driver.findElement(By.linkText("Sortable")).click();
             ////Switch by Index 
			// driver.switchTo().frame(0);
			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

//			action.clickAndHold(driver.findElement(By.xpath("//*[@class='ui-state-default ui-sortable-handle'][1]")))
//					.moveByOffset(0, 80).release().build().perform();
//			Keywords.wait(2);
//
//			action.clickAndHold(driver.findElement(By.xpath("//*[@class='ui-state-default ui-sortable-handle'][7]")))
//					.moveByOffset(0, -100).release().build().perform();
//			Keywords.wait(2);
//			action.clickAndHold(driver.findElement(By.xpath("//*[contains(@class,'ui-sortable-handle')][2]")))
//					.moveByOffset(0, 120).release().build().perform();
//			  Keywords.wait(3);

			System.out.println(" Test pass");

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Test failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}
