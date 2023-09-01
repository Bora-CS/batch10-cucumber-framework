package hui_automation.selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import hui_automation.utilities.Testkeys;

public class MinimalWebTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			String testUrl = "https://minimals.cc/";
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.get(testUrl);

			// login
			driver.findElement(By.xpath("//a[@href='/dashboard'][text()='Login']")).click();

			String email = driver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[1]"))
					.getText();
			String password = driver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[2]"))
					.getText();

			driver.findElement(By.name("email")).sendKeys(email);
			driver.findElement(By.name("password")).sendKeys(password);

			driver.findElement(By.xpath("//button[@type='submit']")).click();

			// dash board
			// select user/list
			driver.findElement(By.xpath("//span[contains(@class, 'MuiTypography')][text()='user']")).click();
			driver.findElement(By.xpath("//span[contains(@class, 'MuiTypography')][text()='list']")).click();

			// filter
			driver.findElement(By.xpath("//label[text()='Role']/following-sibling::div")).click();
			// choose Data Analyst and UX/UI Designer
			driver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();
			driver.findElement(By.xpath("//li[@data-value='UX/UI Designer']")).click();
			// exit role drop-down menu
			driver.findElement(By.id("menu-")).click();
			// validate top filter buttons
			String roleButtonXpah = "//div[contains(@class, 'MuiPaper-root')]/span[text()='Role:']/following-sibling::div/div[contains(@class, 'MuiButtonBase-root')]/span";
			List<WebElement> roleButtons = driver.findElements(By.xpath(roleButtonXpah));
			List<String> expectedTexts = new ArrayList<String>();
			expectedTexts.add("Data Analyst");
			expectedTexts.add("UX/UI Designer");
			for (WebElement roleButton : roleButtons) {
				if (!expectedTexts.contains(roleButton.getText())) {
					String unexpectedRole = roleButton.getText();
					throw new Exception(String.format("Unexpected role found: %s%d", unexpectedRole));
				}
				System.out.println("Top filer role: " + roleButton.getText());
			}
			// validate table cell values - role
			List<WebElement> roleCells = driver.findElements(By.xpath("//table/tbody/tr/td[5]"));
			for (WebElement roleCell : roleCells) {
				if (!expectedTexts.contains(roleCell.getText())) {
					String unexpectedRole = roleCell.getText();
					throw new Exception(String.format("Unexpected role found: %s%d", unexpectedRole));
				}
				System.out.println("Table cell role: " + roleCell.getText());
			}

			System.out.println("Test passed.");
			Testkeys.pause(driver, 3);
		} catch (Exception e) {
			System.out.println("Test failed.");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(driver);
			System.out.println("Test completed.");
		}
	} // main

}
