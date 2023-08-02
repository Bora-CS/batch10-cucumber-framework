package ardal_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addEducation {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("ardal002713@gmail.com");
			driver.findElement(By.name("password")).sendKeys("Ardal002713");
			driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
			Thread.sleep(3000);

			// first validation on login page
			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();

			if (!titleText.equals("Dashboard")) {
				throw new Exception("There is a bug, Title text doesn't match.");
			}

			// start testing adding education
			driver.findElement(By.xpath("//*[@href='/add-education']")).click();

			// second validation to see if we are on add education page

			String educationIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']")).getText();

			if (!educationIntro.equals("Add Your Education")) {
				throw new Exception("There is a bug,educationIntro text doesn't match.");
			}

			// now testing add education functions
			driver.findElement(By.name("school")).sendKeys("British Columbia Institute Of Technology");
			driver.findElement(By.name("degree")).sendKeys("Bachelor");
			driver.findElement(By.name("fieldofstudy")).sendKeys("Civil Engineering");
			driver.findElement(By.xpath("//*[@name='from']")).sendKeys("2015-09-03");
			driver.findElement(By.xpath("//*[@name='to']")).sendKeys("2016-05-27");
			driver.findElement(By.xpath("//*/textarea[@name='description']"))
					.sendKeys("Picked such a hard major and had to drop");
			driver.findElement(By.xpath("//*/input[@type='submit']")).click();

			// since adding uploading information is going to take a while hard coded wait
			// time added

			Thread.sleep(20000);

			// third validation to see if education was added successfully
			driver.findElement(By.xpath("//td[text()='Bachelor']"));
			driver.findElement(By.xpath("//td[text()='British Columbia Institute Of Technology']"));

			System.out.println("Test passed");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("There is a exception:" + e.getMessage());
		} finally {
			driver.quit();
		}
	}

}
