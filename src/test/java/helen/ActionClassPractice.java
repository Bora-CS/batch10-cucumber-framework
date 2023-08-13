package helen;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class ActionClassPractice {
	
	public static void main(String[] args) {
		//draggable();
		checkBox();
	}
	
	static void draggable() {
		
		WebDriver localDriver = ApplicationForm_DataDrivenTest.driverFactory();

		Actions ac = new Actions(localDriver);
		
		//Droppable
		localDriver.get("https://jqueryui.com/droppable/");
		localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));  //switch into "iframe"
//		ac.clickAndHold(localDrive.findElement(By.id("draggable"))).build().perform();
//		ac.moveByOffset(200, -100).build().perform();		
		ac.dragAndDrop(localDriver.findElement(By.id("draggable")),localDriver.findElement(By.id("droppable"))).build().perform();
		localDriver.switchTo().defaultContent();  //switch back to default content
		
		//Resizable
		localDriver.findElement(By.linkText("Resizable")).click();
		localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
		ac.clickAndHold(localDriver.findElement(By.className("ui-resizable-se"))).moveByOffset(100, -30).build().perform();
		localDriver.switchTo().defaultContent();
		
		//Sortable - move around
		localDriver.findElement(By.linkText("Sortable")).click();
		localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
		
		ac.clickAndHold(localDriver.findElement(By.xpath("//*[contains(@class,'ui-sortable-handle')][1]")))
		.moveByOffset(0,80).release().build().perform();
		
		ac.clickAndHold(localDriver.findElement(By.xpath("//*[contains(@class,'ui-sortable-handle')][7]")))
		.moveByOffset(0,-100).release().build().perform();
		
		ac.clickAndHold(localDriver.findElement(By.xpath("//*[contains(@class,'ui-sortable-handle')][2]")))
		.moveByOffset(0,120).release().build().perform();
		localDriver.switchTo().defaultContent();
		
		localDriver.close();
		localDriver.quit();
		
	}
	
	
	
	static void checkBox() {
		
		WebDriver localDriver = new ChromeDriver();
		localDriver.get("https://jqueryui.com/checkboxradio/");
		localDriver.switchTo().frame(0);
		
		//before click
		boolean star_2= localDriver.findElement(By.xpath("//*[@for='checkbox-1']/span")).isSelected();
		System.out.println("2 Star status is: " + star_2);
		
		//after click
		localDriver.findElement(By.id("checkbox-1")).click();
		star_2 = localDriver.findElement(By.id("//*[@for='checkbox-1']/span")).isSelected();
		System.out.println("2 Star status is: " + star_2);
		
		localDriver.close();
		localDriver.quit();
		
	}

}
