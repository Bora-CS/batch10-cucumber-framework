package helen.apis;

import helen.pojo.Experience;

public class AddExperience {

	public static void main(String[] args) {

		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		Experience exp1 = new Experience("Test Automation Engineer", "Bora Tech", "Annandale,VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", null);
		Experience exp2 = new Experience("Tester", "Bora", ",VA", "05/01/2022", "10/01/2022", false, "Build and maintain an automation framework for the software using Selenium.", null);
		
		//two different individual objects
		//Default object
		System.out.println(exp1.equals(exp2));  //false  euqals method from object class
		
		
		helen.utilities.BoraTechApis.addExperience(token, exp);
		
		
		
	}

}
