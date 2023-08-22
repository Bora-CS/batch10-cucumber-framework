package anthony;

import java.io.FileOutputStream;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.AmazonSearchResult;
import utilities.Keywords;

public class UTILITIES {
	// wait function
	public static void wait(int second) throws InterruptedException {
		Thread.sleep(second * 1000);
	}

	public static void waitWithoutTry(int second) {
		try {
			Thread.sleep(second * 1000);
		} catch (Exception e) {

		}
	}

	// get time stamp
	public static String getTimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return timestamp.getTime() + "";
	}

	// format date
	public static String formatInputDate(String inputDateString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate date = LocalDate.parse(inputDateString, formatter);
		return date.toString();
	}

	// validate by locator
	public static boolean checkIfElementExists(WebDriver driver, By locator) {
		try {
			driver.findElement(locator);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// validate errorMessage
	public static void checkIfElementExists(WebDriver driver, By locator, String errorMessage) throws Exception {
		boolean found = checkIfElementExists(driver, locator);

		if (!found) {
			throw new Exception(errorMessage);
		}
	}

	// switch to new tab
	public static void switchToTheOtherWindow(WebDriver driver) {
		Set<String> handles = driver.getWindowHandles();
		String mainHandle = driver.getWindowHandle();

		for (String handle : handles) {
			if (!handle.equals(mainHandle)) {
				driver.switchTo().window(handle);
			}
		}
	}

	// create new excel
	public static void createExcel(String searchTerm, List<AmazonSearchResult> results) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(searchTerm);

		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Price");
		headerRow.createCell(2).setCellValue("Title");

		double firstResultPrice = results.get(0).price;
		double min = firstResultPrice;
		double max = firstResultPrice;
		double avg = 0, sum = 0;
		for (int index = 0; index < results.size(); index++) {
			XSSFRow row = sheet.createRow(index + 1);
			AmazonSearchResult result = results.get(index);
			row.createCell(0).setCellValue(result.id);
			row.createCell(1).setCellValue(result.price);
			row.createCell(2).setCellValue(result.title);

			if (result.price > max) {
				max = result.price;
			}

			if (result.price < min) {
				min = result.price;
			}

			sum += result.price;
		}
		avg = sum / results.size();

		XSSFRow minRow = sheet.createRow(results.size() + 2);
		XSSFRow maxRow = sheet.createRow(results.size() + 3);
		XSSFRow avgRow = sheet.createRow(results.size() + 4);

		minRow.createCell(0).setCellValue("Min");
		minRow.createCell(1).setCellValue(min);

		maxRow.createCell(0).setCellValue("Max");
		maxRow.createCell(1).setCellValue(max);

		avgRow.createCell(0).setCellValue("Avg");
		avgRow.createCell(1).setCellValue(avg);

		String timeStamp = Keywords.getTimeStamp();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("target/ASR-" + timeStamp + ".xlsx");
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// API LOGIN
	public static String login(String email, String password) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		HashMap<String, String> body = new HashMap<>();
		body.put("email", email);
		body.put("password", password);

		request.body(body);
		request.header("Content-Type", "application/json");

		Response response = request.post(endpoint);

		JsonPath jp = response.jsonPath();
		String token = jp.get("token");
		return token;
	}

	// API TOKEN
	public static String getToken(String token) {
		String endpoint = "/api/auth";
		RestAssured.baseURI = "https://boratech-practice-app.onrender.com";
		RequestSpecification request = RestAssured.given();

		request.header("x-auth-token", token);

		Response response = request.get(endpoint);
		return response.jsonPath().get("name");
	}

}
