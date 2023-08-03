package hui10;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperienceTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();

		String expCompany = "Walmart";
		String expTitle = "Cashier";
		String expStartTime = "2010/06/06";
		String expEndTime = "2012/11/11";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// adding experience
			testDriver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			testDriver.findElement(By.xpath("//input[@name='title']")).sendKeys(expTitle);
			testDriver.findElement(By.xpath("//input[@name='company']")).sendKeys(expCompany);
			testDriver.findElement(By.xpath("//input[@name='location']")).sendKeys("Manassas, Virginia");

			testDriver.findElement(By.xpath("//input[@name='from']")).sendKeys(TestAsst.findInputDateStrYMD(expStartTime));
			testDriver.findElement(By.xpath("//input[@name='to']")).sendKeys(TestAsst.findInputDateStrYMD(expEndTime));
			testDriver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Payment collection.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);

			// validation
			String actCompany = testDriver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[1]")).getText();
			String actTitle = testDriver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[2]")).getText();
			String actStartTime = testDriver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[3]/time[1]")).getText();
			String actEndTime = testDriver.findElement(By.xpath("//table[1]/tbody/tr[1]/td[3]/time[2]")).getText();

			if (!actCompany.equals(expCompany)) {
				throw new Exception(
						"Company names mismatch\nExpected name: " + expCompany + 
						"\nActual name: " + actCompany + "\n");
			}

			if (!actTitle.equals(expTitle)) {
				throw new Exception(
						"Company titles mismatch\nExpected title: " + expTitle + 
						"\nActual title: " + actTitle + "\n");
			}
			
			if (!actStartTime.equals(expStartTime)) {
				throw new Exception(
						"Start time mismatch\nExpected start time: " + expStartTime + 
						"\nActual start time: " + actStartTime + "\n");
			}
			
			if (!actStartTime.equals(expStartTime)) {
				throw new Exception(
						"End time mismatch\nExpected end time: " + expEndTime + 
						"\nActual end time: " + actEndTime + "\n");
			}

			System.out.println("Test passed.");
			System.out.println("Company: " + actCompany);
			System.out.println("Title: " + actTitle);
			System.out.println("Start Date: " + actStartTime);
			System.out.println("End: " + actEndTime);
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			testDriver.quit();
		}
	}

}
