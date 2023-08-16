package hui_automation.utilities;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import hui_automation.pojo.AmazonSearchResult;

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
		int maxID = 1, minID = 1, avgID = 1;
		double min = firstResult.price;
		double avg = 0, sum = 0;

		for (int index = 0; index < results.size(); index++) {
			XSSFRow row = sheet.createRow(index + 1);
			AmazonSearchResult result = results.get(index);
			row.createCell(0).setCellValue(result.id);
			row.createCell(1).setCellValue(result.price);
			row.createCell(2).setCellValue(result.title);
			row.createCell(3).setCellValue(result.subtitle);
			if (result.price > max)
				max = result.price;
			if (result.price < min)
				min = result.price;
			sum += result.price;
		}
		avg = sum / results.size();

		XSSFRow maxRow = sheet.createRow(results.size() + 2);
		FileOutputStream output;
		try {
			output = new FileOutputStream("./target/ASR-" + "" + ".xlsx");
			workbook.write(output);
			workbook.close();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
