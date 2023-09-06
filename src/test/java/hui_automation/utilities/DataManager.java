package hui_automation.utilities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import hui_automation.api_pojos.Error;
import hui_automation.pojos.Education;
import hui_automation.pojos.Experience;

public class DataManager {

	private static DataManager dataManager = null;

	private String token;
	private List<Error> errors;
	private Experience experienceUI;
	private Education educationUI;

	private DataManager() {
	}

	public static DataManager getInstance() {
		if (dataManager == null) {
			dataManager = new DataManager();
		}
		return dataManager;
	}

	public static void tearDown() {
		dataManager = null;
	}

	public String getToken() {
		assertNotNull(token, "DataManager - Token is not available");
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Error> getErrors() {
		assertNotNull(errors, "No error message.");
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}

	public Experience getExperienceUI() {
		assertNotNull(experienceUI, "No experience created.");
		return experienceUI;
	}

	public void setExperienceUI(Experience experienceUI) {
		this.experienceUI = experienceUI;
	}

	public Education getEducationUI() {
		assertNotNull(educationUI, "No education created.");
		return educationUI;
	}

	public void setEducationUI(Education educationUI) {
		this.educationUI = educationUI;
	}

}
