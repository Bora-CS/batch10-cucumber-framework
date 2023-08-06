package hui_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClassPractice0805 {

	public static void main(String[] args) {
		action();
		checkBox();
	}

	private static void checkBox() {
		WebDriver localDriver = StudentRegistrationFormTest.driverFactory();
		try {
			localDriver.get("https://jqueryui.com/checkboxradio/");
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			localDriver.findElement(By.xpath("//label[@for='checkbox-1']")).click();
			boolean twoStars = localDriver.findElement(By.xpath("//label[@for='checkbox-1']")).isSelected();
			System.out.println(twoStars);
			Testkeys.pause(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			localDriver.close();
			localDriver.quit();
		}
	}

	private static void action() {
		WebDriver localDriver = StudentRegistrationFormTest.driverFactory();
		try {
			Actions ac = new Actions(localDriver);
			localDriver.get("https://jqueryui.com/droppable/");
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			Testkeys.pause(3);
			ac.dragAndDrop(localDriver.findElement(By.id("draggable")), localDriver.findElement(By.id("droppable")))
					.build().perform();

			Testkeys.pause(3);
			localDriver.switchTo().defaultContent();
			// resize
			localDriver.findElement(By.xpath("//a[text()='Resizable']")).click();
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			Testkeys.pause(3);
			ac.clickAndHold(localDriver.findElement(By.xpath(
					"//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']")))
					.moveByOffset(100, -50).build().perform();

			// drag
			Testkeys.pause(3);
			localDriver.switchTo().defaultContent();
			localDriver.findElement(By.xpath("//a[text()='Draggable']")).click();
			Testkeys.pause(3);
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			ac.clickAndHold(localDriver.findElement(By.id("draggable"))).moveByOffset(100, 50).build().perform();

			// sort
			Testkeys.pause(3);
			localDriver.switchTo().defaultContent();
			localDriver.findElement(By.xpath("//a[text()='Sortable']")).click();
			Testkeys.pause(3);
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			ac.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 4']")), 0, 50).build().perform();
			ac.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 6']")), 0, -50).build().perform();
			ac.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 3']")), 0, -50).build().perform();

			Testkeys.pause(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			localDriver.close();
			localDriver.quit();
		}
	}
}
