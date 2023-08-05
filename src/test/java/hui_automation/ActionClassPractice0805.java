package hui_automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionClassPractice0805 {

	public static void main(String[] args) {
		drag();
	}

	private static void drag() {
		WebDriver localDriver = StudentRegistrationFormTest.driverFactory();
		try {
			Actions ac = new Actions(localDriver);
			localDriver.get("https://jqueryui.com/draggable/");
			localDriver.switchTo().frame(localDriver.findElement(By.tagName("iframe")));
			ac.clickAndHold(localDriver.findElement(By.id("draggable"))).moveByOffset(50, 100).build().perform();
			TestAsst.sleep(3);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			localDriver.close();
			localDriver.quit();
		}
	}
}
