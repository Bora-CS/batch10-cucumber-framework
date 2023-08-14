package turhunjohn;

import java.time.Duration;
import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class Minimals_filteruser {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		String username = "demo@minimals.cc";
		String password = "demo1234";

		try {
			driver.get("https://minimals.cc/auth/amplify/login/");
			Keywords.wait(1);

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

			driver.findElement(By.xpath("//span[text()='user']")).click();
			driver.findElement(By.xpath("//span[text()='list']")).click();

			driver.findElement(By.xpath("//div[@class='MuiFormControl-root css-4o91m5']")).click();
			driver.findElement(By.xpath("//li[@data-value='UX/UI Designer']")).click();
			driver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();
			driver.findElement(By.xpath("//li[@data-value='Project Manager']")).click();
			driver.findElement(By.xpath("//li[@data-value='Software Developer']")).click();

			driver.findElement(By.tagName("body")).click();

			
			// Validate top filter role buttons
		    String roleButtonXpath = "//div[contains(@class, 'MuiPaper-root')]/span[text()='Role:']/following-sibling::div/div[contains(@class,'MuiButtonBase-root')]/span";
			List<WebElement> roleButtons = driver.findElements(By.xpath(roleButtonXpath));

			List<String> ecpectedTexts = new ArrayList<String>();
			ecpectedTexts.add("UX/UI Designer");
			ecpectedTexts.add("Data Analyst");
			ecpectedTexts.add("Project Manager");
			ecpectedTexts.add("Software Developer");

			
			for (WebElement roleButton : roleButtons) {
				if (!ecpectedTexts.contains(roleButton.getText())) {
					throw new Exception("Ecpected Texts do not match");
				}
				System.out.println(" Top filter role button(s) : " + roleButton.getText());
			}

			String roleCellsXpath = "//td[text()='UX/UI Designer']";
			List<WebElement> roleCells = driver.findElements(By.xpath(roleCellsXpath));

			//Validate table cell role 
			for (WebElement roleCell : roleCells) {
				if (!ecpectedTexts.contains(roleCell.getText())) {
					throw new Exception("Ecpected Texts do not match");

				}
				System.out.println(" Table cell role is : " + roleCell.getText());

			}
			Keywords.wait(2);

			System.out.println(" Test passed.");

		}

		catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Test failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
	}
}