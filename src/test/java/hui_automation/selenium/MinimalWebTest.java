package hui_automation.selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import hui_automation.utilities.Testkeys;

public class MinimalWebTest {

	public static void main(String[] args) {
		WebDriver testDriver = Testkeys.getChromeDriver();
		try {
			String testUrl = "https://minimals.cc/";
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			testDriver.manage().window().maximize();
			testDriver.get(testUrl);

			// login
			testDriver.findElement(By.xpath("//a[@href='/dashboard'][text()='Login']")).click();

			String email = testDriver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[1]"))
					.getText();
			String password = testDriver.findElement(By.xpath("//div[contains(@class, 'MuiAlert-message')]/strong[2]"))
					.getText();

			testDriver.findElement(By.name("email")).sendKeys(email);
			testDriver.findElement(By.name("password")).sendKeys(password);

			testDriver.findElement(By.xpath("//button[@type='submit']")).click();

			// dash board
			// select user/list
			testDriver.findElement(By.xpath("//span[contains(@class, 'MuiTypography')][text()='user']")).click();
			testDriver.findElement(By.xpath("//span[contains(@class, 'MuiTypography')][text()='list']")).click();

			// filter
			testDriver.findElement(By.xpath("//label[text()='Role']/following-sibling::div")).click();
			// choose Data Analyst and UX/UI Designer
			testDriver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();
			testDriver.findElement(By.xpath("//li[@data-value='UX/UI Designer']")).click();
			// exit role drop-down menu
			testDriver.findElement(By.id("menu-")).click();
			// validate top filter buttons
			String roleButtonXpah = "//div[contains(@class, 'MuiPaper-root')]/span[text()='Role:']/following-sibling::div/div[contains(@class, 'MuiButtonBase-root')]/span";
			List<WebElement> roleButtons = testDriver.findElements(By.xpath(roleButtonXpah));
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
			List<WebElement> roleCells = testDriver.findElements(By.xpath("//table/tbody/tr/td[5]"));
			for (WebElement roleCell : roleCells) {
				if (!expectedTexts.contains(roleCell.getText())) {
					String unexpectedRole = roleCell.getText();
					throw new Exception(String.format("Unexpected role found: %s%d", unexpectedRole));
				}
				System.out.println("Table cell role: " + roleCell.getText());
			}

			System.out.println("Test passed.");
			Testkeys.pause(3);
		} catch (Exception e) {
			System.out.println("Test failed.");
			e.printStackTrace();
		} finally {
			Testkeys.terminate(testDriver);
			System.out.println("Test completed.");
		}
	} // main

}
