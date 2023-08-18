package hui_automation.rest_assured.boratech_tests;

import hui_automation.pojo.Education;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class ViewEducation {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);

			String school = "BoraTech School";
			school += " " + Testkeys.getUniqueMillsTimeStr();
			Education edu = new Education(school, "Certificate of Completion", "Test Automation Engineering",
					"2023/05/07", null, true, "Web application testing for quality control.", null);
			
			BoraTechAPIs.putEducation(token, edu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
