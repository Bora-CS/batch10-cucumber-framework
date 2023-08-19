package ardal_practice.pojo;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import utilities.Keywords;

public class ExcelArdal {

	public static void exportIndigoSearchResults(String searchTerm, List<IndigoSearchResult> results) {

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
		int maxId = 0, minId = 0;
		String maxTitle = "";
		String minTitle = "";
		for (int index = 0; index < results.size(); index++) {
			XSSFRow row = sheet.createRow(index + 1);
			IndigoSearchResult result = results.get(index);
			row.createCell(0).setCellValue(result.id);
			row.createCell(1).setCellValue(result.price);
			row.createCell(2).setCellValue(result.title);

			if (result.price > max) {
				max = result.price;
				maxId = result.id;
				maxTitle = result.title;
			}

			if (result.price < min) {
				min = result.price;
				minId = result.id;
				minTitle = result.title;
			}

			sum += result.price;
		}
		avg = sum / results.size();

		XSSFRow minRow = sheet.createRow(results.size() + 2);
		XSSFRow maxRow = sheet.createRow(results.size() + 3);
		XSSFRow avgRow = sheet.createRow(results.size() + 4);

		minRow.createCell(0).setCellValue("Min");
		minRow.createCell(1).setCellValue(min);
		minRow.createCell(2).setCellValue(minId);
		minRow.createCell(3).setCellValue(minTitle);

		maxRow.createCell(0).setCellValue("Max");
		maxRow.createCell(1).setCellValue(max);
		maxRow.createCell(2).setCellValue(maxId);
		maxRow.createCell(3).setCellValue(maxTitle);

		avgRow.createCell(0).setCellValue("Avg");
		avgRow.createCell(1).setCellValue(avg);

		String timeStamp = Keywords.getTimeStamp();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("target/ISR<Ardal>-" + timeStamp + ".xlsx");
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
