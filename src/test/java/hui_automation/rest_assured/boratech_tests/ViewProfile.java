package hui_automation.rest_assured.boratech_tests;

import hui_automation.utilities.BoraTechAPIs;

public class ViewProfile {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
			BoraTechAPIs.getUserProfile(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
