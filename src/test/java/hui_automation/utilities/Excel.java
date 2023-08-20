package hui_automation.utilities;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hui_automation.pojos.AmazonSearchResult;

public class Excel {

	public static void exportAmazonSearchResult(String searchTerm, List<AmazonSearchResult> results) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(searchTerm);

		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Price");
		headerRow.createCell(2).setCellValue("Title");
		headerRow.createCell(3).setCellValue("Subtitle");

		AmazonSearchResult firstResult = results.get(0);
		double max = firstResult.price;
		double min = firstResult.price;
		double avg = 0, sum = 0;
		int maxID = 1, minID = 1;

		for (int index = 0; index < results.size(); index++) {
			XSSFRow row = sheet.createRow(index + 1);
			AmazonSearchResult result = results.get(index);
			row.createCell(0).setCellValue(result.id);
			row.createCell(1).setCellValue(result.price);
			row.createCell(2).setCellValue(result.title);
			row.createCell(3).setCellValue(result.subtitle);
			if (result.price > max) {
				max = result.price;
				maxID = result.id;
			}
			if (result.price < min) {
				min = result.price;
				minID = result.id;
			}
			sum += result.price;
		}
		avg = sum / results.size();

		XSSFRow maxRow = sheet.createRow(results.size() + 2);
		XSSFRow minRow = sheet.createRow(results.size() + 3);
		XSSFRow avgRow = sheet.createRow(results.size() + 4);

		maxRow.createCell(0).setCellValue("Max");
		maxRow.createCell(1).setCellValue(max);
		maxRow.createCell(3).setCellValue("Product ID");
		maxRow.createCell(4).setCellValue(maxID);

		minRow.createCell(0).setCellValue("Min");
		minRow.createCell(1).setCellValue(min);
		minRow.createCell(3).setCellValue("Product ID");
		minRow.createCell(4).setCellValue(minID);

		avgRow.createCell(0).setCellValue("Avg");
		avgRow.createCell(1).setCellValue(avg);

		FileOutputStream output;
		String ts = Testkeys.getUniqueMillsTimeStr();
		try {
			output = new FileOutputStream("./target/ASR-" + ts + ".xlsx");
			workbook.write(output);
			workbook.close();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
