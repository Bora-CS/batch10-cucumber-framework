package hui_automation.selenium;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPractice0815 {

	public static void main(String[] args) {
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("men's shoes results");
		XSSFRow headerRow = sheet.createRow(0);
		XSSFCell headerRowFirstCell = headerRow.createCell(0);
		headerRowFirstCell.setCellValue("Something");

		try {
			FileOutputStream output = new FileOutputStream("./target/test.xlsx");
			workbook.write(output);

			workbook.close();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
