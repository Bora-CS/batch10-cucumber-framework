package lydia.utilities;

import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import lydia.POJO.AmazonSearchResult;

public class ExcelLydia {
	
	public static void exportAmazonSearchResults(String searchItem, List<AmazonSearchResult> results) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(searchItem);
		
		XSSFRow headerRow = sheet.createRow(0);
		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("PRICE");
		headerRow.createCell(2).setCellValue("TITLE");
		
		double min = results.get(0).price;//firstResultPrice
		double max = results.get(0).price;
		double avg = 0.0, sum = 0.0;
		for (int index=0; index<results.size(); index++) {
			XSSFRow row = sheet.createRow(index +1);
			AmazonSearchResult result = results.get(index);
			row.createCell(0).setCellValue(result.id);
			row.createCell(1).setCellValue(result.price);
			row.createCell(2).setCellValue(result.title);
			
			if(result.price>max) {
				max = result.price;
			}
			if(result.price<min) {
				min = result.price;
			}
			sum+=result.price;
			
		}
		
		
		avg = sum/results.size();
		
		XSSFRow minRow = sheet.createRow(results.size()+2);
		XSSFRow maxRow = sheet.createRow(results.size()+3);
		XSSFRow avgRow = sheet.createRow(results.size()+4);
		
		minRow.createCell(0).setCellValue("Min");
		maxRow.createCell(0).setCellValue("Max");
		avgRow.createCell(0).setCellValue("Avg");
		
		minRow.createCell(1).setCellValue(min);
		maxRow.createCell(1).setCellValue(max);
		avgRow.createCell(1).setCellValue(avg);
		
		String timeStamp = Keywords.getTimeStamp();
		FileOutputStream fos;
		try {
			 fos = new FileOutputStream("target/ASR-L-" + timeStamp + ".xlsx");
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	}

