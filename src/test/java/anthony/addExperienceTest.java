package anthony;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addExperienceTest {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.name("email")).sendKeys("anth0ny@gmail.com");
			driver.findElement(By.name("password")).sendKeys("PaSsWoRd123!");
			driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();
			Thread.sleep(5000);

			String titleText = driver.findElement(By.xpath("//h1[@class='large text-primary']")).getText();

			if (!titleText.equals("Dashboard")) {
				throw new Exception("Title text does not match." + 
			"\n Title text should be: " + titleText);
			}

			driver.findElement(By.xpath("//*[@href='/add-experience']")).click();

			String experienceIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']"))
					.getText();

			if (!experienceIntro.equals("Add An Experience")) {
				throw new Exception(
						"Experience text does not match." + 
				"\n Experience text should be: " + experienceIntro);
			}

			driver.findElement(By.name("title")).sendKeys("Noob Automator");
			driver.findElement(By.name("company")).sendKeys("ToraBech");
			driver.findElement(By.name("location")).sendKeys("BorAnnandale");
			driver.findElement(By.xpath("//*[@name='from']")).sendKeys("01-01-2023");
			driver.findElement(By.xpath("//*[@name='current']")).click();
			driver.findElement(By.xpath("//*/textarea[@name='description']"))
					.sendKeys("Sysout squares ▦,  " + "rectangles ▭, " + "triangle △, " + "and diamonds ❖");
			driver.findElement(By.xpath("//*/input[@type='submit']")).click();

			Thread.sleep(7000);

			System.out.println("Test passed");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("Something went wrong!!! " + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}