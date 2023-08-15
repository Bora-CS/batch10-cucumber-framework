package rubenTrials;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class ClassActionJquery {

	public static void main(String[] args) {
		draggable();
	}
	
	static void checkBox () {
		
		WebDriver driver= new ChromeDriver();
		try {
			driver.get("https://jqueryui.com/checkboxradio/");
			
			driver.switchTo().frame(0);
			
			boolean star_2 = driver.findElement(By.xpath("//*[@class=\'ui-checkboxradio-label ui-corner-all ui-button"
					+ " ui-widget\']")).isSelected();
						
			System.out.println("2 star status is: " +star_2 );
			
			Keywords.wait(3);
			
		}catch(Exception e) {
		
		}	finally {
			
		}
		
	}

	static void draggable() {
		WebDriver driver = new ChromeDriver();
		Actions ac = new Actions(driver);

		try {
			driver.get("https://jqueryui.com/draggable/");

			driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

			ac.clickAndHold(driver.findElement(By.id("draggable"))).moveByOffset(-200, -100).build().perform();

			ac.clickAndHold(driver.findElement(By.className("ui-resizable-se"))).moveByOffset(100, -30).build().perform();
			
			
			
			driver.switchTo().defaultContent();
			driver.findElements(By.linkText("sortable"));
			
			ac.clickAndHold(driver.findElement(By.xpath("//*contains[@class='ui-sortable-handle'][1]"))).moveByOffset(0, -30).release().perform();
			
			ac.clickAndHold(driver.findElement(By.xpath("//*contains[@class='ui-sortable-handle'][1]"))).moveByOffset(0, 40).release().perform();
			
			
			
			Keywords.wait(3);

		} catch (Exception e) {
			System.out.println("bad");
			System.out.println("bad bc: " + e.getMessage());
		} finally {

			driver.quit();
			driver.close();
		}

	}

}
