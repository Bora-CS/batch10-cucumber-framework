package hui_automation;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestAsst {

	public static String findInputDateStrMDY(String dateStr, String dateStrPattern) {
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

	public static void sleep(int sec) throws Exception {
		Thread.sleep(sec * 1000);
	}

}
