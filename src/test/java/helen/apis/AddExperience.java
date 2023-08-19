package helen.apis;

import helen.pojo.Experience;

public class AddExperience {

	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		Experience exp = new Experience("Test Automation Engineer", "Bora Tech", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", null);
		
		helen.utilities.BoraTechApis.addExperience(token, exp);
	}

}
