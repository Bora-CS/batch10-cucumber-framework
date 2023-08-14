package turhunjohn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class ActionClassPractice {

	public static void main(String[] args) {
		draggable();

	}
		
	static void checkBox() {
		WebDriver localDriver= new ChromeDriver();
		localDriver.get("https://jqueryui.com/checkboxradio/");
		localDriver.switchTo().frame(0);
		localDriver.findElement(By.id("checkbox-1")).click();
	boolean star_2	=localDriver.findElement(By.id("//*[@for='checkbox-1']")).isSelected();
	System.out.println( "2 Star status is : " + star_2);
	
	}


	static void draggable() {
		WebDriver localDriver = DataDrivern_ApplicationForm.driverFactory();

		try {

			Actions ac = new Actions(localDriver);
			localDriver.get("https://jqueryui.com/droppable/");
			localDriver.switchTo().frame(
					localDriver.findElement(By.tagName("iframe")));
//     ac.clickAndHold(localDriver.findElement(By.id("draggable"))).build().perform();
//     ac.moveByOffset(200, -100).build().perform();


    ac.dragAndDrop(localDriver.findElement(By.id("draggable")),localDriver.findElement(By.id("droppable")))
    .build().perform();
			
	Keywords.wait(3);
	localDriver.switchTo().defaultContent();
	localDriver.findElement(By.linkText("Resizable")).click();
	localDriver.switchTo().frame(
			localDriver.findElement(By.tagName("iframe")));
    
    
    ac.clickAndHold(localDriver.findElement(By.className("ui-resizable-se")))
    .moveByOffset(50, -30).build().perform(); 
    localDriver.switchTo().defaultContent();
    
    localDriver.switchTo().defaultContent();
    localDriver.findElement(By.linkText("Sortable")).click();
    localDriver.switchTo().frame(
			localDriver.findElement(By.tagName("iframe")));
    
    
    
			Keywords.wait(3);
			System.out.println("Test Pass!");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			localDriver.close();
			localDriver.quit();
		}

	}
}