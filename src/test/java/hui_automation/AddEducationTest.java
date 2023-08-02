package hui_automation;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AddEducationTest {

	public static void main(String[] args) {
		WebDriver testDriver = new ChromeDriver();
		
		String expSchool = "George Mason University";
		String expDegree = "Bachelor's Degree";
		String expStartTime = "2008/01/31";
		String expEndTime = "2010/05/15";

		try {
			testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
			testDriver.navigate().to("https://boratech-practice-app.onrender.com/");
			testDriver.findElement(By.linkText("Login")).click();

			testDriver.findElement(By.xpath("//input[@name='email']")).sendKeys("hui-pretender@outlook.com");
			testDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("Hui123456");
			testDriver.findElement(By.xpath("//input[@type='submit']")).click();

			// adding education
			testDriver.findElement(By.xpath("//a[@href='/add-education']")).click();
			testDriver.findElement(By.xpath("//input[@name='school']")).sendKeys(expSchool);
			testDriver.findElement(By.xpath("//input[@name='degree']")).sendKeys(expDegree);
			testDriver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys("Biology");
			
			testDriver.findElement(By.xpath("//input[@name='from']")).sendKeys(TestAsst.findInputDateStrYMD(expStartTime));
			testDriver.findElement(By.xpath("//input[@name='to']")).sendKeys(TestAsst.findInputDateStrYMD(expEndTime));
			testDriver.findElement(By.xpath("//textarea[@name='description']")).sendKeys("Practice the scientific study of life.");

			testDriver.findElement(By.xpath("//input[@type='submit']")).click();
			Thread.sleep(3000);

			// validation
			String actSchool = testDriver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[1]")).getText();
			String actDegree = testDriver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[2]")).getText();
			String actStartTime = testDriver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[3]/time[1]")).getText();
			String actEndTime = testDriver.findElement(By.xpath("//table[2]/tbody/tr[1]/td[3]/time[2]")).getText();

			if (!actSchool.equals(expSchool)) {
				throw new Exception(
						"School names mismatch\nExpected name: " + expSchool + 
						"\nActual name: " + actSchool + "\n");
			}

			if (!actDegree.equals(expDegree)) {
				throw new Exception(
						"Degrees mismatch\nExpected degree: " + expDegree + 
						"\nActual title: " + actDegree + "\n");
			}
			

			if (!actStartTime.equals(expStartTime)) {
				throw new Exception("Start time mismatch\nExpected start time: " + expStartTime
						+ "\nActual start time: " + actStartTime + "\n");
			}

			if (!actStartTime.equals(expStartTime)) {
				throw new Exception("End time mismatch\nExpected end time: " + expEndTime + "\nActual end time: "
						+ actEndTime + "\n");
			}

			System.out.println("Test passed.");
			System.out.println("School: " + actSchool);
			System.out.println("Degree: " + actDegree);
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
