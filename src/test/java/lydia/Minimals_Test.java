package lydia;

	import java.time.Duration;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

import lydia.utilities.Minimals;
import utilities.Keywords;

	public class Minimals_Test {

		public static void main(String[] args) throws InterruptedException {

			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
			driver.manage().window().maximize();

			String email = "demo@minimals.cc";
			String password = "demo1234";

			try {
				
				Minimals.login(driver, email, password);
				driver.findElement(By.xpath("//span[text()='user']")).click();
				driver.findElement(By.xpath("//span[text()='list']")).click();

				driver.findElement(By.xpath("//div[contains(@class, 'MuiInputBase-root')]//span[text()='Role']/../../.."))
						.click();

				// Validating Roles

				String displayedRole = driver.findElement(By.xpath("//div[@aria-haspopup='listbox']")).getText();
				String actualRole = driver.findElement(By.xpath("//td[contains(@class, 'MuiTableCell-root')][5]")).getText();
				List<WebElement> displayedRoleBoxes = driver.findElements(By.xpath("//li//input[@type='checkbox']"));
				int count = 0;

				for (WebElement displayedRoleBox : displayedRoleBoxes) {
					displayedRoleBox.click();
					System.out.println("actural: "+ actualRole + " displayed: " +displayedRole);
					Keywords.wait(2);
					if (displayedRole.equals(actualRole)) {
						System.out.println("Found an user for " + displayedRole + ".");
						count++;
						displayedRoleBox.click();
						Keywords.wait(2);
					} else {
						System.out.println("Test failed!" + "\n There's not user for " + displayedRole + ".");
					}
					System.out.println("Total find " + count + "roles of users.");
				}

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Test Failed" + e.getMessage());
			} finally {

				driver.close();
				driver.quit();

			}

		}
	}

