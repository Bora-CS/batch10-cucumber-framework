package hui_automation.rest_assured.boratech_tests;

import hui_automation.pojo.Experience;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class ViewExperience {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);
			
			String company = "Walmart";
			company += " " + Testkeys.getUniqueMillsTimeStr();
			Experience exp = new Experience(company, "Cashier", "Manassas, Virginia", "2006/06/06", "2008/11/11", false,
					"Payment collection.", null);

			BoraTechAPIs.putExperience(token, exp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
