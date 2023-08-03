/**
 * 
 */
package rubenTrials;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;



public class IHaveADream {


	public static void main(String[] args) {
		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// fake attempt at adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			
			// validation
			List<WebElement> errBlocks = testDriver.findElements(By.cssSelector(".alert.alert-danger"));
			if (errBlocks.size() == errMessages.size()) {
				for (WebElement errBlock : errBlocks) {
					if (!errMessages.contains(errBlock.getText()))
						throw new Exception(
								"Such error message does not exist in the system: [" + errBlock.getText() + "]");
				}
			} else {
				String testFailStr = "Error messages mismatch\n";
				testFailStr += "Expected numbers of error messages: " + errMessages.size() + "\n";
				testFailStr += "Actual numbers of error messages: " + errBlocks.size() + "\n";
				throw new Exception(testFailStr);
			}
			
			TestAsst.sleep(3);
			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Bad shit happened!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}
		
		
	}

}
