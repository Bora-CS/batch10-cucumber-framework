package hui_automation.steps.api;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
	public void api_user_adds_a_wrong_experience_with_test_company_and(String company, String title, String from) {
		Experience exp = new Experience(company, title, "", from, "", true, "");
		List<Error> errors = BoraTechAPIs.putExperienceWrong(dataManager.getToken(), exp);
		List<String> texts = new ArrayList<>();
		for (Error error : errors)
			texts.add(error.msg);
		dataManager.setTexts(texts);
	}

	@Then("[API] user sees a list of error messages of [Experience]")
	public void api_user_sees_a_list_of_error_messages_of_experience(DataTable dataTable) {
		// generate expected error messages
		List<String> expectedErrorMsgs = Arrays.asList(dataTable.asMap().get("error").split(","));

		// generate actual error messages
		List<String> actualErrorMsgs = dataManager.getTexts();

		// validation
		Collections.sort(expectedErrorMsgs);
		Collections.sort(actualErrorMsgs);
		assertEquals(expectedErrorMsgs, actualErrorMsgs);
	}

}
