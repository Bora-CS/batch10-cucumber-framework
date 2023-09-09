package hui_automation.utilities;

import java.util.HashMap;
import java.util.Map;

public class UtilsTester {

	public static void main(String[] args) {
		Map<String, String> input = new HashMap<>();
		input.put("name", null);
		input.put("location", "China");
		System.out.println("Input: " + input);
		System.out.println("Output: " + Testkeys.getNoNullMap(input));
	}

}
