package helen.utilities;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Keywords {

	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
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

	// overloading with different parameters
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
	

	public static Double getMaxPrice(WebDriver driver, ArrayList<Double> prices) {
		Double max = prices.get(0);
		for (int i = 0; i < prices.size(); i++) {
			if (prices.get(i) > max) {
				max = prices.get(i);
			}
		}
		return max;

	}

	public static Double getMinPrice(WebDriver driver, ArrayList<Double> prices) {
		Double min = prices.get(0);
		for (int i = 0; i < prices.size(); i++) {
			if (prices.get(i) < min) {
				min = prices.get(i);
			}
		}
		return min;

	}

	public static double getAvePrice(WebDriver driver, ArrayList<Double> prices) {
		double sum = prices.get(0);
		double ave = prices.get(0);
		for (int i = 0; i < prices.size(); i++) {
			sum += prices.get(i);
		}
		ave = sum / prices.size();
		double roundedAve = (double) Math.round(ave * 100.0) / 100.0;
		return roundedAve;
	}

}
