package hui_automation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilsTester {

	public static void main(String[] args) {
		LocalDate current = LocalDate.now();
		String dayStr = current.format(DateTimeFormatter.ofPattern("MMMM"));
		System.out.println(dayStr);
	}

}
