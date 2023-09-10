package hui_automation.utilities;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testkeys {

	public static String getDateInput(LocalDate date, String dateInputPattern) {
		return date.format(DateTimeFormatter.ofPattern(dateInputPattern));
	}

	public static LocalDate getDate(String dateData, String datePattern) {
		return LocalDate.parse(datePattern, DateTimeFormatter.ofPattern(datePattern));
	}

	public static Map<String, String> getNoNullMap(Map<String, String> data) {
		Map<String, String> output = new HashMap<>();
		for (Entry<String, String> entry : data.entrySet()) {
			output.put(entry.getKey(), entry.getValue());
			if (entry.getValue() == null)
				output.put(entry.getKey(), "");
		}
		return output;
	}

	public static String getTimeStr() {
		LocalDateTime present = LocalDateTime.now();
		String timeStr = present.getYear() + "";
		timeStr += present.getMonthValue();
		timeStr += present.getDayOfMonth();
		timeStr += present.getHour();
		timeStr += present.getMinute();
		timeStr += present.getSecond();
		timeStr += present.getNano();
		return timeStr;
	}

	public static String getTimestamp() {
		String timeStr = Timestamp.valueOf(LocalDateTime.now()).getTime() + "";
		return timeStr;
	}

	public static boolean containsElement(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void pause(WebDriver driver, int sec) {
		new Actions(driver).scrollByAmount(0, 0).pause(Duration.ofSeconds(sec)).build().perform();
	}

	public static void jsClick(WebDriver driver, By locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", driver.findElement(locator));
	}

	public static void jsViewTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 0)");
	}

	public static void terminate(WebDriver driver) {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void clickDropDown(WebDriver driver, By loctor) {
		try {
			driver.findElement(loctor).click();
		} catch (ElementClickInterceptedException e) {
			new Actions(driver).scrollToElement(driver.findElement(loctor)).moveToElement(driver.findElement(loctor))
					.click().build().perform();
		} catch (ElementNotInteractableException e) {
			new Actions(driver).scrollToElement(driver.findElement(loctor)).moveToElement(driver.findElement(loctor))
					.click().build().perform();
		}
	}

	public static void waitUtilURL_Contains(WebDriver driver, String partialURL, int sec) {
		new WebDriverWait(driver, Duration.ofSeconds(sec)).until(ExpectedConditions.urlContains(partialURL));
	}

}
