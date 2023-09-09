package hui_automation.selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import hui_automation.utilities.Testkeys;

public class ActionClassPractice0805 {

	public static void main(String[] args) {
		action();
//		checkBox();
	}

	private static void checkBox() {
		WebDriver localDriver = new ChromeDriver();
		try {
			localDriver.get("https://jqueryui.com/checkboxradio/");
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			localDriver.findElement(By.xpath("//label[@for='checkbox-1']")).click();
			boolean twoStars = localDriver.findElement(By.xpath("//label[@for='checkbox-1']")).isSelected();
			System.out.println(twoStars);
			Testkeys.pause(localDriver, 3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Testkeys.terminate(localDriver);
		}
	}

	private static void action() {
		WebDriver localDriver = new ChromeDriver();
		try {
			Actions act = new Actions(localDriver);
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			localDriver.manage().window().maximize();

			// practice web page
			localDriver.get("https://jqueryui.com/");

			// drop
			localDriver.findElement(By.xpath("//a[@href='https://jqueryui.com/droppable/']")).click();
			// inside iframe
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			Testkeys.pause(localDriver, 3);
			// from drag to drop
			act.dragAndDrop(localDriver.findElement(By.id("draggable")), localDriver.findElement(By.id("droppable")))
					.build().perform();
			Testkeys.pause(localDriver, 3); // pause to view the result

			// resize
			localDriver.switchTo().defaultContent();
			// change to another web page for resize practice
			localDriver.findElement(By.xpath("//a[@href='https://jqueryui.com/resizable/']")).click();
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			Testkeys.pause(localDriver, 3);
			act.clickAndHold(localDriver.findElement(By.xpath(
					"//div[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']")))
					.moveByOffset(100, -50).build().perform();
			Testkeys.pause(localDriver, 3); // pause to view the result

			// drag
			localDriver.switchTo().defaultContent();
			localDriver.findElement(By.xpath("//a[@href='https://jqueryui.com/draggable/']")).click();
			Testkeys.pause(localDriver, 3);
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			act.clickAndHold(localDriver.findElement(By.id("draggable"))).moveByOffset(100, 50).build().perform();
			Testkeys.pause(localDriver, 3); // pause to view the result

			System.out.println("All actions completed.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Testkeys.terminate(localDriver);
			System.out.println("We're done!");
		}
	}

	private static void actionSort() {
		WebDriver localDriver = new ChromeDriver();
		try {
			Actions act = new Actions(localDriver);
			localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			localDriver.manage().window().maximize();

			// practice web page
			localDriver.get("https://jqueryui.com/");

			// sort
			localDriver.findElement(By.xpath("//a[text()='Sortable']")).click();
			Testkeys.pause(localDriver, 3);
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			act.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 4']")), 0, 50).build().perform();
			act.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 6']")), 0, -50).build().perform();
			act.dragAndDropBy(localDriver.findElement(By.xpath("//li[text()='Item 3']")), 0, -50).build().perform();
			Testkeys.pause(localDriver, 3);

			System.out.println("Sorting completed.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Testkeys.terminate(localDriver);
			System.out.println("We're done!");
		}
	}

}
