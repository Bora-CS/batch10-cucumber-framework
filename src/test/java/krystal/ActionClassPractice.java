package krystal;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClassPractice {

	public static void main(String[] args) {
//		draggable();
		checkBox();
	}

	static void checkBox() {

		WebDriver localDriver = new ChromeDriver();

		localDriver.get(null);
		localDriver.switchTo().frame(0);
		localDriver.findElement(By.id("checkbox-1")).click();
		boolean star_2 = localDriver.findElement(By.id("checkbox-1")).isSelected();
	}

	static void draggable() {

		WebDriver localDrive = selenium_0805.driverFactory();

		Actions ac = new Actions(localDrive);

		localDrive.get("https://jqueryui.com/droppable/");

//		localDrive.switchTo().frame(localDrive.findElement(By.tagName("iframe")));
//
//		ac.clickAndHold(localDrive.findElement(By.id("draggable"))).moveToLocation(200, -100).build();
//		ac.moveByOffset(200, -100).build().perform();

//		ac.dragAndDrop(localDrive.findElement(By.id("draggable")), localDrive.findElement(By.id("droppable")))
//		.build().perform();

		localDrive.switchTo().defaultContent();
		localDrive.findElement(By.linkText("Resizable")).click();
		localDrive.switchTo().defaultContent();
		localDrive.switchTo().frame(localDrive.findElement(By.tagName("iframe")));
		localDrive.switchTo().defaultContent();

		localDrive.findElement(By.xpath("//a[text()='Sortable']")).click();
		localDrive.switchTo().frame(localDrive.findElement(By.tagName("iframe")));
		ac.clickAndHold(localDrive.findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][5]")))
				.moveByOffset(0, -100).release().build().perform();
		ac.clickAndHold(localDrive.findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][7]")))
				.moveByOffset(0, -100).release().build().perform();
		ac.clickAndHold(localDrive.findElement(By.xpath("//li[@class='ui-state-default ui-sortable-handle'][3]")))
				.moveByOffset(0, -100).release().build().perform();

//		ac.clickAndHold(localDrive.findElement(By.className("ui-resizable-se"))).build().perform();
//
		localDrive.close();
		localDrive.quit();

	}

}
