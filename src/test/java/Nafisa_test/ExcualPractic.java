package Nafisa_test;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcualPractic {

	public static void main(String[] args) {
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Shanmpoo Result");
		XSSFRow row = sheet.createRow(0);
		XSSFCell row1Cell1 = row.createCell(0);
		
		row1Cell1 .setCellValue("Hello world");
		
		try {
		
		FileOutputStream fos = new FileOutputStream("target/test.xlsx");
		workbook.write(fos);
		
		workbook.close();
		fos.close();
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
