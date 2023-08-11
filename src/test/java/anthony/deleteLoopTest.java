package anthony;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.Keywords;

public class deleteLoopTest {

	public static void delete4EaLoop(WebDriver driver) throws InterruptedException {
		int count = 0;
		try {

			List<WebElement> totalDeleteButtons = driver.findElements(By.xpath(
					"//h2[text()='Experience Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"
							+ " | "
							+ "//h2[text()='Experience Credentials']/following-sibling::table[2]//button[@class='btn btn-danger']"));

			for (WebElement deleteButton : totalDeleteButtons) {
				deleteButton.click();
				Keywords.wait(1);
				count++;

			}
		} catch (Exception e) {

		}
		if (count == 0) {
			System.out.println("nothing to delete");
		} else {
			System.out.println("Deletion success!" + "("+count+")");
		}

	}
}