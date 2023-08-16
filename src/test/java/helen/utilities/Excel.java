package helen.utilities;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import helen.pojo.AmazonSearchResult;

public class Excel {

	public static void exportAmazonSearchResults(String searchTerm, List<AmazonSearchResult> results) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("searchTerm");

		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Price");
		headerRow.createCell(2).setCellValue("Title");
		
		double firstResultPrice = results.get(0).price;
		double min = firstResultPrice;
		double max = firstResultPrice;
		double avg = 0.0, sum = 0;
		
		for (int index = 0; index < results.size(); index++) {
			XSSFRow row = sheet.createRow(index + 1);  //right below the header
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
		maxRow.createCell(0).setCellValue("Max");
		avgRow.createCell(0).setCellValue("Avg");
		
		minRow.createCell(1).setCellValue(min);
		maxRow.createCell(1).setCellValue(max);
		avgRow.createCell(1).setCellValue(avg);
		
		String timeStamp =  helen.utilities.Keywords.getTimeStamp();
		FileOutputStream fos;
		try {
			fos = new FileOutputStream("target/ASR-" + timeStamp + ".xlsx"); // write data from memory into actual file using interface
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}


/// put Pr0duct ID and Title INTO C203 CELL