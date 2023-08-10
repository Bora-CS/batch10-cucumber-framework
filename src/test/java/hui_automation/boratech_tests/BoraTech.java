package hui_automation.boratech_tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import hui_automation.Testkeys;
import hui_automation.pojo.Education;
import hui_automation.pojo.Experience;

public class BoraTech {

	public static void login(WebDriver driver, String email, String password) {
		driver.get("https://boratech-practice-app.onrender.com/login");
		driver.findElement(By.name("email")).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.xpath("//input[@type='submit']")).click();
	}

	public static void addExperience(WebDriver driver, Experience exp) {
		driver.findElement(By.xpath("//a[@href='/add-experience']")).click();

		// adding experience
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(exp.expCompany);
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(exp.expTitle);
		driver.findElement(By.xpath("//input[@name='location']")).sendKeys(exp.location);

		driver.findElement(By.xpath("//input[@name='from']"))
				.sendKeys(Testkeys.findDateInputStrMDY(exp.expStartDate, "yyyy/MM/dd"));
		if (exp.current)
			driver.findElement(By.name("current")).click();
		else
			driver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(Testkeys.findDateInputStrMDY(exp.expEndDate, "yyyy/MM/dd"));
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(exp.description);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.jsViewTop(driver);
	}

	public static void addEducation(WebDriver driver, Education edu) {
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		// adding education
		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(edu.expSchool);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(edu.expDegree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(edu.fieldofstudy);

		driver.findElement(By.xpath("//input[@name='from']"))
				.sendKeys(Testkeys.findDateInputStrMDY(edu.expStartDate, "yyyy/MM/dd"));
		if (edu.current)
			driver.findElement(By.name("current")).click();
		else
			driver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(Testkeys.findDateInputStrMDY(edu.expEndDate, "yyyy/MM/dd"));
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(edu.description);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.jsViewTop(driver);
	}
	
	public static void deleteAllExperiences(WebDriver driver) throws Exception {
		String expDeleteXpath = "//h2[text()='Experience Credentials']/following-sibling::table[1]/tbody/tr/td/button[text()='Delete']";
		By locator = By.xpath(expDeleteXpath);
		int count = 0;
		while (Testkeys.containsElement(driver, locator)) {
			if (driver.findElements(locator).size() > 0)
				driver.findElement(locator).click();
			driver.navigate().refresh();
			count++;
		}
		if (count == 0)
			throw new Exception("Nothing was deleted");
		System.out.printf("Deleted %d experience(s).%n", count);
	}

	public static void deleteAllEducations(WebDriver driver) throws Exception {
		String eduDeleteXpath = "//h2[text()='Education Credentials']/following-sibling::table[1]/tbody/tr/td/button[text()='Delete']";
		By locator = By.xpath(eduDeleteXpath);
		int count = 0;
		while (Testkeys.containsElement(driver, locator)) {
			if (driver.findElements(locator).size() > 0)
				driver.findElement(locator).click();
			driver.navigate().refresh();
			count++;
		}
		if (count == 0)
			throw new Exception("Nothing was deleted");
		System.out.printf("Deleted %d education(s).%n", count);
	}

}
