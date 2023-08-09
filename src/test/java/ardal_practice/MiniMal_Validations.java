package ardal_practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Keywords;

public class MiniMal_Validations {

	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		String username = "demo@minimals.cc";
		String password = "demo1234";

		try {
			driver.get("https://minimals.cc/auth/amplify/login");

			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
			driver.findElement(By.xpath("//button[@type='submit']")).click();

			driver.findElement(By.xpath("//span[text()='user']")).click();
			driver.findElement(By.xpath("//a[@href='/dashboard/user/list']")).click();

			driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]//span[text()='Role']/../../.."))
					.click();

			driver.findElement(By.xpath("//li[@data-value='UX/UI Designer']")).click();
			driver.findElement(By.xpath("//li[@data-value='Data Analyst']")).click();

			driver.findElement(By.tagName("body")).click();

			Keywords.wait(2);

//	Validation
			String expectedFilter1 = "UX/UI Designer";
			String expectedFilter2 = "Data Analyst";

			String actualFilter1 = driver.findElement(By.xpath("//span[normalize-space()='UX/UI Designer']")).getText();
			String actualFilter2 = driver.findElement(By.xpath("//span[normalize-space()='Data Analyst']")).getText();

			if (!expectedFilter1.equals(actualFilter1) || !expectedFilter2.equals(actualFilter2)) {
				System.out.println("Expected: " + expectedFilter1 + expectedFilter2 + "\nActual: " + actualFilter1
						+ actualFilter2);
				throw new Exception("Filter options doesn't match ");

			}

			String tableRows = "//*[@class='MuiTable-root css-1pmdcrr']/tbody/tr";

			List<WebElement> rows = driver.findElements(By.xpath(tableRows));

			List<String> actualRoles = new ArrayList<>();

			for (WebElement row : rows) {
				actualRoles.add(row.findElement(By.xpath(tableRows + "/td[5]")).getText());

			}

			if (!actualRoles.get(0).equals(actualFilter1) || !actualRoles.get(1).equals(actualFilter1)) {
				throw new Exception("Expected filter role not found");
			}

			System.out.println("Test Passed");

		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			driver.close();
			driver.quit();
		}

	}

}
