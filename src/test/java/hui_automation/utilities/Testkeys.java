package hui_automation.utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Testkeys {

	public static String findDateInputStrMDY(String dateStr, String dateStrPattern) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(dateStrPattern));
		String inputDateStr = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		return inputDateStr;
	}

	public static String getUniqueTimeStr() {
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

	public static String getUniqueMillsTimeStr() {
		String timeStr = Timestamp.valueOf(LocalDateTime.now()).getTime() + "";
		return timeStr;
	}

	public static boolean containsElement(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void containsElement(WebDriver driver, By locator, String errStr) throws Exception {
		boolean result = containsElement(driver, locator);
		if (!result)
			throw new Exception(errStr);
	}

	public static void pause(int sec) throws Exception {
		Thread.sleep(sec * 1000);
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

	public static WebDriver getChromeDriver() {
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(co);
		return driver;
	}

	public static void clickDropDown(WebDriver driver, By loctor) {
		try {
			driver.findElement(loctor).click();
		} catch (ElementClickInterceptedException e) {
			Actions act = new Actions(driver);
			act.scrollToElement(driver.findElement(loctor)).perform();
			driver.findElement(loctor).click();
		}
	}

}
