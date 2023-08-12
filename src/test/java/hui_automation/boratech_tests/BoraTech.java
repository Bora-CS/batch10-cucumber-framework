package hui_automation.boratech_tests;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

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
		// go to dash board
		driver.navigate().to("https://boratech-practice-app.onrender.com/dashboard");
		driver.findElement(By.xpath("//a[@href='/add-experience']")).click();

		// adding experience
		driver.findElement(By.xpath("//input[@name='company']")).sendKeys(exp.company);
		driver.findElement(By.xpath("//input[@name='title']")).sendKeys(exp.title);
		driver.findElement(By.xpath("//input[@name='location']")).sendKeys(exp.location);

		if (exp.startDate.length() > 0)
			driver.findElement(By.xpath("//input[@name='from']"))
					.sendKeys(Testkeys.findDateInputStrMDY(exp.startDate, "yyyy/MM/dd"));
		if (exp.current)
			driver.findElement(By.name("current")).click();
		else
			driver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(Testkeys.findDateInputStrMDY(exp.endDate, "yyyy/MM/dd"));
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(exp.description);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.jsViewTop(driver);
	}

	public static void validateExperience(WebDriver driver, Experience exp) throws Exception {
		if (exp.isTestPositive)
			addExpPosValidation(driver, exp);
		else
			addExpNegValidation(driver, exp);
	}

	private static void addExpPosValidation(WebDriver driver, Experience exp) throws Exception {
		String url = driver.getCurrentUrl();
		if (!url.endsWith("dashboard"))
			driver.get("https://boratech-practice-app.onrender.com/dashboard");
		Testkeys.pause(1);

		boolean targetRow = false;
		By expTableLocator = RelativeLocator.with(By.tagName("table"))
				.below(By.xpath("//h2[text()='Experience Credentials']"));
		// find all the experience table rows
		List<WebElement> rows = driver.findElement(expTableLocator).findElements(By.xpath("tbody/tr"));
		for (WebElement row : rows) {
			String company = row.findElement(By.xpath("td[1]")).getText();
			String title = row.findElement(By.xpath("td[2]")).getText();
			if (company.equals(exp.company) && title.equals(exp.title))
				targetRow = true;
		}

		// check targetRow
		if (!targetRow)
			throw new Exception(String.format("Added experience not found: [%s, %s]%n", exp.company, exp.title));
	}

	private static void addExpNegValidation(WebDriver driver, Experience exp) throws Exception {
		// find error elements
		List<WebElement> alerts = driver.findElements(By.cssSelector(".alert.alert-danger"));
		// check for size match
		if (exp.messages.size() != alerts.size())
			throw new Exception(
					String.format("Actual error numbers mismatch.%nExpected number(s): %d%nActual number(s): %d%n",
							exp.messages.size(), alerts.size()));
		
		// find actual error texts
		List<String> alertTexts = new ArrayList<>();
		for (WebElement alert : alerts)
			alertTexts.add(alert.getText());
		
		// validation
		for (String msg : exp.messages) {
			if (!alertTexts.contains(msg))
				throw new Exception("Expected error message not found: " + msg);
		}
	}

	public static void addEducation(WebDriver driver, Education edu) {
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		// adding education
		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(edu.school);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(edu.degree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(edu.fieldofstudy);

		driver.findElement(By.xpath("//input[@name='from']"))
				.sendKeys(Testkeys.findDateInputStrMDY(edu.startDate, "yyyy/MM/dd"));
		if (edu.current)
			driver.findElement(By.name("current")).click();
		else
			driver.findElement(By.xpath("//input[@name='to']"))
					.sendKeys(Testkeys.findDateInputStrMDY(edu.endDate, "yyyy/MM/dd"));
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(edu.description);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.jsViewTop(driver);
	}

	public static void deleteAllExperiences(WebDriver driver) throws Exception {
		String url = driver.getCurrentUrl();
		if (!url.endsWith("dashboard"))
			driver.get("https://boratech-practice-app.onrender.com/dashboard");
		Testkeys.pause(1);

		// delete everything
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
