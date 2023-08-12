package xiaolipractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.sun.org.apache.xpath.internal.compiler.Keywords;

public class DeleteExperience {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String username = "xiaoli1005@yahoo.com";
		String password = "Goodbora425!";

		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			Thread.sleep(3000);

			int count = 0;
			while (true) {
				try {
					driver.findElement(By.xpath(
							"//h2[text()='Experience Credentials']/following-sibling::table[1]//button[@class='btn-danger']"))
							.click();
					Keywords.wait(2);
					driver.navigate().refresh();
					count++;

				} catch (NoSuchElementException e) {
					break;
				}
			}

			System.out.println("Test passed, deleted "+ count + " experiences.");

		} catch (Exception e) {
			System.out.println("Test Failed");
			e.printStackTrace();
			System.out.println("Reason:" + e.getMessage());

		} finally {
			driver.close();
			driver.quit();
		}

	}

}
