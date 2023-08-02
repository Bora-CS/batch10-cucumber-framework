package hui_automation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestAsst {

	public static String findInputDateStrYMD(String dateStr) {
		LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		String inputDateStr = date.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		return inputDateStr;
	}

}
