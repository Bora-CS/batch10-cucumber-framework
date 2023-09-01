package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hui_automation.api_pojos.Error;
import hui_automation.api_pojos.Experience;
import hui_automation.utilities.BoraTechAPIs;
import hui_automation.utilities.DataManager;
import hui_automation.utilities.Testkeys;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

public class AddExperienceSteps {

	private DataManager dataManager = DataManager.getInstance();
	private String title;
	private String company;
	private String from;

	@Then("[API] user adds a new [Experience]")
	public void api_user_adds_a_new_experience(DataTable dataTable) {
		Map<String, String> data = dataTable.asMap();
		Experience expData = new Experience(data.get("company") + " " + Testkeys.getUniqueMillsTimeStr(),
				data.get("title"), data.get("location"), data.get("from"), data.get("to"),
				Boolean.parseBoolean(data.get("current")), data.get("description"));
		List<Experience> allExperiences = BoraTechAPIs.putExperience(dataManager.getToken(), expData);
		boolean targetFound = false;
		for (Experience actualExperience : allExperiences) {
			if (actualExperience.equals(expData)) {
				targetFound = true;
				break;
			}
		}
		assertTrue(targetFound);
		System.out.println(expData.company + ", " + expData.title);
	}

	@When("[API] user adds a wrong [Experience] with {}, {} and {}")
	public void api_user_adds_a_wrong_experience_with_test_company_and(String title, String company, String from) {
		this.title = title;
		this.company = company;
		this.from = from;
	}

	@Then("[API] user sees a list of error messages of [Experience]")
	public void api_user_sees_a_list_of_error_messages_of_experience(DataTable dataTable) {
		// system default error messages
		Map<String, String> totalErrors = dataTable.asMap();

		Map<String, String> testData = new HashMap<>();
		testData.put("title", this.title);
		testData.put("company", this.company);
		testData.put("from", this.from);

		// generate expected error messages
		List<String> expectedErrorMsgs = new ArrayList<>();
		for (String errorKey : testData.keySet())
			if (testData.get(errorKey).isEmpty())
				expectedErrorMsgs.add(totalErrors.get(errorKey));

		// api request
		Experience exp = new Experience(this.company, this.title, "", this.from, "", true, "");
		List<Error> actualErrors = BoraTechAPIs.putExperienceWrong(dataManager.getToken(), exp);

		// generate actual error messages
		List<String> actualErrorMsgs = new ArrayList<>();
		for (Error error : actualErrors)
			actualErrorMsgs.add(error.msg);

		// validation
		assertEquals(expectedErrorMsgs.size(), actualErrorMsgs.size());
		for (String msg : actualErrorMsgs)
			assertTrue(expectedErrorMsgs.contains(msg));
	}

}
