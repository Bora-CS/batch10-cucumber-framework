package hui_automation.rest_assured.boratech_tests;

import java.util.List;

import hui_automation.api_pojos.Education;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.Testkeys;

public class AddEducationTest {

	public static void main(String[] args) {
		String email = "hui-pretender@outlook.com";
		String password = "Hui123456";
		try {
			String token = BoraTechAPIs.login(email, password);

			String school = "BoraTech School";
			school += " " + Testkeys.getUniqueMillsTimeStr();
			Education edu = new Education(school, "Certificate of Completion", "Test Automation Engineering",
					"2023-05-07", "", true, "Web application testing for quality control.", null);
			// add education
			List<Education> educations = BoraTechAPIs.putEducation(token, edu);

			// validate education
			boolean targetFound = false;
			for (Education education : educations) {
				if (education.equals(edu)) {
					targetFound = true;
					System.out.println(education.school + ", " + education.degree);
					break;
				}
			}
			if (!targetFound)
				throw new Exception("Target education not found.");

			System.out.println("Test passed.");
		} catch (Exception e) {
			System.out.println("Test failed!");
			System.out.println("Reason: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
