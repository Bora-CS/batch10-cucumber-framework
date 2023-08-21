package helen.apis;

import helen.pojo.Education;

public class AddEducation {

	public static void main(String[] args) {
		
		String token = helen.utilities.BoraTechApis.login("helenhjahn@gmail.com", "06102021");
		Education edu = new Education("Boratech", "Certified Test Automation Engineer", "Certified Test Automation Engineer", "05/01/2022", "10/30/2022", false, "Learning all about Test Automation.", null);		
		
		helen.utilities.BoraTechApis.addEducation(token, edu);
	}
	

}
