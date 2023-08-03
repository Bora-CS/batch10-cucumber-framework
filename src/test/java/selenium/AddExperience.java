package selenium;

<<<<<<< HEAD
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
=======
import java.sql.Timestamp;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
>>>>>>> master
import org.openqa.selenium.chrome.ChromeDriver;

public class AddExperience {

	public static void main(String[] args) {
<<<<<<< HEAD
		WebDriver driver = new ChromeDriver();

		String username = "turhunjuma@gmail.com";
		String password = "turhunjuma";
		String jobTitle = "Information Security Officer";
		String company = "AWS";
		String location = "Chantilly, VA ";
		String from = "10/05/2021";
		String to = "10/14/2022 ";
		boolean current = false;
		String description = "Conduct inspections, audits and reviews in your area of responsibility";

		try {
			driver.get("https://boratech-practice-app.onrender.com/");
			// driver.findElement(By.linkText("Login")).click();
			driver.findElement(By.xpath("//a[@class='btn btn-light']")).click();

=======

		WebDriver driver = new ChromeDriver();

		String username = "muradil.erkin@boratechschool.com";
		String password = "Boratech";
		String jobTitle = "Senior Cashier";
		String company = "Chik-fil-a" + getTimeStamp();
		String location = "Fairfax, VA";
		String from = "08/13/2020";
		String to = "";
		boolean current = true;
		String description = "Bro I know I still count money, but I eat 'healthy' nowdays";

		try {
			driver.get("https://boratech-practice-app.onrender.com/login");
>>>>>>> master
			driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
			wait(2);

			driver.findElement(By.xpath("//a[@href='/add-experience']")).click();
			wait(2);

<<<<<<< HEAD
			String experienceIntro = driver.findElement(By.xpath("//section/h1[@class='large text-primary']"))
					.getText();
			if (!experienceIntro.equals("Add An Experience")) {
				throw new Exception(
						" An Experience text doesn't match." + "\n An Experience text should be: " + experienceIntro);
			}

=======
>>>>>>> master
			driver.findElement(By.xpath("//input[@name='title']")).sendKeys(jobTitle);
			driver.findElement(By.xpath("//input[@name='company']")).sendKeys(company);
			driver.findElement(By.xpath("//input[@name='location']")).sendKeys(location);
			driver.findElement(By.xpath("//input[@name='from']")).sendKeys(from);
			if (current) {
				driver.findElement(By.xpath("//input[@name='current']")).click();
			} else {
				driver.findElement(By.xpath("//input[@name='to']")).sendKeys(to);
<<<<<<< HEAD
				driver.findElement(By.tagName("textarea")).sendKeys(description);
				driver.findElement(By.xpath("//input[@type='submit']")).click();
				wait(2);

				System.out.println("Test Passed");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.quit();
		}
=======
			}
			driver.findElement(By.tagName("textarea")).sendKeys(description);
			driver.findElement(By.xpath("//input[@type='submit']")).click();
			wait(2);

			String tableXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]";
			String tableRowXpath = tableXpath + "/tbody/tr";

			List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));

			boolean found = false;
			for (WebElement row : rows) {
				List<WebElement> cells = row.findElements(By.tagName("td"));
				String actualCompany = cells.get(0).getText();
				String actualJobTitle = cells.get(1).getText();
				if (jobTitle.equals(actualJobTitle) && company.equals(actualCompany)) {
					found = true;
					break;
				}
			}

			if (!found) {
				throw new Exception("The newly entered experience was not found");
			}

			System.out.println("Test Passed");
		} catch (Exception e) {
			System.out.println("Test Failed");
			System.out.println("Reason: " + e.getMessage());
		} finally {
			driver.close();
			driver.quit();
		}

>>>>>>> master
	}

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}
<<<<<<< HEAD
}
=======

	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "";
	}

}
>>>>>>> master
