package helen;

import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelPractice {

	public static void main(String[] args) {

		//create new file, Apache object
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Toddler Book Results");
		XSSFRow row = sheet.createRow(0);
		XSSFCell row1cell1 = row.createCell(0);	
		row1cell1.setCellValue("Hello World!");
		
		try {
			FileOutputStream fos = new FileOutputStream("target/test.xlsx");  //write data from memory into actual file using interface
			workbook.write(fos);
			workbook.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
