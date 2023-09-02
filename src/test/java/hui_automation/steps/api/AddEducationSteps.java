package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Education;
import hui_automation.api_pojos.Error;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AddEducationSteps {

	private DataManager dataManager = DataManager.getInstance();
	private List<Error> errors;
	private Education edu;

	@Then("[API] user adds a new [Education]")
	public void api_user_adds_a_new_education(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Education eduData = new Education(data.get("school") + " " + Testkeys.getUniqueMillsTimeStr(),
				data.get("degree"), data.get("fieldofstudy"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"));
		List<Education> allEducations = BoraTechAPIs.putEducation(dataManager.getToken(), eduData);
		boolean targetFound = false;
		for (Education actualEducation : allEducations) {
			if (actualEducation.equals(eduData)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
		System.out.println(eduData.school + ", " + eduData.degree);
	}

	@When("[API] user adds a wrong [Education] with {}, {}, {} and {}")
	public void api_user_adds_a_wrong_education_with_and(String school, String degree, String fieldofstudy,
			String from) {
		Education edu = new Education(school, degree, fieldofstudy, from, "", true, "");
		this.errors = BoraTechAPIs.putEducationWrong(dataManager.getToken(), edu);
		this.edu = edu;
	}

	@Then("[API] user sees a list of error messages of [Education]")
	public void api_user_sees_a_list_of_error_messages(DataTable dataTable) {
		// system default error messages
		Map<String, String> totalErrors = dataTable.asMap();

		Map<String, String> testData = new HashMap<>();
		testData.put("school", this.edu.school);
		testData.put("degree", this.edu.degree);
		testData.put("fieldofstudy", this.edu.fieldofstudy);
		testData.put("from", this.edu.from);

		// generate expected error messages
		List<String> expectedErrorMsgs = new ArrayList<>();
		for (String errorKey : testData.keySet())
			if (testData.get(errorKey).isEmpty())
				expectedErrorMsgs.add(totalErrors.get(errorKey));

		// generate actual error messages
		List<String> actualErrorMsgs = new ArrayList<>();
		for (Error error : this.errors)
			actualErrorMsgs.add(error.msg);

		// validation
		Collections.sort(expectedErrorMsgs);
		Collections.sort(actualErrorMsgs);
		assertEquals(expectedErrorMsgs, actualErrorMsgs);
	}

}
