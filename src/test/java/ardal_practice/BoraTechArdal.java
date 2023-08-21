package ardal_practice;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import ardal_practice.pojo.Education;
import ardal_practice.pojo.Experience;

public class BoraTechArdal {

	public static void login(WebDriver driver, String username, String password) throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.get("https://boratech-practice-app.onrender.com/login");

		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password + Keys.ENTER);
	}

	public static void deleteAllEducations(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Education Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No education found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " education(s).");
		}
	}

	public static void addEducations(WebDriver driver, Education education) throws Exception {
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(education.school);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(education.degree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(education.fieldofstudy);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(education.from);
		if (education.current) {
			driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(education.to);
		}
		driver.findElement(By.tagName("textarea")).sendKeys(education.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

		String tableXpath = "//h2[text()='Education Credentials']/following-sibling::table[1]";
		String tableRowXpath = tableXpath + "/tbody/tr";

		List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));

		boolean found = false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String actualSchool = cells.get(0).getText();
			String actualDegree = cells.get(1).getText();
			if (education.school.equals(actualSchool) && education.degree.equals(actualDegree)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The newly entered education was not found");
		}

	}

	public static void addExperience(WebDriver driver, Experience experience) throws Exception {
		driver.findElement(By.xpath("//a[@href='/add-experience']")).click();

		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(experience.title);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(experience.company);
		driver.findElement(By.xpath("//input[@name='location']")).sendKeys(experience.location);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(experience.from);
		if (experience.current) {
			driver.findElement(By.xpath("//input[@name='current']")).click();
		} else {
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(experience.to);
		}
		driver.findElement(By.tagName("textarea")).sendKeys(experience.description);
		driver.findElement(By.xpath("//input[@type='submit']")).click();

	}

	public static void deleteAllExperiences(WebDriver driver) throws InterruptedException {
		int count = 0;
		while (true) {
			try {
				driver.findElement(By.xpath(
						"//h2[text()='Experience Credentials']/following-sibling::table[1]//button[@class='btn btn-danger']"))
						.click();
				count++;
			} catch (NoSuchElementException e) {
				break;
			}
		}
		if (count == 0) {
			System.out.println("No experience found that can be deleted.");
		} else {
			System.out.println("Successfully deleted " + count + " experience(s).");
		}
	}

	public static void experienceValidation(WebDriver driver, Experience experience) throws Exception {
		if (experience.isHappyPath) {
			experienceValidationHappyPath(driver, experience);
		} else {
			experienceValidationSadPath(driver, experience);

		}

	}

	private static void experienceValidationHappyPath(WebDriver driver, Experience experience) throws Exception {
		String tableXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]";
		String tableRowXpath = tableXpath + "/tbody/tr";

		List<WebElement> rows = driver.findElements(By.xpath(tableRowXpath));

		boolean found = false;
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			String actualCompany = cells.get(0).getText();
			String actualTitle = cells.get(1).getText();
			if (experience.company.equals(actualCompany) && experience.title.equals(actualTitle)) {
				found = true;
				break;
			}
		}

		if (!found) {
			throw new Exception("The newly entered experience was not found");
		}

	}

	private static void experienceValidationSadPath(WebDriver driver, Experience experience) throws Exception {
		List<WebElement> errorAlerts = driver.findElements(By.xpath("//div[@class='alert alert-danger']"));
		List<String> actualAlerts = new ArrayList<>();
		for (WebElement errorAlert : errorAlerts) {
			actualAlerts.add(errorAlert.getText());
		}

		if (actualAlerts.size() != experience.alerts.size()) {
			String output = "Number of alerts don't match\n" + "Expected: " + experience.alerts + "\n" + "Actual: "
					+ actualAlerts;
			throw new Exception(output);
		}

		for (String expectedAlerts : experience.alerts) {
			if (!actualAlerts.contains(expectedAlerts)) {
				throw new Exception("Expected error not found - [" + expectedAlerts + "]");
			}
		}
	}

}
