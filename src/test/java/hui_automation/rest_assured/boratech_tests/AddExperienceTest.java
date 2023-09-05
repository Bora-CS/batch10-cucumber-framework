package hui_automation.rest_assured.boratech_tests;

import java.util.List;

import hui_automation.api_pojos.Experience;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class AddExperienceTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);

			String company = "Walmart";
			company += " " + Testkeys.getUniqueMillsTimeStr();
			Experience exp = new Experience(company, "Cashier", "Manassas, Virginia", "2006-06-06", "2008-11-11", false,
					"Payment collection.");
			// add experience
			List<Experience> experiences = BoraTechAPIs.putExperience(token, exp);

			// validate experience
			boolean targetFound = false;
			for (Experience experience : experiences) {
				if (experience.equals(exp)) {
					targetFound = true;
					System.out.println(experience.company + ", " + experience.title);
					break;
				}
			}
			if (!targetFound)
				throw new Exception("Target experience not found.");

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
