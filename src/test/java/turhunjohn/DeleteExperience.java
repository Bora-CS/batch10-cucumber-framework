package turhunjohn;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.BoraTech;
import utilities.Keywords;

public class DeleteExperience {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";

		try {
			
			 BoraTech.login(driver, username, password);
			 BoraTech.deleteAllExperiences(driver);

			System.out.println("Test Passed");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}