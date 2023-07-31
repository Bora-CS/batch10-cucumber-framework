package ardal_practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class addExperience {

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

			// start testing adding experience
			driver.findElement(By.xpath("//*[@href='/add-experience']")).click();

			// second validation to see if we are on add experience page

			String experienceIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']"))
					.getText();

			if (!experienceIntro.equals("Add An Experience")) {
				throw new Exception("There is a bug,experienceIntro text doesn't match.");
			}

			// now testing add experience functions
			driver.findElement(By.name("title")).sendKeys("Autobody painter");
			driver.findElement(By.name("company")).sendKeys("Mercedes-Benz Collision Center");
			driver.findElement(By.name("location")).sendKeys("Vancouver,Canada");
			driver.findElement(By.xpath("//*[@name='from']")).sendKeys("2019-10-20");
			driver.findElement(By.xpath("//*[@name='current']")).click();
			driver.findElement(By.xpath("//*/textarea[@name='description']"))
					.sendKeys("Making your damaged high-end car look brand new again.");
			driver.findElement(By.xpath("//*/input[@type='submit']")).click();

			// since adding uploading information is going to take a while hard coded wait
			// time added

			Thread.sleep(20000);

			// third validation to see if experience was added successfully
			driver.findElement(By.xpath("//*/td[text()='Autobody painter']"));
			driver.findElement(By.xpath("//*/td[text()='Mercedes-Benz Collision Center']"));

			System.out.println("Test passed");
		} catch (Exception e) {
			e.getStackTrace();
			System.out.println("There is a exception:" + e.getMessage());
		} finally {
			driver.quit();
		}

	}

}
