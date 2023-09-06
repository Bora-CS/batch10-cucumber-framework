package hui_automation.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import hui_automation.pages.bora_tech.AddExperiencePage;
import hui_automation.pages.bora_tech.DashboardPage;
import hui_automation.pages.bora_tech.LoginPage;
import hui_automation.pojos.Education;
import hui_automation.pojos.Experience;

public class BoraTech {

	public static void login(WebDriver driver, String email, String password) {
		driver.get("https://boratech-practice-app.onrender.com/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(email, password);
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("dashboard"));
	}

	public static void addExperience(WebDriver driver, Experience exp) {
		// go to dash board
		driver.get("https://boratech-practice-app.onrender.com/dashboard");
//		DashboardPage.AddExperienceButton(driver).click();

		// adding experience
//		AddExperiencePage.companyInputBox(driver).sendKeys(exp.company);
//		AddExperiencePage.titleInputBox(driver).sendKeys(exp.title);
//		AddExperiencePage.locationInputBox(driver).sendKeys(exp.location);
//		AddExperiencePage.fromDateInputBox(driver).sendKeys(exp.startDate);
//		if (exp.current)
//			AddExperiencePage.currentCheckBox(driver).click();
//		else
//			AddExperiencePage.toDateInputBox(driver).sendKeys(exp.endDate);
//		AddExperiencePage.descriptionInputBox(driver).sendKeys(exp.description);
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

		// wait for the dash board page
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("dashboard"));

		// locate experience table
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Experience Credentials']"))));
		boolean targetRow = false;
		By expTableLocator = RelativeLocator.with(By.tagName("table"))
				.below(By.xpath("//h2[text()='Experience Credentials']"));
		Testkeys.pause(driver, 3);
		// find all the experience table rows
		List<WebElement> rows = driver.findElement(expTableLocator).findElements(By.xpath("tbody/tr"));
		for (WebElement row : rows) {
			String company = row.findElement(By.xpath("td[1]")).getText();
			String title = row.findElement(By.xpath("td[2]")).getText();
			if (company.equals(exp.company) && title.equals(exp.jobTitle))
				targetRow = true;
		}

		// check targetRow
		if (!targetRow)
			throw new Exception(String.format("Added experience not found: [%s, %s]%n", exp.company, exp.jobTitle));
	}

	private static void addExpNegValidation(WebDriver driver, Experience exp) throws Exception {
		// find error elements
		List<WebElement> alerts = driver.findElements(By.cssSelector(".alert.alert-danger"));
		// check for size match
		if (exp.ErrorMessages.size() != alerts.size())
			throw new Exception(
					String.format("Actual error numbers mismatch.%nExpected number(s): %d%nActual number(s): %d%n",
							exp.ErrorMessages.size(), alerts.size()));

		// find actual error texts
		List<String> alertTexts = new ArrayList<>();
		for (WebElement alert : alerts)
			alertTexts.add(alert.getText());

		// validation
		for (String msg : exp.ErrorMessages) {
			if (!alertTexts.contains(msg))
				throw new Exception("Expected error message not found: " + msg);
		}
	}

	public static void addEducation(WebDriver driver, Education edu) {
		// go to dash board
		driver.get("https://boratech-practice-app.onrender.com/dashboard");
		driver.findElement(By.xpath("//a[@href='/add-education']")).click();

		// adding education
		driver.findElement(By.xpath("//input[@name='school']")).sendKeys(edu.school);
		driver.findElement(By.xpath("//input[@name='degree']")).sendKeys(edu.degree);
		driver.findElement(By.xpath("//input[@name='fieldofstudy']")).sendKeys(edu.fieldofstudy);
		driver.findElement(By.xpath("//input[@name='from']")).sendKeys(edu.fromDate);
		if (edu.current)
			driver.findElement(By.name("current")).click();
		else
			driver.findElement(By.xpath("//input[@name='to']")).sendKeys(edu.toDate);
		driver.findElement(By.xpath("//textarea[@name='description']")).sendKeys(edu.programDescription);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		Testkeys.jsViewTop(driver);
	}

	public static void validateEducation(WebDriver driver, Education edu) throws Exception {
		if (edu.isTestPositive)
			addEduPosValidation(driver, edu);
		else
			addEduNegValidation(driver, edu);
	}

	private static void addEduPosValidation(WebDriver driver, Education edu) throws Exception {
		String url = driver.getCurrentUrl();
		if (!url.endsWith("dashboard"))
			driver.get("https://boratech-practice-app.onrender.com/dashboard");

		// wait for the dash board page
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("dashboard"));

		// locate education table
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(
				ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h2[text()='Education Credentials']"))));
		boolean targetRow = false;
		By eduTableLocator = RelativeLocator.with(By.tagName("table"))
				.below(By.xpath("//h2[text()='Education Credentials']"));
		// find all the experience table rows
		List<WebElement> rows = driver.findElement(eduTableLocator).findElements(By.xpath("tbody/tr"));
		for (WebElement row : rows) {
			String school = row.findElement(By.xpath("td[1]")).getText();
			String degree = row.findElement(By.xpath("td[2]")).getText();
			if (school.equals(edu.school) && degree.equals(edu.degree))
				targetRow = true;
		}

		// check targetRow
		if (!targetRow)
			throw new Exception(String.format("Added education not found: [%s, %s]%n", edu.school, edu.degree));
	}

	private static void addEduNegValidation(WebDriver driver, Education edu) throws Exception {
		// find error elements
		List<WebElement> alerts = driver.findElements(By.cssSelector(".alert.alert-danger"));
		// check for size match
		if (edu.ErrorMessages.size() != alerts.size())
			throw new Exception(
					String.format("Actual error numbers mismatch.%nExpected number(s): %d%nActual number(s): %d%n",
							edu.ErrorMessages.size(), alerts.size()));

		// find actual error texts
		List<String> alertTexts = new ArrayList<>();
		for (WebElement alert : alerts)
			alertTexts.add(alert.getText());

		// validation
		for (String msg : edu.ErrorMessages) {
			if (!alertTexts.contains(msg))
				throw new Exception("Expected error message not found: " + msg);
		}
	}

	public static void deleteAllExperiences(WebDriver driver) throws Exception {
		String url = driver.getCurrentUrl();
		if (!url.endsWith("dashboard"))
			driver.get("https://boratech-practice-app.onrender.com/dashboard");

		// wait for the dash board page
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.urlContains("dashboard"));

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
		String url = driver.getCurrentUrl();
		if (!url.endsWith("dashboard"))
			driver.get("https://boratech-practice-app.onrender.com/dashboard");

		// wait for the dash board page
		new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.urlContains("dashboard"));

		// delete everything
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
