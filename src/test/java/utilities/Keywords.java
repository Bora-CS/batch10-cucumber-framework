package utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import apiPojos.Education;
import apiPojos.Experience;

public class Keywords {

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}

	public static void waitWithoutTry(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (Exception e) {

		}
	}

	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "";
	}

	/**
	 * Converts date string from "MM/dd/yyyy" format to "yyyy-mm-dd" format
	 * 
	 * @param inputDateString
	 * @return formattedDateString
	 */
	public static String formatInputDate(String inputDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(inputDateString, formatter);
		return date.toString();
	}

	public static boolean checkIfElementExists(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void checkIfElementExists(WebDriver driver, By locator, String errorMessage) throws Exception {
		boolean found = checkIfElementExists(driver, locator);

		if (!found) {
			throw new Exception(errorMessage);
		}
	}

	public static void switchToTheOtherWindow(WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();
		String mainHandle = driver.getWindowHandle();

		for (String handle : handles) {
			if (!handle.equals(mainHandle)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public static Experience convertMapToExperience(Map<String, String> data) {
		String company = data.get("company") == null ? "" : data.get("company") + getTimeStamp();
		String title = data.get("title") == null ? "" : data.get("title");
		String location = data.get("location") == null ? "" : data.get("location");
		String from = data.get("from") == null ? "" : data.get("from");
		String to = data.get("to") == null ? "" : data.get("to");
		boolean current = data.get("current") == null ? false : Boolean.valueOf(data.get("current"));
		String description = data.get("description") == null ? "" : data.get("description");

		Experience experience = new Experience(company, title, location, from, to, current, description);

		return experience;
	}

}
