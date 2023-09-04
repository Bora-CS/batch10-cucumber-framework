package pages.bora_tech;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DashboardPage {

	private static WebElement element;
	private static List<WebElement> elements;
	private static String experienceTableXpath = "//*[text()='Experience Credentials']/following-sibling::table[1]";
	private static String educationTableXpath = "//*[text()='Education Credentials']/following-sibling::table[1]";

	public static WebElement pageTitle(WebDriver driver) {
		element = driver.findElement(By.xpath("//h1[@class='large text-primary']"));
		return element;
	}

	public static WebElement EditProfileButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='/edit-profile']"));
		return element;
	}

	public static WebElement AddExperienceButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='/add-experience']"));
		return element;
	}

	public static WebElement AddEducationButton(WebDriver driver) {
		element = driver.findElement(By.xpath("//a[@href='/add-education']"));
		return element;
	}

	public static WebElement ExperienceTable(WebDriver driver) {
		element = driver.findElement(By.xpath(experienceTableXpath));
		return element;
	}

	public static List<WebElement> ExperienceTableRows(WebDriver driver) {
		elements = driver.findElements(By.xpath(experienceTableXpath + "/tbody/tr"));
		return elements;
	}

	public static WebElement experienceDeleteButton(WebDriver driver) {
		element = driver.findElement(By.xpath(experienceTableXpath + "/tbody/tr/td/button"));
		return element;
	}

	public static WebElement EducationTable(WebDriver driver) {
		element = driver.findElement(By.xpath(educationTableXpath));
		return element;
	}

	public static List<WebElement> EducationTableRows(WebDriver driver) {
		elements = driver.findElements(By.xpath(educationTableXpath + "/tbody/tr"));
		return elements;
	}

	public static WebElement educationDeleteButton(WebDriver driver) {
		element = driver.findElement(By.xpath(educationTableXpath + "/tbody/tr/td/button"));
		return element;
	}

}
