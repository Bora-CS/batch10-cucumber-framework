package ardal_practice;

import java.time.LocalDateTime;

public class Tools {

	public static String timeStamp() {
		
		LocalDateTime currentTime = LocalDateTime.now();
		return currentTime + "";
	}
	
	
}
