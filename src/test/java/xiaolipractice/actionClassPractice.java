package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import utilities.Keywords;

public class actionClassPractice {

	public static void main(String[] args) {
		draggable();
	}

	static void draggable() {

		WebDriver localDrive = DataDriven_test2.driverFactory();
		Actions ac = new Actions(localDrive);
		localDrive.get("https://jqueryui.com/droppable/");

		localDrive.switchTo().frame(localDrive.findElement(By.tagName("iframe")));

		ac.clickAndHold(localDrive.findElement(By.id("draggable"))).moveByOffset(400, -100).build().perform();

		ac.dragAndDrop(localDrive.findElement(By.id("draggable")), localDrive.findElement(By.id("droppable"))).build()
				.perform();

		localDrive.switchTo().defaultContent();

		localDrive.findElement(By.linkText("Resizable")).click();
		localDrive.switchTo().frame(localDrive.findElement(By.tagName("iframe")));

		ac.clickAndHold(localDrive.findElement(By.className("ui-resizable-se"))).moveByOffset(50, -30).build()
				.perform();

		localDrive.switchTo().defaultContent();
		
		
		
		Keywords.waitWithOutTry(3);
		localDrive.close();
		localDrive.quit();
		
	}

}